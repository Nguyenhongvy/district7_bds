<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<c:set var="pageTitle" value="Trang chủ - District 7 BDS" />
<%@ page isELIgnored="false"%>

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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
	<%@ include file="layout/header.jsp"%>
	<!-- Hero Section -->
	<section class="hero-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="hero-content">
						<h1 class="hero-title">Khám phá Bất động sản Quận 7</h1>
						<p class="hero-subtitle">Tìm kiếm và khám phá các dự án bất
							động sản cao cấp tại Quận 7 - Trung tâm phát triển năng động của
							TP.HCM</p>

						<div class="row justify-content-center mt-4">
							<div class="col-auto">
								<a href="${pageContext.request.contextPath}/projects"
									class="btn btn-primary btn-lg me-3"> <i
									class="fas fa-search me-2"></i>Xem dự án
								</a> <a href="${pageContext.request.contextPath}/map"
									class="btn btn-outline-light btn-lg"> <i
									class="fas fa-map-marked-alt me-2"></i>Bản đồ dự án
								</a>
							</div>
						</div>

						<!-- Quick Search Box -->
						<div class="search-box">
							<div class="row g-3">
								<div class="col-md-3">
									<select class="form-select" id="quickSearchType">
										<option value="">Loại dự án</option>
										<option value="APARTMENT">Căn hộ</option>
										<option value="VILLA">Biệt thự</option>
										<option value="TOWNHOUSE">Nhà phố</option>
										<option value="OFFICE">Văn phòng</option>
									</select>
								</div>
								<div class="col-md-3">
									<select class="form-select" id="quickSearchWard">
										<option value="">Chọn phường</option>
										<option value="Phường Tân Thuận Đông">Phường Tân
											Thuận Đông</option>
										<option value="Phường Tân Thuận Tây">Phường Tân Thuận
											Tây</option>
										<option value="Phường Tân Kiểng">Phường Tân Kiểng</option>
										<option value="Phường Tân Hưng">Phường Tân Hưng</option>
										<option value="Phường Bình Thuận">Phường Bình Thuận</option>
										<option value="Phường Tân Phong">Phường Tân Phong</option>
										<option value="Phường Tân Phú">Phường Tân Phú</option>
										<option value="Phường Tân Quy">Phường Tân Quy</option>
										<option value="Phường Phú Thuận">Phường Phú Thuận</option>
										<option value="Phường Phú Mỹ">Phường Phú Mỹ</option>
									</select>
								</div>
								<div class="col-md-3">
									<select class="form-select" id="quickSearchPrice">
										<option value="">Mức giá</option>
										<option value="under-2">Dưới 2 tỷ</option>
										<option value="2-5">2 - 5 tỷ</option>
										<option value="5-10">5 - 10 tỷ</option>
										<option value="above-10">Trên 10 tỷ</option>
									</select>
								</div>
								<div class="col-md-3">
									<button class="btn btn-primary w-100"
										onclick="performQuickSearch()">
										<i class="fas fa-search me-1"></i>Tìm kiếm
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Statistics Section -->
	<section class="stats-section">
		<div class="container">
			<div class="row text-center mb-5">
				<div class="col-12">
					<h2 class="mb-3">Thống kê dự án</h2>
					<p class="text-muted">Tổng quan về các dự án bất động sản tại
						Quận 7</p>
				</div>
			</div>

			<div class="row g-4">
				<div class="col-md-3">
					<div class="stats-card">
						<div class="stats-number">36</div>
						<h5>Tổng dự án</h5>
						<p class="text-muted mb-0">Dự án đang hoạt động</p>
					</div>
				</div>
				<div class="col-md-3">
					<div class="stats-card">
						<div class="stats-number">20</div>
						<h5>Căn hộ</h5>
						<p class="text-muted mb-0">Dự án căn hộ cao cấp</p>
					</div>
				</div>
				<div class="col-md-3">
					<div class="stats-card">
						<div class="stats-number">58</div>
						<h5>Biệt thự</h5>
						<p class="text-muted mb-0">Biệt thự & nhà phố</p>
					</div>
				</div>
				<div class="col-md-3">
					<div class="stats-card">
						<div class="stats-number">75</div>
						<h5>Văn phòng</h5>
						<p class="text-muted mb-0">Tòa nhà văn phòng</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Featured Projects -->
	<c:if test="${not empty featuredProjects}">
		<section class="py-5">
			<div class="container">
				<div class="row mb-5">
					<div class="col-12 text-center">
						<h2 class="mb-3">Dự án nổi bật</h2>
						<p class="text-muted">Những dự án được quan tâm nhiều nhất tại
							Quận 7</p>
					</div>
				</div>

				<div class="row g-4">
					<c:forEach var="project" items="${featuredProjects}"
						varStatus="loop">
						<c:if test="${loop.index < 6}">
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
												<span class="badge-featured"> <i
													class="fas fa-star me-1"></i>Nổi bật
												</span>
											</c:if>
											<c:if test="${project.isHot}">
												<span class="badge-hot"> <i class="fas fa-fire me-1"></i>Hot
												</span>
											</c:if>
										</div>
									</div>


									<div class="project-content">
										<div class="project-type">${project.projectType.displayName}</div>

										<h5 class="mb-2">
											<a
												href="${pageContext.request.contextPath}/projects/${project.slug}"
												class="text-decoration-none text-dark"> ${project.name}
											</a>
										</h5>

										<p class="text-muted small mb-3">
											<i class="fas fa-map-marker-alt me-1"></i>
											${project.address}, ${project.ward}
										</p>

										<div class="project-price">
											<c:choose>
												<c:when
													test="${project.priceFrom != null and project.priceTo != null}">
													<fmt:formatNumber value="${project.priceFrom / 1000000000}"
														type="currency" currencySymbol="" pattern="#,###" /> - 
                                                <fmt:formatNumber
														value="${project.priceTo / 1000000000}" type="currency"
														currencySymbol="" pattern="#,###" /> tỷ
                                            </c:when>
												<c:when test="${project.priceFrom != null}">
                                                Từ <fmt:formatNumber
														value="${project.priceFrom / 1000000000}" type="currency"
														currencySymbol="" pattern="#,###" /> tỷ
                                            </c:when>
												<c:otherwise>
                                                Liên hệ
                                            </c:otherwise>
											</c:choose>
										</div>

										<div class="d-flex justify-content-between align-items-center">
											<small class="text-muted"> <i class="fas fa-eye me-1"></i>${project.viewCount}
												lượt xem
											</small> <a
												href="${pageContext.request.contextPath}/projects/${project.slug}"
												class="btn btn-outline-primary btn-sm"> Chi tiết </a>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>

				<div class="row mt-5">
					<div class="col-12 text-center">
						<a href="${pageContext.request.contextPath}/projects"
							class="btn btn-primary btn-lg"> <i
							class="fas fa-th-large me-2"></i>Xem tất cả dự án
						</a>
					</div>
				</div>
			</div>
		</section>
	</c:if>

	<!-- Features Section -->
	<section class="features-section bg-light">
		<div class="container">
			<div class="row mb-5">
				<div class="col-12 text-center">
					<h2 class="mb-3">Tại sao chọn chúng tôi?</h2>
					<p class="text-muted">Những ưu điểm vượt trội khi sử dụng hệ
						thống của chúng tôi</p>
				</div>
			</div>

			<div class="row g-4">
				<div class="col-md-4">
					<div class="feature-card">
						<div class="feature-icon">
							<i class="fas fa-map-marked-alt"></i>
						</div>
						<h4>Bản đồ tương tác</h4>
						<p class="text-muted">Khám phá dự án qua bản đồ trực quan với
							thông tin chi tiết và vị trí chính xác</p>
					</div>
				</div>

				<div class="col-md-4">
					<div class="feature-card">
						<div class="feature-icon">
							<i class="fas fa-search"></i>
						</div>
						<h4>Tìm kiếm thông minh</h4>
						<p class="text-muted">Bộ lọc và tìm kiếm nâng cao giúp bạn tìm
							được dự án phù hợp nhanh chóng</p>
					</div>
				</div>

				<div class="col-md-4">
					<div class="feature-card">
						<div class="feature-icon">
							<i class="fas fa-info-circle"></i>
						</div>
						<h4>Thông tin đầy đủ</h4>
						<p class="text-muted">Cập nhật thông tin chi tiết về giá, tiện
							ích, tiến độ của từng dự án</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="layout/footer.jsp"%>

	<!-- Scripts -->
	<script>
    var contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/home.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</body>
</html>