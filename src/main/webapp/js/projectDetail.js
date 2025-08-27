document.addEventListener('DOMContentLoaded', function() {
    const contactForm = document.getElementById('contactForm');
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();

            // Log contextPath value
            console.log('contextPath value:', typeof contextPath !== 'undefined' ? contextPath : 'UNDEFINED');

            const formData = new FormData(this);
            
            // Log all form data
            console.log('=== FORM DATA ===');
            for (let [key, value] of formData.entries()) {
                console.log(`${key}: "${value}"`);
            }

            // Basic validation
            const fullName = formData.get('fullName');
            const phone = formData.get('phone');
            const projectId = formData.get('projectId');
            
            if (!fullName || !phone) {
                alert('Vui lòng điền đầy đủ thông tin bắt buộc!');
                return;
            }

            if (!projectId) {
                console.error('Missing projectId:', projectId);
                alert('Thiếu thông tin dự án!');
                return;
            }

            const submitButton = this.querySelector('button[type="submit"]');
            const originalText = submitButton.innerHTML;
            submitButton.disabled = true;
            submitButton.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Đang gửi...';

            // HARDCODE URL để test
            const endpoint = contextPath + '/api/contact/submit';
            console.log('=== SENDING TO ===');
            console.log('Endpoint:', endpoint);

            fetch(endpoint, {
                method: 'POST',
                body: formData,
                headers: {
                    'X-Requested-With': 'XMLHttpRequest'
                }
            })
            .then(response => {
                console.log('=== RESPONSE STATUS ===');
                console.log('Status:', response.status);
                console.log('URL:', response.url);
                
                if (!response.ok) {
                    throw new Error(`HTTP ${response.status}: ${response.statusText}`);
                }
                
                return response.text();
            })
            .then(text => {
                console.log('=== RAW RESPONSE ===');
                console.log('Response text:', text);
                
                try {
                    const data = JSON.parse(text);
                    console.log('=== PARSED JSON ===');
                    console.log('Data:', data);
                    
                    if (data.success) {
                        alert('Cảm ơn bạn đã liên hệ . Chúng tôi sẽ phản hồi trong thời gian sớm nhất!!');
                        this.reset();
                    } else {
                        alert('Lỗi: ' + (data.message || 'Có lỗi xảy ra'));
                    }
                } catch (e) {
                    console.error('JSON parse error:', e);
                    alert('Server trả về response không phải JSON: ' + text);
                }
            })
            .catch(error => {
                console.error('=== FETCH ERROR ===');
                console.error('Error:', error);
                alert('Lỗi kết nối: ' + error.message);
            })
            .finally(() => {
                submitButton.disabled = false;
                submitButton.innerHTML = originalText;
            });
        });
    }
});
// Gallery Functions
function changeMainImage(imageUrl, thumbnail) {
	const mainImage = document.getElementById('mainImage');
	const mainImageLink = document.querySelector('.main-image-link');

	if (mainImage && mainImageLink) {
		// Update background image
		mainImage.style.backgroundImage = `url('${imageUrl}')`;

		// Update lightbox link href
		mainImageLink.href = imageUrl;

		// Update active thumbnail
		document.querySelectorAll('.thumbnail').forEach(t => t.classList.remove('active'));
		if (thumbnail) {
			thumbnail.classList.add('active');
		}

		// Add smooth transition effect
		mainImage.style.opacity = '0.7';
		setTimeout(() => {
			mainImage.style.opacity = '1';
		}, 150);
	}
}

// Open lightbox gallery
function openLightboxGallery() {
	// Tìm link lightbox đầu tiên (main image)
	const firstLightboxLink = document.querySelector('[data-lightbox="project-gallery"]');
	if (firstLightboxLink) {
		firstLightboxLink.click();
	} else {
		console.error('Không tìm thấy lightbox link');
	}
}

// Scroll thumbnails horizontally
function scrollThumbnails(direction) {
	const container = document.querySelector('.gallery-thumbnails');
	if (!container) {
		console.error('Không tìm thấy gallery thumbnails container');
		return;
	}

	const scrollAmount = 200;
	const currentScroll = container.scrollLeft;

	if (direction === 'left') {
		container.scrollTo({
			left: Math.max(0, currentScroll - scrollAmount),
			behavior: 'smooth'
		});
	} else {
		container.scrollTo({
			left: currentScroll + scrollAmount,
			behavior: 'smooth'
		});
	}
}

// Initialize gallery when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
	console.log('Gallery script loaded');

	// Auto-scroll thumbnails on mouse wheel
	const thumbnailContainer = document.querySelector('.gallery-thumbnails');
	if (thumbnailContainer) {
		thumbnailContainer.addEventListener('wheel', function(e) {
			e.preventDefault();
			this.scrollLeft += e.deltaY;
		});
	}

	// Kiểm tra xem Lightbox đã được load chưa
	if (typeof lightbox !== 'undefined') {
		// Cấu hình lightbox
		lightbox.option({
			'resizeDuration': 200,
			'wrapAround': true,
			'showImageNumberLabel': true
		});
		console.log('Lightbox initialized');
	} else {
		console.error('Lightbox library not loaded');
	}

	// Add debug info
	console.log('Gallery elements found:', {
		mainImage: !!document.getElementById('mainImage'),
		thumbnails: document.querySelectorAll('.thumbnail').length,
		lightboxLinks: document.querySelectorAll('[data-lightbox="project-gallery"]').length
	});
});

// Keyboard navigation for gallery
document.addEventListener('keydown', function(e) {
	if (e.key === 'ArrowLeft') {
		scrollThumbnails('left');
	} else if (e.key === 'ArrowRight') {
		scrollThumbnails('right');
	}
});

// Video Functions
function playVideo(videoUrl) {
	const modal = new bootstrap.Modal(document.getElementById('videoModal'));
	document.getElementById('videoIframe').src = videoUrl;
	modal.show();
}

document.addEventListener('DOMContentLoaded', function() {
	const videoModal = document.getElementById('videoModal');
	if (videoModal) {
		videoModal.addEventListener('hidden.bs.modal', function() {
			document.getElementById('videoIframe').src = '';
		});
	}
});

// Google Maps
let map;
let marker;

function initMap() {
	if (typeof projectData === 'undefined') {
		console.error('Project data not found');
		return;
	}

	const projectLocation = {
		lat: parseFloat(projectData.latitude),
		lng: parseFloat(projectData.longitude)
	};

	const mapElement = document.getElementById('projectMap');
	if (!mapElement) {
		console.error('Map element not found');
		return;
	}

	map = new google.maps.Map(mapElement, {
		zoom: 15,
		center: projectLocation,
		styles: [{ featureType: 'poi', elementType: 'labels', stylers: [{ visibility: 'off' }] }]
	});

	marker = new google.maps.Marker({
		position: projectLocation,
		map: map,
		title: projectData.name,
		animation: google.maps.Animation.DROP
	});

	const infoWindow = new google.maps.InfoWindow({
		content: `
            <div style="max-width: 300px;">
                <h6>${projectData.name}</h6>
                <p class="mb-2">${projectData.address}, ${projectData.ward}</p>
                <div class="text-danger fw-bold">
                    ${projectData.priceFrom ? 'Từ ' + (projectData.priceFrom / 1000000000).toFixed(2) + ' tỷ' : 'Liên hệ'}
                </div>
            </div>
        `
	});

	marker.addListener('click', function() {
		infoWindow.open(map, marker);
	});
}

