<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="pageTitle" value="Danh sách dự án - District 7 BDS" />

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/projectList.css">
</head>
<body>

	<%@ include file="../layout/header.jsp" %>
    <!-- Page Header -->
    <div class="page-header">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h1 class="display-4 mb-3">Danh sách dự án</h1>
                    <p class="lead mb-0">Khám phá các dự án bất động sản cao cấp tại Quận 7</p>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <!-- Filter Section -->
        <div class="filter-section">
            <form id="filterForm" method="GET">
                <div class="row g-3">
                    <div class="col-md-6 col-lg-3">
                        <label class="form-label fw-bold">Loại dự án</label>
                        <select class="form-select" name="type" id="typeFilter">
                            <option value="">Tất cả loại</option>
                            <option value="APARTMENT" ${param.type == 'APARTMENT' ? 'selected' : ''}>Căn hộ</option>
                            <option value="VILLA" ${param.type == 'VILLA' ? 'selected' : ''}>Biệt thự</option>
                            <option value="TOWNHOUSE" ${param.type == 'TOWNHOUSE' ? 'selected' : ''}>Nhà phố</option>
                            <option value="OFFICE" ${param.type == 'OFFICE' ? 'selected' : ''}>Văn phòng</option>
                            <option value="COMMERCIAL" ${param.type == 'COMMERCIAL' ? 'selected' : ''}>Thương mại</option>
                        </select>
                    </div>
                    
                    <div class="col-md-6 col-lg-3">
                        <label class="form-label fw-bold">Phường/Xã</label>
                        <select class="form-select" name="ward" id="wardFilter">
                            <option value="">Tất cả phường</option>
                            <option value="Phường Tân Thuận Đông" ${param.ward == 'Phường Tân Thuận Đông' ? 'selected' : ''}>Phường Tân Thuận Đông</option>
                            <option value="Phường Tân Thuận Tây" ${param.ward == 'Phường Tân Thuận Tây' ? 'selected' : ''}>Phường Tân Thuận Tây</option>
                            <option value="Phường Tân Kiểng" ${param.ward == 'Phường Tân Kiểng' ? 'selected' : ''}>Phường Tân Kiểng</option>
                            <option value="Phường Tân Hưng" ${param.ward == 'Phường Tân Hưng' ? 'selected' : ''}>Phường Tân Hưng</option>
                            <option value="Phường Bình Thuận" ${param.ward == 'Phường Bình Thuận' ? 'selected' : ''}>Phường Bình Thuận</option>
                            <option value="Phường Tân Phong" ${param.ward == 'Phường Tân Phong' ? 'selected' : ''}>Phường Tân Phong</option>
                            <option value="Phường Tân Phú" ${param.ward == 'Phường Tân Phú' ? 'selected' : ''}>Phường Tân Phú</option>
                            <option value="Phường Tân Quy" ${param.ward == 'Phường Tân Quy' ? 'selected' : ''}>Phường Tân Quy</option>
                            <option value="Phường Phú Thuận" ${param.ward == 'Phường Phú Thuận' ? 'selected' : ''}>Phường Phú Thuận</option>
                            <option value="Phường Phú Mỹ" ${param.ward == 'Phường Phú Mỹ' ? 'selected' : ''}>Phường Phú Mỹ</option>
                        </select>
                    </div>
                    
                    <div class="col-md-6 col-lg-2">
                        <label class="form-label fw-bold">Trạng thái</label>
                        <select class="form-select" name="status" id="statusFilter">
                            <option value="">Tất cả</option>
                            <option value="PLANNING" ${param.status == 'PLANNING' ? 'selected' : ''}>Quy hoạch</option>
                            <option value="CONSTRUCTION" ${param.status == 'CONSTRUCTION' ? 'selected' : ''}>Đang xây</option>
                            <option value="COMPLETED" ${param.status == 'COMPLETED' ? 'selected' : ''}>Hoàn thành</option>
                            <option value="HANDOVER" ${param.status == 'HANDOVER' ? 'selected' : ''}>Bàn giao</option>
                        </select>
                    </div>
                    
                    <div class="col-md-6 col-lg-2">
                        <label class="form-label fw-bold">Mức giá</label>
                        <select class="form-select" name="priceRange" id="priceFilter">
                            <option value="">Tất cả</option>
                            <option value="under-2" ${param.priceRange == 'under-2' ? 'selected' : ''}>Dưới 2 tỷ</option>
                            <option value="2-5" ${param.priceRange == '2-5' ? 'selected' : ''}>2 - 5 tỷ</option>
                            <option value="5-10" ${param.priceRange == '5-10' ? 'selected' : ''}>5 - 10 tỷ</option>
                            <option value="above-10" ${param.priceRange == 'above-10' ? 'selected' : ''}>Trên 10 tỷ</option>
                        </select>
                    </div>
                    
                    <div class="col-lg-2 d-flex align-items-end">
                        <div class="w-100">
                            <button type="submit" class="btn btn-primary w-100 me-2">
                                <i class="fas fa-search me-1"></i>Lọc
                            </button>
                        </div>
                    </div>
                </div>
                
                <!-- Search bar -->
                <div class="row mt-3">
                    <div class="col-md-8">
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" 
                                   value="${param.search}" placeholder="Tìm kiếm theo tên dự án...">
                            <button class="btn btn-outline-primary" type="submit">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-md-4 text-end">
                        <a href="${pageContext.request.contextPath}/projects" class="btn btn-outline-secondary">
                            <i class="fas fa-times me-1"></i>Xóa bộ lọc
                        </a>
                    </div>
                </div>
            </form>
            
            <!-- Active filters display -->
            <c:if test="${not empty param.type or not empty param.ward or not empty param.status or not empty param.priceRange or not empty param.search}">
                <div class="mt-3">
                    <small class="text-muted me-2">Bộ lọc đang áp dụng:</small>
                    <c:if test="${not empty param.type}">
                        <span class="filter-tag">
                            Loại: ${param.type}
                            <span class="remove-filter" onclick="removeFilter('type')">&times;</span>
                        </span>
                    </c:if>
                    <c:if test="${not empty param.ward}">
                        <span class="filter-tag">
                            Phường: ${param.ward}
                            <span class="remove-filter" onclick="removeFilter('ward')">&times;</span>
                        </span>
                    </c:if>
                    <c:if test="${not empty param.status}">
                        <span class="filter-tag">
                            Trạng thái: ${param.status}
                            <span class="remove-filter" onclick="removeFilter('status')">&times;</span>
                        </span>
                    </c:if>
                    <c:if test="${not empty param.priceRange}">
                        <span class="filter-tag">
                            Giá: ${param.priceRange}
                            <span class="remove-filter" onclick="removeFilter('priceRange')">&times;</span>
                        </span>
                    </c:if>
                    <c:if test="${not empty param.search}">
                        <span class="filter-tag">
                            Từ khóa: "${param.search}"
                            <span class="remove-filter" onclick="removeFilter('search')">&times;</span>
                        </span>
                    </c:if>
                </div>
            </c:if>
        </div>

        <!-- Results Header -->
        <div class="search-result-header">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <h5 class="mb-0">
                        Tìm thấy <strong>${not empty projects ? fn:length(projects) : 0}</strong> dự án
                        <c:if test="${not empty param.search}">
                            cho từ khóa "<strong>${param.search}</strong>"
                        </c:if>
                    </h5>
                </div>
                <div class="col-md-6">
                    <div class="sort-options">
                        <label class="form-label mb-0 me-2">Sắp xếp:</label>
                        <select class="form-select form-select-sm w-auto" name="sort" id="sortSelect" onchange="applySorting()">
                            <option value="newest" ${param.sort == 'newest' ? 'selected' : ''}>Mới nhất</option>
                            <option value="oldest" ${param.sort == 'oldest' ? 'selected' : ''}>Cũ nhất</option>
                            <option value="price-asc" ${param.sort == 'price-asc' ? 'selected' : ''}>Giá tăng dần</option>
                            <option value="price-desc" ${param.sort == 'price-desc' ? 'selected' : ''}>Giá giảm dần</option>
                            <option value="popular" ${param.sort == 'popular' ? 'selected' : ''}>Phổ biến</option>
                        </select>
                        
                        <div class="view-mode-toggle ms-3">
                            <button class="view-mode-btn active" id="gridViewBtn" onclick="setViewMode('grid')">
                                <i class="fas fa-th"></i>
                            </button>
                            <button class="view-mode-btn" id="listViewBtn" onclick="setViewMode('list')">
                                <i class="fas fa-list"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Projects Grid -->
        <c:choose>
            <c:when test="${not empty projects}">
                <div class="row g-4" id="projectsGrid">
                    <c:forEach var="project" items="${projects}">
                        <div class="col-lg-4 col-md-6">
                            <div class="project-card">
                                <c:set var="projectImageUrl" 
       value="${not empty project.thumbnail 
               ? pageContext.request.contextPath.concat(project.thumbnail) 
               : pageContext.request.contextPath.concat('/images/default-project.jpg')}" />

<div class="project-image" 
     style="background-image: url('${projectImageUrl}')">
    <div class="project-badges">
        <c:if test="${project.isFeatured}">
            <span class="badge-featured">
                <i class="fas fa-star me-1"></i>Nổi bật
            </span>
        </c:if>
        <c:if test="${project.isHot}">
            <span class="badge-hot">
                <i class="fas fa-fire me-1"></i>Hot
            </span>
        </c:if>
    </div>
</div>

                                
                                <div class="project-content">
                                    <div class="project-type">${project.projectType.displayName}</div>
                                    
                                    <h5 class="project-title">
                                        <a href="${pageContext.request.contextPath}/projects/${project.slug}">
                                            ${project.name}
                                        </a>
                                    </h5>
                                    
                                    <div class="project-location">
                                        <i class="fas fa-map-marker-alt me-2"></i>
                                        ${project.address}, ${project.ward}
                                    </div>
                                    
                                    <div class="project-price">
                                        <c:choose>
                                            <c:when test="${project.priceFrom != null and project.priceTo != null}">
                                                <fmt:formatNumber value="${project.priceFrom / 1000000000}" type="currency" currencySymbol="" pattern="#,###"/> - 
                                                <fmt:formatNumber value="${project.priceTo / 1000000000}" type="currency" currencySymbol="" pattern="#,###"/> tỷ
                                            </c:when>
                                            <c:when test="${project.priceFrom != null}">
                                                Từ <fmt:formatNumber value="${project.priceFrom / 1000000000}" type="currency" currencySymbol="" pattern="#,###"/> tỷ
                                            </c:when>
                                            <c:otherwise>
                                                Liên hệ
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    
                                    <div class="project-info">
                                        <span>
                                            <c:if test="${project.areaFrom != null}">
                                                <i class="fas fa-expand me-1"></i>
                                                Từ <fmt:formatNumber value="${project.areaFrom}" pattern="#,###"/>m²
                                            </c:if>
                                        </span>
                                        <span class="project-status status-${fn:toLowerCase(project.status)}">
                                            ${project.status.displayName}
                                        </span>
                                    </div>
                                    
                                    <div class="project-actions">
                                        <span class="view-count">
                                            <i class="fas fa-eye me-1"></i>${project.viewCount} lượt xem
                                        </span>
                                        <a href="${pageContext.request.contextPath}/projects/${project.slug}" 
                                           class="btn-view-detail" style="text-decoration: none;">
                                            Chi tiết
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                
                <!-- Pagination -->
                <c:if test="${totalPages > 1}">
                    <nav aria-label="Projects pagination" class="pagination">
                        <ul class="pagination">
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?${pageContext.request.queryString}&page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            
                            <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="?${pageContext.request.queryString}&page=${pageNum}">${pageNum}</a>
                                </li>
                            </c:forEach>
                            
                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="?${pageContext.request.queryString}&page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>
            </c:when>
            <c:otherwise>
                <div class="no-results">
                    <i class="fas fa-search fa-3x text-muted mb-3"></i>
                    <h4>Không tìm thấy dự án nào</h4>
                    <p class="text-muted mb-4">
                        Thử thay đổi bộ lọc hoặc từ khóa tìm kiếm để có kết quả tốt hơn
                    </p>
                    <a href="${pageContext.request.contextPath}/projects" class="btn btn-primary">
                        <i class="fas fa-refresh me-2"></i>Xem tất cả dự án
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Footer -->
    <footer class="footer mt-5" style="background-color: #1a202c; color: #e2e8f0; padding: 3rem 0 2rem;">
        <div class="container">
            <div class="row">
                <div class="col-md-4 mb-4">
                    <h5 class="text-white mb-3">
                        <i class="fas fa-building me-2"></i>District 7 BDS
                    </h5>
                    <p>Hệ thống quản lý và tìm kiếm bất động sản tại Quận 7, TP.HCM. 
                       Chuyên cung cấp thông tin về các dự án căn hộ, biệt thự, nhà phố cao cấp.</p>
                </div>
                
                <div class="col-md-4 mb-4">
                    <h5 class="text-white mb-3">Liên kết nhanh</h5>
                    <ul class="list-unstyled">
                        <li><a href="${pageContext.request.contextPath}/projects/featured" class="text-light text-decoration-none">
                            <i class="fas fa-star me-2"></i>Dự án nổi bật
                        </a></li>
                        <li><a href="${pageContext.request.contextPath}/projects/hot" class="text-light text-decoration-none">
                            <i class="fas fa-fire me-2"></i>Dự án hot
                        </a></li>
                        <li><a href="${pageContext.request.contextPath}/map" class="text-light text-decoration-none">
                            <i class="fas fa-map-marked-alt me-2"></i>Bản đồ dự án
                        </a></li>
                    </ul>
                </div>
                
                <div class="col-md-4 mb-4">
                    <h5 class="text-white mb-3">Thông tin liên hệ</h5>
                    <p><i class="fas fa-map-marker-alt me-2"></i>Quận 7, TP.HCM</p>
                    <p><i class="fas fa-phone me-2"></i>0901 234 567</p>
                    <p><i class="fas fa-envelope me-2"></i>info@district7bds.com</p>
                </div>
            </div>
            
            <hr class="my-4" style="border-color: #4a5568;">
            
            <div class="row align-items-center">
                <div class="col-md-6">
                    <p class="mb-0">&copy; 2024 District 7 BDS. All rights reserved.</p>
                </div>
                <div class="col-md-6 text-md-end">
                    <p class="mb-0">Phát triển bởi Spring MVC Team</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/projectList.js"></script>
</body>
</html>