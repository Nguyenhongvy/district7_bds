<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="pageTitle" value="Giới thiệu - District 7 BDS" />

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css">
</head>
<body>
    <%@ include file="layout/header.jsp" %>

    <!-- Hero Section -->
    <div class="hero-section">
        <div class="container text-center">
            <h1 class="hero-title">Giới thiệu về chúng tôi</h1>
            <p class="hero-subtitle">
                Đối tác tin cậy trong mọi giao dịch bất động sản tại Quận 7
            </p>
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center text-white-50">
                            <li class="breadcrumb-item">
                                <a href="${pageContext.request.contextPath}/" class="text-white">Trang chủ</a>
                            </li>
                            <li class="breadcrumb-item active text-white" aria-current="page">Giới thiệu</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <div class="container py-4">
        <!-- Company Overview -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-building"></i>Về District 7 BDS
            </h2>
            
            <div class="row align-items-center">
                <div class="col-lg-6">
                    <p class="lead mb-4">
                        <strong>District 7 BDS</strong> là công ty hàng đầu chuyên về dịch vụ bất động sản tại Quận 7, 
                        TP. Hồ Chí Minh với hơn 10 năm kinh nghiệm trong ngành.
                    </p>
                    
                    <p>
                        Chúng tôi tự hào là cầu nối đáng tin cậy giữa khách hàng và những cơ hội đầu tư bất động sản 
                        tốt nhất tại khu vực phát triển năng động nhất của thành phố. Với đội ngũ chuyên gia giàu kinh nghiệm 
                        và am hiểu sâu sắc về thị trường địa phương, chúng tôi cam kết mang đến dịch vụ chất lượng cao 
                        và giá trị thực cho mọi khách hàng.
                    </p>
                    
                    <p>
                        Sứ mệnh của chúng tôi là làm cho việc mua bán, cho thuê và đầu tư bất động sản trở nên đơn giản, 
                        minh bạch và hiệu quả nhất có thể.
                    </p>
                </div>
                
                <div class="col-lg-6">
                    <div class="text-center">
                        <div class="d-inline-flex align-items-center justify-content-center" 
                             style="width: 200px; height: 200px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); 
                                    border-radius: 50%; color: white; font-size: 4rem;">
                            <i class="fas fa-city"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Statistics -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-chart-line"></i>Thành tựu của chúng tôi
            </h2>
            
            <div class="stats-grid">
                <div class="stat-item">
                    <div class="stat-icon">
                        <i class="fas fa-handshake"></i>
                    </div>
                    <div class="stat-number">1,500+</div>
                    <div class="stat-label">Giao dịch thành công</div>
                </div>
                
                <div class="stat-item">
                    <div class="stat-icon">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="stat-number">5,000+</div>
                    <div class="stat-label">Khách hàng tin tưởng</div>
                </div>
                
                <div class="stat-item">
                    <div class="stat-icon">
                        <i class="fas fa-building"></i>
                    </div>
                    <div class="stat-number">200+</div>
                    <div class="stat-label">Dự án đã tham gia</div>
                </div>
                
                <div class="stat-item">
                    <div class="stat-icon">
                        <i class="fas fa-award"></i>
                    </div>
                    <div class="stat-number">10+</div>
                    <div class="stat-label">Năm kinh nghiệm</div>
                </div>
            </div>
        </div>

        <!-- Core Values -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-heart"></i>Giá trị cốt lõi
            </h2>
            
            <div class="values-grid">
                <div class="value-item">
                    <h5><i class="fas fa-shield-alt"></i>Uy tín</h5>
                    <p>Xây dựng niềm tin thông qua sự minh bạch, chính trực trong mọi giao dịch và cam kết thực hiện đúng lời hứa.</p>
                </div>
                
                <div class="value-item">
                    <h5><i class="fas fa-graduation-cap"></i>Chuyên nghiệp</h5>
                    <p>Đội ngũ được đào tạo bài bản, am hiểu thị trường và luôn cập nhật kiến thức mới nhất trong ngành.</p>
                </div>
                
                <div class="value-item">
                    <h5><i class="fas fa-users-cog"></i>Tận tâm</h5>
                    <p>Lắng nghe, thấu hiểu nhu cầu khách hàng và đưa ra giải pháp tối ưu nhất cho từng trường hợp cụ thể.</p>
                </div>
                
                <div class="value-item">
                    <h5><i class="fas fa-rocket"></i>Sáng tạo</h5>
                    <p>Ứng dụng công nghệ hiện đại, phương pháp tiếp thị sáng tạo để mang lại hiệu quả cao nhất.</p>
                </div>
            </div>
        </div>

        <!-- Leadership Team -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-user-tie"></i>Đội ngũ lãnh đạo
            </h2>
            
            <div class="team-grid">
                <div class="team-member">
                    <div class="team-avatar">
                        <i class="fas fa-user-tie"></i>
                    </div>
                    <div class="team-name">Nguyễn Hồng Vy</div>
                    <div class="team-position">Tổng Giám đốc</div>
                    <div class="team-description">
                        Hơn 15 năm kinh nghiệm trong lĩnh vực bất động sản. Thạc sĩ Kinh tế, chuyên gia phân tích thị trường BDS.
                    </div>
                    <div class="team-social">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        <a href="#"><i class="fas fa-envelope"></i></a>
                    </div>
                </div>
                
                <div class="team-member">
                    <div class="team-avatar">
                        <i class="fas fa-user-graduate"></i>
                    </div>
                    <div class="team-name">Nguyễn Lê Bích Ngân</div>
                    <div class="team-position">Giám đốc Kinh doanh</div>
                    <div class="team-description">
                        Chuyên gia tư vấn đầu tư với 12 năm kinh nghiệm. Từng làm việc tại các tập đoàn BDS lớn tại Việt Nam.
                    </div>
                    <div class="team-social">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        <a href="#"><i class="fas fa-envelope"></i></a>
                    </div>
                </div>
                
                <div class="team-member">
                    <div class="team-avatar">
                        <i class="fas fa-balance-scale"></i>
                    </div>
                    <div class="team-name">Lê Minh Cường</div>
                    <div class="team-position">Giám đốc Pháp lý</div>
                    <div class="team-description">
                        Luật sư chuyên về bất động sản với 10 năm kinh nghiệm. Đảm bảo mọi giao dịch đều an toàn và hợp pháp.
                    </div>
                    <div class="team-social">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        <a href="#"><i class="fas fa-envelope"></i></a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Company Timeline -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-history"></i>Lịch sử phát triển
            </h2>
            
            <div class="timeline">
                <div class="timeline-item">
                    <div class="timeline-year">2013</div>
                    <div class="timeline-content">
                        <h5>Thành lập công ty</h5>
                        <p>District 7 BDS được thành lập với 5 nhân viên và văn phòng nhỏ tại Quận 7.</p>
                    </div>
                </div>
                
                <div class="timeline-item">
                    <div class="timeline-year">2015</div>
                    <div class="timeline-content">
                        <h5>Mở rộng hoạt động</h5>
                        <p>Mở rộng quy mô lên 20 nhân viên và thiết lập mạng lưới đối tác chiến lược.</p>
                    </div>
                </div>
                
                <div class="timeline-item">
                    <div class="timeline-year">2018</div>
                    <div class="timeline-content">
                        <h5>Chuyển đổi số</h5>
                        <p>Ứng dụng công nghệ vào hoạt động kinh doanh, ra mắt website và hệ thống CRM.</p>
                    </div>
                </div>
                
                <div class="timeline-item">
                    <div class="timeline-year">2020</div>
                    <div class="timeline-content">
                        <h5>Đạt mốc 1000 giao dịch</h5>
                        <p>Thực hiện thành công giao dịch thứ 1000 và nhận nhiều giải thưởng uy tín.</p>
                    </div>
                </div>
                
                <div class="timeline-item">
                    <div class="timeline-year">2023</div>
                    <div class="timeline-content">
                        <h5>Mở rộng ra toàn quốc</h5>
                        <p>Mở chi nhánh tại Hà Nội và Đà Nẵng, trở thành thương hiệu BDS toàn quốc.</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Services Overview -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-concierge-bell"></i>Dịch vụ của chúng tôi
            </h2>
            
            <div class="row">
                <div class="col-lg-6">
                    <h5 class="mb-3">Dịch vụ chính</h5>
                    <ul class="list-unstyled">
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-success me-2"></i>
                            Mua bán bất động sản các loại
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-success me-2"></i>
                            Cho thuê văn phòng, nhà ở, mặt bằng
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-success me-2"></i>
                            Tư vấn đầu tư và phân tích thị trường
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-success me-2"></i>
                            Định giá bất động sản chuyên nghiệp
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-success me-2"></i>
                            Tư vấn pháp lý và thủ tục
                        </li>
                    </ul>
                </div>
                
                <div class="col-lg-6">
                    <h5 class="mb-3">Dịch vụ hỗ trợ</h5>
                    <ul class="list-unstyled">
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-primary me-2"></i>
                            Hỗ trợ vay vốn ngân hàng
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-primary me-2"></i>
                            Quản lý tài sản cho thuê
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-primary me-2"></i>
                            Thiết kế và thi công nội thất
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-primary me-2"></i>
                            Marketing bất động sản
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-check-circle text-primary me-2"></i>
                            Tư vấn phong thủy và thiết kế
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Partners -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-handshake"></i>Đối tác chiến lược
            </h2>
            
            <div class="partners-grid">
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-university"></i>
                    </div>
                    <h6>Vietcombank</h6>
                    <small class="text-muted">Ngân hàng đối tác</small>
                </div>
                
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-landmark"></i>
                    </div>
                    <h6>BIDV</h6>
                    <small class="text-muted">Hỗ trợ vay vốn</small>
                </div>
                
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-building"></i>
                    </div>
                    <h6>Vingroup</h6>
                    <small class="text-muted">Đối tác phát triển</small>
                </div>
                
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-home"></i>
                    </div>
                    <h6>Novaland</h6>
                    <small class="text-muted">Chủ đầu tư</small>
                </div>
                
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-city"></i>
                    </div>
                    <h6>CapitaLand</h6>
                    <small class="text-muted">Đối tác quốc tế</small>
                </div>
                
                <div class="partner-item">
                    <div class="partner-logo">
                        <i class="fas fa-shield-alt"></i>
                    </div>
                    <h6>Bảo Việt</h6>
                    <small class="text-muted">Bảo hiểm BDS</small>
                </div>
            </div>
        </div>

        <!-- Mission & Vision -->
        <div class="row">
            <div class="col-lg-6">
                <div class="about-section">
                    <h2 class="section-title">
                        <i class="fas fa-bullseye"></i>Sứ mệnh
                    </h2>
                    <p class="lead">
                        Trở thành cầu nối đáng tin cậy giữa khách hàng và những cơ hội bất động sản tốt nhất, 
                        mang đến giá trị thực và trải nghiệm hoàn hảo trong mọi giao dịch.
                    </p>
                    <ul class="list-unstyled">
                        <li class="mb-2">
                            <i class="fas fa-arrow-right text-primary me-2"></i>
                            Cung cấp dịch vụ chất lượng cao
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-arrow-right text-primary me-2"></i>
                            Đảm bảo lợi ích tối đa cho khách hàng
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-arrow-right text-primary me-2"></i>
                            Xây dựng mối quan hệ bền vững
                        </li>
                    </ul>
                </div>
            </div>
            
            <div class="col-lg-6">
                <div class="about-section">
                    <h2 class="section-title">
                        <i class="fas fa-eye"></i>Tầm nhìn
                    </h2>
                    <p class="lead">
                        Trở thành công ty bất động sản hàng đầu Việt Nam, được khách hàng tin tưởng và 
                        đối tác tôn trọng vào năm 2030.
                    </p>
                    <ul class="list-unstyled">
                        <li class="mb-2">
                            <i class="fas fa-star text-warning me-2"></i>
                            Dẫn đầu về chất lượng dịch vụ
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-star text-warning me-2"></i>
                            Mở rộng ra toàn khu vực
                        </li>
                        <li class="mb-2">
                            <i class="fas fa-star text-warning me-2"></i>
                            Ứng dụng công nghệ tiên tiến
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Awards & Certifications -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-trophy"></i>Giải thưởng & Chứng nhận
            </h2>
            
            <div class="row">
                <div class="col-md-6 mb-3">
                    <div class="d-flex align-items-center p-3 border rounded">
                        <div class="text-warning me-3" style="font-size: 2rem;">
                            <i class="fas fa-award"></i>
                        </div>
                        <div>
                            <h6 class="mb-1">Top 10 Công ty BDS uy tín 2023</h6>
                            <small class="text-muted">Hiệp hội BDS Việt Nam</small>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6 mb-3">
                    <div class="d-flex align-items-center p-3 border rounded">
                        <div class="text-success me-3" style="font-size: 2rem;">
                            <i class="fas fa-certificate"></i>
                        </div>
                        <div>
                            <h6 class="mb-1">Chứng nhận ISO 9001:2015</h6>
                            <small class="text-muted">Hệ thống quản lý chất lượng</small>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6 mb-3">
                    <div class="d-flex align-items-center p-3 border rounded">
                        <div class="text-info me-3" style="font-size: 2rem;">
                            <i class="fas fa-medal"></i>
                        </div>
                        <div>
                            <h6 class="mb-1">Doanh nghiệp tiêu biểu 2022</h6>
                            <small class="text-muted">UBND TP. Hồ Chí Minh</small>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6 mb-3">
                    <div class="d-flex align-items-center p-3 border rounded">
                        <div class="text-primary me-3" style="font-size: 2rem;">
                            <i class="fas fa-thumbs-up"></i>
                        </div>
                        <div>
                            <h6 class="mb-1">Thương hiệu tin cậy 2023</h6>
                            <small class="text-muted">Người tiêu dùng bình chọn</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Why Choose Us -->
        <div class="about-section">
            <h2 class="section-title">
                <i class="fas fa-question-circle"></i>Tại sao chọn chúng tôi?
            </h2>
            
            <div class="row">
                <div class="col-lg-4 mb-4">
                    <div class="text-center">
                        <div class="d-inline-flex align-items-center justify-content-center mb-3" 
                             style="width: 80px; height: 80px; background: rgba(102, 126, 234, 0.1); 
                                    border-radius: 50%; color: #667eea; font-size: 2rem;">
                            <i class="fas fa-clock"></i>
                        </div>
                        <h5>Phục vụ 24/7</h5>
                        <p class="text-muted">
                            Đội ngũ tư vấn luôn sẵn sàng hỗ trợ khách hàng bất cứ lúc nào, 
                            kể cả ngày nghỉ và lễ tết.
                        </p>
                    </div>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <div class="text-center">
                        <div class="d-inline-flex align-items-center justify-content-center mb-3" 
                             style="width: 80px; height: 80px; background: rgba(102, 126, 234, 0.1); 
                                    border-radius: 50%; color: #667eea; font-size: 2rem;">
                            <i class="fas fa-map-marked-alt"></i>
                        </div>
                        <h5>Am hiểu địa phương</h5>
                        <p class="text-muted">
                            Hơn 10 năm hoạt động tại Quận 7, chúng tôi hiểu rõ từng con phố, 
                            từng dự án và tiềm năng phát triển.
                        </p>
                    </div>
                </div>
                
                <div class="col-lg-4 mb-4">
                    <div class="text-center">
                        <div class="d-inline-flex align-items-center justify-content-center mb-3" 
                             style="width: 80px; height: 80px; background: rgba(102, 126, 234, 0.1); 
                                    border-radius: 50%; color: #667eea; font-size: 2rem;">
                            <i class="fas fa-shield-alt"></i>
                        </div>
                        <h5>Đảm bảo pháp lý</h5>
                        <p class="text-muted">
                            Đội ngũ luật sư chuyên nghiệp đảm bảo mọi giao dịch đều 
                            an toàn, minh bạch và tuân thủ pháp luật.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <!-- Call to Action -->
        <div class="about-section text-center">
            <h2 class="section-title justify-content-center">
                <i class="fas fa-phone"></i>Liên hệ với chúng tôi ngay hôm nay
            </h2>
            
            <p class="lead mb-4">
                Hãy để chúng tôi giúp bạn tìm kiếm cơ hội bất động sản tốt nhất tại Quận 7
            </p>
            
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="d-flex flex-wrap gap-3 justify-content-center">
                        <a href="${pageContext.request.contextPath}/contact" class="btn btn-primary">
                            <i class="fas fa-envelope me-2"></i>Liên hệ tư vấn
                        </a>
                        <a href="tel:1900123456" class="btn btn-outline-primary">
                            <i class="fas fa-phone me-2"></i>Hotline: 1900 123 456
                        </a>
                        <a href="${pageContext.request.contextPath}/projects" class="btn btn-outline-success">
                            <i class="fas fa-list me-2"></i>Xem dự án
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="layout/footer.jsp" %>
    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JavaScript -->
<script src="${pageContext.request.contextPath}/js/about.js"></script>
</body>
</html>