<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<c:set var="pageTitle" value="${project.name} - District 7 BDS" />

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${pageTitle}</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">

<!-- Lightbox CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.4/css/lightbox.min.css" rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/projectDetail.css">
</head>
<body>
	<%@ include file="../layout/header.jsp"%>

	<div class="container py-4">
		<!-- Breadcrumb -->
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/">Trang chủ</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/projects">Dự án</a></li>
				<li class="breadcrumb-item active" aria-current="page">${project.name}</li>
			</ol>
		</nav>

		<!-- Project Header -->
		<div class="project-header">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
						<!-- Badges -->
						<div class="project-badges">
							<span class="project-type-badge">${project.projectType.displayName}</span>
							<c:if test="${project.isFeatured}">
								<span class="badge-featured"> <i class="fas fa-star me-1"></i>Nổi
									bật
								</span>
							</c:if>
							<c:if test="${project.isHot}">
								<span class="badge-hot"> <i class="fas fa-fire me-1"></i>Hot
								</span>
							</c:if>
						</div>

						<!-- Project Title -->
						<h1 class="project-title">${project.name}</h1>

						<!-- Location -->
						<div class="project-location">
							<i class="fas fa-map-marker-alt me-2"></i> ${project.address},
							${project.ward}, Quận 7, TP.HCM
						</div>

						<!-- Status -->
						<div class="mb-3">
							<span
								class="project-status status-${fn:toLowerCase(project.status)}">
								<i class="fas fa-info-circle me-1"></i>
								${project.status.displayName}
							</span>
						</div>

						<!-- Statistics -->
						<div class="project-stats">
							<div class="stat-item">
								<span class="stat-number">${project.viewCount}</span> <span
									class="stat-label">Lượt xem</span>
							</div>
							<c:if test="${project.totalUnits != null}">
								<div class="stat-item">
									<span class="stat-number">${project.totalUnits}</span> <span
										class="stat-label">Tổng số căn</span>
								</div>
							</c:if>
							<c:if test="${project.availableUnits != null}">
								<div class="stat-item">
									<span class="stat-number">${project.availableUnits}</span> <span
										class="stat-label">Căn còn lại</span>
								</div>
							</c:if>
							<c:if test="${project.completionYear != null}">
								<div class="stat-item">
									<span class="stat-number">${project.completionYear}</span> <span
										class="stat-label">Năm hoàn thành</span>
								</div>
							</c:if>
						</div>
					</div>

					<div class="col-lg-4">
						<!-- Price -->
						<div class="project-price">
							<c:choose>
								<c:when
									test="${project.priceFrom != null and project.priceTo != null}">
									<fmt:formatNumber value="${project.priceFrom / 1000000000}"
										pattern="#,###.##" /> - 
            <fmt:formatNumber value="${project.priceTo / 1000000000}"
										pattern="#,###.##" /> tỷ
        </c:when>
								<c:when test="${project.priceFrom != null}">
            Từ <fmt:formatNumber
										value="${project.priceFrom / 1000000000}" pattern="#,###.##" /> tỷ
        </c:when>
								<c:otherwise>
                                    Liên hệ
                                </c:otherwise>
							</c:choose>
						</div>

						<!-- Quick Actions -->
						<div class="d-grid gap-2">
							<button class="btn btn-primary" data-bs-toggle="modal"
								data-bs-target="#contactModal">
								<i class="fas fa-phone me-2"></i>Liên hệ tư vấn
							</button>
							<a
								href="${pageContext.request.contextPath}/map?project=${project.id}"
								class="btn btn-outline-primary"> <i
								class="fas fa-map-marked-alt me-2"></i>Xem trên bản đồ
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-8">
				<!-- Image Gallery -->
				<!-- Image Gallery -->
				<div class="project-gallery">
					<h3 class="section-title">
						<i class="fas fa-images"></i>Hình ảnh dự án
						<c:if test="${not empty projectImages}">
							<span class="badge bg-primary ms-2">${fn:length(projectImages) + 1}
								ảnh</span>
						</c:if>
					</h3>

					<!-- Main Image -->
					<c:set var="mainImageUrl"
						value="${not empty project.thumbnail 
               ? pageContext.request.contextPath.concat(project.thumbnail) 
               : pageContext.request.contextPath.concat('/images/default-project.jpg')}" />

					<!-- Sửa lại main image để có lightbox link trực tiếp -->
					<a href="${mainImageUrl}" data-lightbox="project-gallery"
						data-title="${project.name}" class="main-image-link">
						<div class="main-image" id="mainImage"
							style="background-image: url('${mainImageUrl}')">
							<div class="image-overlay">
								<i class="fas fa-expand-alt"></i> <span class="overlay-text">Click
									để xem full size</span>
							</div>
						</div>
					</a>

					<!-- Thumbnails -->
					<c:if test="${not empty projectImages}">
						<div class="gallery-thumbnails">
							<!-- Main thumbnail -->
							<div class="thumbnail active"
								style="background-image: url('${mainImageUrl}')"
								onclick="changeMainImage('${mainImageUrl}', this)"
								title="Ảnh chính">
								<div class="thumbnail-overlay">
									<i class="fas fa-image"></i>
								</div>
							</div>

							<!-- Project images thumbnails với lightbox links -->
							<c:forEach var="image" items="${projectImages}"
								varStatus="status">
								<c:set var="thumbUrl"
									value="${pageContext.request.contextPath}${image.imageUrl}" />
								<div class="thumbnail"
									style="background-image: url('${thumbUrl}')"
									onclick="changeMainImage('${thumbUrl}', this)"
									title="${not empty image.caption ? image.caption : 'Ảnh '.concat(status.index + 1)}">

									<div class="thumbnail-overlay">
										<i class="fas fa-image"></i>
									</div>

									<c:if test="${not empty image.caption}">
										<div class="thumbnail-caption">${image.caption}</div>
									</c:if>

									<!-- Image type indicator -->
									<c:if test="${image.imageType != null}">
										<div class="image-type-badge">
											<c:choose>
												<c:when test="${image.imageType.name() == 'EXTERIOR'}">
													<i class="fas fa-building" title="Ngoại thất"></i>
												</c:when>
												<c:when test="${image.imageType.name() == 'INTERIOR'}">
													<i class="fas fa-home" title="Nội thất"></i>
												</c:when>
												<c:when test="${image.imageType.name() == 'AMENITIES'}">
													<i class="fas fa-star" title="Tiện ích"></i>
												</c:when>
												<c:when test="${image.imageType.name() == 'FLOOR_PLAN'}">
													<i class="fas fa-map" title="Mặt bằng"></i>
												</c:when>
												<c:otherwise>
													<i class="fas fa-image" title="Khác"></i>
												</c:otherwise>
											</c:choose>
										</div>
									</c:if>

									<!-- Hidden lightbox link cho từng ảnh -->
									<a href="${thumbUrl}" data-lightbox="project-gallery"
										data-title="${not empty image.caption ? image.caption : project.name}"
										style="display: none;" class="lightbox-link"></a>
								</div>
							</c:forEach>
						</div>

						<!-- Gallery actions -->
						<div class="gallery-actions mt-3">
							<button class="btn btn-outline-primary btn-sm me-2"
								onclick="openLightboxGallery()">
								<i class="fas fa-expand me-1"></i>Xem slideshow
							</button>
							<button class="btn btn-outline-secondary btn-sm"
								onclick="scrollThumbnails('left')">
								<i class="fas fa-chevron-left"></i>
							</button>
							<button class="btn btn-outline-secondary btn-sm"
								onclick="scrollThumbnails('right')">
								<i class="fas fa-chevron-right"></i>
							</button>
						</div>
					</c:if>

					<c:if test="${empty projectImages}">
						<div class="no-images-message text-center text-muted py-3">
							<i class="fas fa-images fa-2x mb-2"></i>
							<p>Chưa có thêm hình ảnh cho dự án này</p>
						</div>
					</c:if>
				</div>
				<!-- Video Section -->
				<c:if test="${not empty project.videoUrl}">
					<div class="info-section">
						<h3 class="section-title">
							<i class="fas fa-play"></i>Video giới thiệu
						</h3>

						<div class="video-section">
							<div class="video-thumbnail"
								style="background-image: url('${project.thumbnail}')">
								<div class="play-button"
									onclick="playVideo('${project.videoUrl}')">
									<i class="fas fa-play"></i>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<!-- Project Information -->
				<div class="info-section">
					<h3 class="section-title">
						<i class="fas fa-info-circle"></i>Thông tin dự án
					</h3>

					<div class="info-grid">
						<div class="info-item">
							<div class="info-icon">
								<i class="fas fa-building"></i>
							</div>
							<div class="info-content">
								<h6>Loại dự án</h6>
								<span>${project.projectType.displayName}</span>
							</div>
						</div>

						<div class="info-item">
							<div class="info-icon">
								<i class="fas fa-map-marker-alt"></i>
							</div>
							<div class="info-content">
								<h6>Vị trí</h6>
								<span>${project.ward}, Quận 7</span>
							</div>
						</div>

						<c:if test="${project.areaFrom != null or project.areaTo != null}">
							<div class="info-item">
								<div class="info-icon">
									<i class="fas fa-expand"></i>
								</div>
								<div class="info-content">
									<h6>Diện tích</h6>
									<span> <c:choose>
											<c:when
												test="${project.areaFrom != null and project.areaTo != null}">
												<fmt:formatNumber value="${project.areaFrom}"
													pattern="#,###" />m² - 
                                                <fmt:formatNumber
													value="${project.areaTo}" pattern="#,###" />m²
                                            </c:when>
											<c:when test="${project.areaFrom != null}">
                                                Từ <fmt:formatNumber
													value="${project.areaFrom}" pattern="#,###" />m²
                                            </c:when>
										</c:choose>
									</span>
								</div>
							</div>
						</c:if>

						<c:if test="${project.totalUnits != null}">
							<div class="info-item">
								<div class="info-icon">
									<i class="fas fa-home"></i>
								</div>
								<div class="info-content">
									<h6>Tổng số căn</h6>
									<span><fmt:formatNumber value="${project.totalUnits}"
											pattern="#,###" /> căn</span>
								</div>
							</div>
						</c:if>

						<c:if test="${project.handoverYear != null}">
							<div class="info-item">
								<div class="info-icon">
									<i class="fas fa-calendar"></i>
								</div>
								<div class="info-content">
									<h6>Năm bàn giao</h6>
									<span>${project.handoverYear}</span>
								</div>
							</div>
						</c:if>

						<c:if test="${project.developer != null}">
							<div class="info-item">
								<div class="info-icon">
									<i class="fas fa-building"></i>
								</div>
								<div class="info-content">
									<h6>Chủ đầu tư</h6>
									<span>${project.developer.name}</span>
								</div>
							</div>
						</c:if>
					</div>
				</div>

				<!-- Description -->
				<c:if test="${not empty project.description}">
					<div class="info-section">
						<h3 class="section-title">
							<i class="fas fa-align-left"></i>Mô tả dự án
						</h3>

						<div class="description-text">
							${fn:replace(fn:escapeXml(project.description), newLineChar, '<br/>')}
						</div>
					</div>
				</c:if>

				<!-- Amenities -->
				<c:if test="${not empty project.amenities}">
					<div class="info-section">
						<h3 class="section-title">
							<i class="fas fa-star"></i>Tiện ích dự án
						</h3>

						<div class="amenities-grid">
							<c:forTokens items="${project.amenities}" delims=","
								var="amenity">
								<div class="amenity-item">
									<i class="fas fa-check-circle text-success me-2"></i>
									${fn:trim(amenity)}
								</div>
							</c:forTokens>
						</div>
					</div>
				</c:if>

				<!-- Location Map -->
				<div class="info-section">
					<h3 class="section-title">
						<i class="fas fa-map"></i>Vị trí dự án
					</h3>

					<div class="location-map" id="projectMap"></div>
				</div>

				<!-- Related Projects -->
				<c:if test="${not empty similarProjects}">
					<div class="related-projects">
						<h3 class="section-title">
							<i class="fas fa-th-large"></i>Dự án tương tự
						</h3>

						<div class="row">
							<c:forEach var="relatedProject" items="${similarProjects}">
								<div class="col-md-6 mb-3">
									<a
										href="${pageContext.request.contextPath}/projects/${relatedProject.slug}"
										class="text-decoration-none" style="display: block;">

										<div class="related-project-card">
											<c:set var="thumbUrl"
												value="${not empty relatedProject.thumbnail 
                ? pageContext.request.contextPath.concat(relatedProject.thumbnail) 
                : pageContext.request.contextPath.concat('/images/default-project.jpg')}" />

											<!-- Ảnh dự án -->
											<div class="related-project-image"
												style="background-image: url('${thumbUrl}')"></div>

											<!-- Nội dung -->
											<div class="related-project-content">
												<h6 class="mb-2 text-dark">${relatedProject.name}</h6>
												<p class="text-muted small mb-2">
													<i class="fas fa-map-marker-alt me-1"></i>
													${relatedProject.ward}, Quận 7
												</p>
												<div class="text-danger fw-bold">
													<c:choose>
														<c:when test="${relatedProject.priceFrom != null}">
                            Từ <fmt:formatNumber
																value="${relatedProject.priceFrom / 1000000000}"
																pattern="#,###.##" /> tỷ
                        </c:when>
														<c:otherwise>
                            Liên hệ
                        </c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>

			<!-- Sidebar -->
			<div class="col-lg-4">
				<!-- Contact Card -->
				<div class="contact-card mb-4">
					<h4 class="mb-3">
						<i class="fas fa-headset me-2"></i>Hỗ trợ tư vấn
					</h4>
					<p class="mb-3">Liên hệ với chúng tôi để được tư vấn miễn phí
						về dự án này</p>

					<div class="d-grid gap-2">
						<button class="btn btn-light" data-bs-toggle="modal"
							data-bs-target="#contactModal">
							<i class="fas fa-phone me-2"></i>Gọi tư vấn ngay
						</button>
						<a href="tel:0901234567" class="btn btn-outline-light"> <i
							class="fas fa-mobile-alt me-2"></i>0901 234 567
						</a>
					</div>
				</div>

				<!-- Developer Info -->
				<c:if test="${project.developer != null}">
					<div class="info-section">
						<h4 class="section-title">
							<i class="fas fa-building"></i>Chủ đầu tư
						</h4>

						<div class="text-center">
							<c:if test="${not empty project.developer.logoUrl}">
								<img
									src="${pageContext.request.contextPath}${project.developer.logoUrl}"
									alt="${project.developer.name}" class="img-fluid mb-3"
									style="max-height: 100px;">
							</c:if>

							<h5>${project.developer.name}</h5>

							<c:if test="${not empty project.developer.description}">
								<p class="text-muted small">${project.developer.description}</p>
							</c:if>

							<div class="mt-3">
								<c:if test="${not empty project.developer.phone}">
									<p class="mb-1">
										<i class="fas fa-phone me-2"></i> <a
											href="tel:${project.developer.phone}">${project.developer.phone}</a>
									</p>
								</c:if>

								<c:if test="${not empty project.developer.email}">
									<p class="mb-1">
										<i class="fas fa-envelope me-2"></i> <a
											href="mailto:${project.developer.email}">${project.developer.email}</a>
									</p>
								</c:if>

								<c:if test="${not empty project.developer.website}">
									<p class="mb-1">
										<i class="fas fa-globe me-2"></i> <a
											href="${project.developer.website}" target="_blank">Website</a>
									</p>
								</c:if>
							</div>
						</div>
					</div>
				</c:if>

				<!-- Contact Form -->
				<div class="contact-form">
					<h4 class="section-title">
						<i class="fas fa-envelope"></i>Liên hệ tư vấn
					</h4>

					<form id="contactForm" action="${pageContext.request.contextPath}/api/contact/submit" method="POST">
						<div class="mb-3">
							<label class="form-label">Họ tên <span
								class="text-danger">*</span></label> <input type="text"
								class="form-control" name="fullName"
								placeholder="Nhập họ tên của bạn" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Số điện thoại <span
								class="text-danger">*</span></label> <input type="tel"
								class="form-control" name="phone" placeholder="0901 234 567"
								required>
						</div>

						<div class="mb-3">
							<label class="form-label">Email</label> <input type="email"
								class="form-control" name="email"
								placeholder="email@example.com">
						</div>

						<div class="mb-3">
							<label class="form-label">Loại yêu cầu</label> <select
								class="form-select" name="requestType">
								<option value="CONSULTATION">Tư vấn</option>
								<option value="VIEWING">Xem nhà</option>
								<option value="PRICE_INQUIRY">Hỏi giá</option>
								<option value="OTHER">Khác</option>
							</select>
						</div>

						<div class="mb-3">
							<label class="form-label">Tin nhắn</label>
							<textarea class="form-control" name="message" rows="4"
								placeholder="Tôi quan tâm đến dự án ${project.name}. Xin vui lòng liên hệ tư vấn cho tôi..."></textarea>
						</div>

						<!-- Hidden fields -->
						<input type="hidden" name="projectId" value="${project.id}">

						<div class="d-grid">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-paper-plane me-2"></i>Gửi yêu cầu
							</button>
						</div>

						<div class="text-center mt-3">
							<small class="text-muted"> Hoặc gọi trực tiếp: <a
								href="tel:0901234567" class="text-primary fw-bold">0901 234
									567</a>
							</small>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Contact Modal -->
	<div class="modal fade" id="contactModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
						<i class="fas fa-phone me-2"></i>Liên hệ tư vấn
					</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<div class="text-center mb-4">
						<h6>${project.name}</h6>
						<p class="text-muted">${project.address},${project.ward}, Quận
							7</p>
					</div>

					<div class="row text-center">
						<div class="col-6">
							<a href="tel:0901234567" class="btn btn-primary w-100 mb-2">
								<i class="fas fa-phone me-2"></i> Gọi ngay<br> <small>0901
									234 567</small>
							</a>
						</div>
						<div class="col-6">
							<a href="mailto:info@district7bds.com"
								class="btn btn-outline-primary w-100 mb-2"> <i
								class="fas fa-envelope me-2"></i> Email<br> <small>info@district7bds.com</small>
							</a>
						</div>
					</div>

					<div class="text-center mt-3">
						<a href="https://zalo.me/0901234567" target="_blank"
							class="btn btn-info"> <i
							class="fab fa-facebook-messenger me-2"></i>Chat Zalo
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Video Modal -->
	<div class="modal fade" id="videoModal" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Video giới thiệu dự án</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body p-0">
					<div class="ratio ratio-16x9">
						<iframe id="videoIframe" src="" frameborder="0" allowfullscreen></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap 5 JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Lightbox JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.4/js/lightbox.min.js"></script>


	<!-- Google Maps API -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0VB4Yh823g6TWEsYCBet2WkfxuQ72Sug&libraries=marker&callback=initMap"
		async defer>
</script>
	<script>
    var contextPath = "${pageContext.request.contextPath}";
    var projectData = {
        id: "${project.id}",
        name: "${project.name}",
        latitude: ${project.latitude != null ? project.latitude : 10.7427},
        longitude: ${project.longitude != null ? project.longitude : 106.7181},
        address: "${project.address}",
        ward: "${project.ward}",
        priceFrom: ${project.priceFrom != null ? project.priceFrom : 'null'}
    };
</script>

	<script src="${pageContext.request.contextPath}/js/projectDetail.js"></script>
</body>
</html>