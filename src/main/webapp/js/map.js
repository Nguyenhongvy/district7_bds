    // Đảm bảo hàm initMap trong global scope
    window.initMap = function() {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 13,
            center: DISTRICT7_CENTER,
            mapTypeId: 'roadmap',
            styles: [
                {
                    featureType: 'poi',
                    elementType: 'labels',
                    stylers: [{ visibility: 'off' }]
                }
            ],
            mapTypeControl: true,
            streetViewControl: true,
            fullscreenControl: true,
            zoomControl: true
        });
        
        infoWindow = new google.maps.InfoWindow();
        loadProjects();
        
        map.addListener('idle', function() {
            loadProjectsInBounds();
        });
        
        hideLoading();
    };

    // Declare global variables
    let map;
    let markers = [];
    let infoWindow;

    // District 7 center coordinates
    const DISTRICT7_CENTER = { lat: 10.7329, lng: 106.7172 };

    // Load all projects
    function loadProjects() {
        showLoading();
        const apiUrl = contextPath + '/map/api/projects';
        
        $.get(apiUrl)
            .done(function(projects) {
                displayProjects(projects);
            })
            .fail(function(xhr) {
                const errorDiv = document.createElement('div');
                errorDiv.innerHTML = `
                    <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); 
                               background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                        <h5>Không thể tải dữ liệu dự án</h5>
                        <p>Vui lòng kiểm tra kết nối và thử lại.</p>
                        <button class="btn btn-primary" onclick="loadProjects()">Thử lại</button>
                    </div>
                `;
                document.getElementById('map').appendChild(errorDiv);
            })
            .always(function() {
                hideLoading();
            });
    }

    // Load projects in current map bounds
    function loadProjectsInBounds() {
        if (!map) return;
        
        const bounds = map.getBounds();
        if (!bounds) return;
        
        const ne = bounds.getNorthEast();
        const sw = bounds.getSouthWest();
        
        const params = {
            neLat: ne.lat(),
            neLng: ne.lng(),
            swLat: sw.lat(),
            swLng: sw.lng()
        };
        
        const type = $('#typeFilter').val();
        const ward = $('#wardFilter').val();
        const status = $('#statusFilter').val();
        
        if (type) params.type = type;
        if (ward) params.ward = ward;
        if (status) params.status = status;
        
        $.get(contextPath + '/map/api/projects', params)
            .done(function(projects) {
                displayProjects(projects);
            });
    }

    // Display projects on map
    function displayProjects(projects) {
        // Clear existing markers
        markers.forEach(marker => marker.setMap(null));
        markers = [];
        
        if (!projects || projects.length === 0) {
            return;
        }
        
        projects.forEach((project) => {
            const marker = createProjectMarker(project);
            if (marker) {
                markers.push(marker);
            }
        });
    }

    function createProjectMarker(project) {
        
    	if (!project || !project.id || !project.latitude || !project.longitude) {
            return null;
        }

        // Tạo vị trí marker
        const position = {
            lat: parseFloat(project.latitude),
            lng: parseFloat(project.longitude)
        };

        // Tạo marker
        const marker = new google.maps.Marker({
            position: position,
            map: map,
            title: project.name || 'Dự án',
            animation: google.maps.Animation.DROP,
            icon: {
                url: contextPath + '/images/map-marker.png',
                scaledSize: new google.maps.Size(40, 40),
                anchor: new google.maps.Point(20, 40)
            }
        });

        // Gán thêm data nếu cần
        marker.projectData = project;
        marker.projectId = project.id;

        // Click listener
        marker.addListener('click', function() {
            
            const clickedProject = this.projectData;
            
            if (!clickedProject || !clickedProject.id) {
                const errorContent = '<div style="color: red; padding: 10px;">Lỗi: Không có dữ liệu dự án</div>';
                
                if (infoWindow) infoWindow.close();
                infoWindow = new google.maps.InfoWindow({
                    content: errorContent
                });
                infoWindow.open(map, this);
                return;
            }
            
            // Đóng InfoWindow hiện tại
            if (infoWindow) {
                infoWindow.close();
            }
            
            // Tạo content
            const content = createInfoWindowContent(clickedProject);
            
            // Tạo và hiển thị InfoWindow
            infoWindow = new google.maps.InfoWindow({
                content: content,
                maxWidth: 320
            });
            
            infoWindow.open(map, this);
            
            // Auto show project detail panel
            showProjectInfo(clickedProject.id);
        });
        
        return marker;
    }
    function createCustomMarkerIcon(projectType) {
        const colors = {
            'APARTMENT': '#3b82f6',
            'VILLA': '#10b981',
            'TOWNHOUSE': '#f59e0b',
            'OFFICE': '#8b5cf6',
            'COMMERCIAL': '#ef4444'
        };
        
        const color = colors[projectType] || '#6b7280';
        const icon = getProjectTypeIcon(projectType);
        
        const svg = `
            <svg width="40" height="40" viewBox="0 0 40 40" xmlns="http://www.w3.org/2000/svg">
                <circle cx="20" cy="20" r="18" fill="${color}" stroke="white" stroke-width="3"/>
                <text x="20" y="26" text-anchor="middle" fill="white" font-family="Arial" font-size="16" font-weight="bold">
                    ${icon}
                </text>
            </svg>
        `;
        
        return 'data:image/svg+xml;charset=UTF-8,' + encodeURIComponent(svg);
    }

    // Get project type icon
    function getProjectTypeIcon(projectType) {
        const icons = {
            'APARTMENT': '🏢',
            'VILLA': '🏡', 
            'TOWNHOUSE': '🏠',
            'OFFICE': '🏢',
            'COMMERCIAL': '🏪'
        };
        return icons[projectType] || '🏗';
    }

function createInfoWindowContent(project) {
    
    if (!project || !project.id) {
        return `<div style="color: red;">Lỗi: Không có ID dự án</div>`;
    }
    
    // Tên dự án
    const projectName = project.name || 'Chưa có tên';
    
    // Địa chỉ
    const address = project.address || 'Chưa có địa chỉ';
    
    // Giá (hiển thị thô, không format)
    let price = 'Liên hệ';
    if (project.priceFrom && project.priceTo) {
        price = `${project.priceFrom} - ${project.priceTo}`;
    } else if (project.priceFrom) {
        price = `Từ ${project.priceFrom}`;
    } else if (project.priceTo) {
        price = `Đến ${project.priceTo}`;
    }
    
    // Loại dự án
    const projectType = project.projectTypeDisplay || project.projectType || 'Chưa phân loại';
    
    // Trạng thái
    const status = project.statusDisplay || project.status || 'Chưa có trạng thái';
    
    const content = `
            <button 
                class="btn btn-primary" 
                style="width: 100%; font-size: 12px; padding: 6px; background: #007bff; border: none; color: white; border-radius: 4px; cursor: pointer;"
                onclick="handleInfoWindowClick(${project.id})"
            >
                <i class="fas fa-info-circle" style="margin-right: 4px;"></i>
                Xem chi tiết
            </button>
        </div>
    `;
    
    return content;
}


    // Handle info window button click
    function handleInfoWindowClick(projectId) {
        if (!projectId || projectId === undefined) {
            showErrorMessage('ID dự án không hợp lệ');
            return;
        }
        showProjectInfo(projectId);
    }
    
    function showProjectInfo(projectId) {
        
        if (!projectId || projectId === 'undefined' || projectId === undefined) {
            showErrorMessage('ID dự án không hợp lệ: ' + projectId);
            return;
        }
        
        const numericId = parseInt(projectId);
        if (isNaN(numericId)) {
            showErrorMessage('ID dự án phải là số: ' + projectId);
            return;
        }
        
        showLoading();
        
        const apiUrl = contextPath + '/map/api/project/' + numericId;
        
        $.ajax({
            url: apiUrl,
            type: 'GET',
            timeout: 10000,
        })
        .done(function(project, textStatus, xhr) {
            
            if (project && typeof project === 'object') {
                displayProjectInfo(project);
            } else {
                showErrorMessage('Dữ liệu dự án không hợp lệ: ' + JSON.stringify(project));
            }
        })
        .fail(function(xhr, textStatus, errorThrown) {
            
            let errorMessage = 'Không thể tải thông tin dự án. ';
            
            switch(xhr.status) {
                case 0:
                    errorMessage += 'Không thể kết nối đến server.';
                    break;
                case 400:
                    errorMessage += 'Yêu cầu không hợp lệ (ID dự án: ' + projectId + ')';
                    break;
                case 404:
                    errorMessage += 'Không tìm thấy dự án.';
                    break;
                case 500:
                    errorMessage += 'Lỗi server nội bộ.';
                    break;
                default:
                    errorMessage += `Lỗi HTTP ${xhr.status}`;
            }
            
            showErrorMessage(errorMessage);
        })
        .always(function() {
            hideLoading();
        });
    }

 function displayProjectInfo(project) {

    if (!project || typeof project !== 'object') {
        showErrorMessage('Dữ liệu dự án không hợp lệ');
        return;
    }


    function formatNumber(num) {
        if (!num || num === null || num === 'null') return '';
        return new Intl.NumberFormat('vi-VN').format(num);
    }

    // Tên dự án
    const projectName = project.name || 'Chưa có tên dự án';

    // Địa chỉ
    const fullAddress = (project.address || 'Chưa có địa chỉ') + 
                       (project.ward ? ', ' + project.ward : '');

    // Giá - không format, chỉ show thô
    let price = 'Liên hệ';
    if (project.priceFrom || project.priceTo) {
        if (project.priceFrom && project.priceTo) {
            price = project.priceFrom + ' - ' + project.priceTo;
        } else if (project.priceFrom) {
            price = 'Từ ' + project.priceFrom;
        }
    }

    // Diện tích
    let area = '';
    if (project.areaFrom || project.areaTo) {
        if (project.areaFrom && project.areaTo) {
            area = project.areaFrom + 'm² - ' + project.areaTo + 'm²';
        } else if (project.areaFrom) {
            area = 'Từ ' + project.areaFrom + 'm²';
        } else if (project.areaTo) {
            area = 'Đến ' + project.areaTo + 'm²';
        }
    }

    // Trạng thái
    const rawStatus = project.status || '';
    let statusClass;
    switch(rawStatus) {
        case 'PLANNING': statusClass = 'status-planning'; break;
        case 'UNDER_CONSTRUCTION':
        case 'CONSTRUCTION': statusClass = 'status-construction'; break;
        case 'COMPLETED': statusClass = 'status-completed'; break;
        case 'HANDOVER': statusClass = 'status-handover'; break;
        case 'SELLING': statusClass = 'status-selling'; break;
        default: statusClass = 'status-unknown';
    }
    const statusDisplay = project.statusDisplay || rawStatus || 'Chưa có trạng thái';

    // Loại dự án
    const typeDisplay = project.projectTypeDisplay || project.projectType || 'Chưa phân loại';

    // Chủ đầu tư
    const developer = project.developer || {};
    const developerName = developer.name || project.developerName || '';
    const developerPhone = developer.phone || project.developerPhone || '';
    const developerEmail = developer.email || project.developerEmail || '';
    const developerWebsite = developer.website || project.developerWebsite || '';

    // Tạo HTML content
    let content = '<div class="row"><div class="col-12">';
    
    // Tiêu đề
    content += '<h5 class="mb-3" style="color:#333;font-weight:600;">' + projectName + '</h5>';
    
    // Địa chỉ
    content += '<div class="mb-3">';
    content += '<i class="fas fa-map-marker-alt me-2" style="color:#666;"></i>';
    content += '<span style="color:#666;">' + fullAddress + '</span>';
    content += '</div>';
    
    // Badges
    content += '<div class="mb-3">';
    content += '<span class="type-badge me-2">' + typeDisplay + '</span>';
    content += '<span class="status-badge ' + statusClass + '">' + statusDisplay + '</span>';
    content += '</div>';
    
    // Giá - không format
    content += '<div class="price-display mb-3">' + price + '</div>';

    // Diện tích
    if (area) {
        content += '<div class="mb-2">';
        content += '<i class="fas fa-expand me-2" style="color:#666;"></i>';
        content += '<span style="color:#666;">Diện tích: ' + area + '</span>';
        content += '</div>';
    }

    // Tổng số căn - không format
    if (project.totalUnits && project.totalUnits !== null && project.totalUnits !== 'null') {
        content += '<div class="mb-2">';
        content += '<i class="fas fa-building me-2" style="color:#666;"></i>';
        content += '<span style="color:#666;">Tổng số căn: ' + project.totalUnits + '</span>';
        content += '</div>';
    }

    // Căn còn lại - không format
    if (project.availableUnits !== undefined && project.availableUnits !== null && project.availableUnits !== 'null') {
        content += '<div class="mb-2">';
        content += '<i class="fas fa-home me-2" style="color:#666;"></i>';
        content += '<span style="color:#666;">Căn còn lại: ' + project.availableUnits + '</span>';
        content += '</div>';
    }

    // Năm bàn giao
    const handoverYear = project.handoverYear || project.completionYear;
    if (handoverYear && handoverYear !== null && handoverYear !== 'null') {
        content += '<div class="mb-2">';
        content += '<i class="fas fa-calendar me-2" style="color:#666;"></i>';
        content += '<span style="color:#666;">Năm bàn giao: ' + handoverYear + '</span>';
        content += '</div>';
    }

    // Mô tả
    if (project.description && project.description !== 'null' && project.description !== null) {
        const shortDesc = project.description.length > 150 
            ? project.description.substring(0, 150) + '...' 
            : project.description;
        content += '<div class="mb-3 p-3" style="background:#f8f9fa;border-radius:8px;">';
        content += '<small style="color:#666;line-height:1.4;">' + shortDesc + '</small>';
        content += '</div>';
    }

    // Button xem chi tiết - sử dụng contextPath từ JavaScript thay vì JSP
    content += '<div class="d-grid gap-2 mb-3">';
    content += '<a href="' + contextPath + '/projects/' + (project.slug || project.id) + '" ';
    content += 'class="btn btn-primary" style="border-radius:8px;">';
    content += '<i class="fas fa-eye me-1"></i>Xem chi tiết dự án';
    content += '</a>';
    content += '</div>';

    // Chủ đầu tư
    if (developerName && developerName !== 'null' && developerName !== null) {
        content += '<div class="border-top pt-3" style="border-color:#dee2e6!important;">';
        content += '<h6 style="color:#333;font-weight:600;margin-bottom:10px;">';
        content += '<i class="fas fa-building me-2"></i>Chủ đầu tư';
        content += '</h6>';
        content += '<div style="color:#666;">';
        content += '<strong>' + developerName + '</strong>';
        
        if (developerPhone && developerPhone !== 'null') {
            content += '<br><i class="fas fa-phone me-1"></i>';
            content += '<a href="tel:' + developerPhone + '" style="color:#0066cc;">' + developerPhone + '</a>';
        }
        
        if (developerEmail && developerEmail !== 'null') {
            content += '<br><i class="fas fa-envelope me-1"></i>';
            content += '<a href="mailto:' + developerEmail + '" style="color:#0066cc;">' + developerEmail + '</a>';
        }
        
        if (developerWebsite && developerWebsite !== 'null') {
            content += '<br><i class="fas fa-globe me-1"></i>';
            content += '<a href="' + developerWebsite + '" target="_blank" style="color:#0066cc;">Website</a>';
        }
        
        content += '</div></div>';
    }

    content += '</div></div>';

    // Render
    const contentElement = document.getElementById('projectInfoContent');
    const panelElement = document.getElementById('projectInfoPanel');

    if (contentElement && panelElement) {
        contentElement.innerHTML = content;
        panelElement.classList.add('active');
    } 
}

    // Close project panel
    function closeProjectPanel() {
        document.getElementById('projectInfoPanel').classList.remove('active');
        if (infoWindow) {
            infoWindow.close();
        }
    }

    // Apply filters
    function applyFilters() {
        loadProjectsInBounds();
    }

    // Clear filters
    function clearFilters() {
        $('#typeFilter').val('');
        $('#wardFilter').val('');
        $('#statusFilter').val('');
        loadProjects();
    }

    function showLoading() {
        const loadingElement = document.getElementById('loadingOverlay');
        if (loadingElement) {
            loadingElement.style.display = 'flex';
        }
    }

    function hideLoading() {
        const loadingElement = document.getElementById('loadingOverlay');
        if (loadingElement) {
            loadingElement.style.display = 'none';
        }
    }

    // Initialize when DOM is ready
    $(document).ready(function() {
        // Auto-apply filters on change
        $('#typeFilter, #wardFilter, #statusFilter').on('change', function() {
            applyFilters();
        });
    });