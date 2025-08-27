<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="pageTitle" value="Liên hệ - District 7 BDS" />

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
    <style>
       
    </style>
</head>
<body>
    <%@ include file="layout/header.jsp" %>
    <!-- Hero Section -->
    <div class="hero-section">
        <div class="container text-center">
            <h1 class="hero-title">Liên hệ với chúng tôi</h1>
            <p class="hero-subtitle">
                Đội ngũ chuyên gia bất động sản District 7 BDS luôn sẵn sàng hỗ trợ bạn
            </p>
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center text-white-50">
                            <li class="breadcrumb-item">
                                <a href="${pageContext.request.contextPath}/" class="text-white">Trang chủ</a>
                            </li>
                            <li class="breadcrumb-item active text-white" aria-current="page">Liên hệ</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <div class="container py-4">
        <!-- Success/Error Messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <i class="fas fa-check-circle me-2"></i>
                <strong>Cảm ơn bạn đã để lại lời nhắn. Chúng tôi sẽ liên hệ trong thời gian sớm nhất </strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <i class="fas fa-exclamation-triangle me-2"></i>
                <strong>Lỗi!</strong> ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Contact Information -->
        <div class="contact-section">
            <h2 class="section-title">
                <i class="fas fa-info-circle"></i>Thông tin liên hệ
            </h2>
            
            <div class="contact-info-grid">
                <div class="contact-info-item">
                    <div class="contact-icon">
                        <i class="fas fa-map-marker-alt"></i>
                    </div>
                    <h5>Địa chỉ văn phòng</h5>
                    <p>
                        123 Nguyễn Thị Thập<br>
                        Phường Tân Phú, Quận 7<br>
                        TP. Hồ Chí Minh
                    </p>
                </div>
                
                <div class="contact-info-item">
                    <div class="contact-icon">
                        <i class="fas fa-phone"></i>
                    </div>
                    <h5>Điện thoại</h5>
                    <p>
                        Hotline: <a href="tel:1900123456">1900 123 456</a><br>
                        Di động: <a href="tel:0901234567">0901 234 567</a>
                    </p>
                </div>
                
                <div class="contact-info-item">
                    <div class="contact-icon">
                        <i class="fas fa-envelope"></i>
                    </div>
                    <h5>Email</h5>
                    <p>
                        Tổng đài: <a href="mailto:info@district7bds.com">info@district7bds.com</a><br>
                        Hỗ trợ: <a href="mailto:support@district7bds.com">support@district7bds.com</a>
                    </p>
                </div>
                
                <div class="contact-info-item">
                    <div class="contact-icon">
                        <i class="fas fa-clock"></i>
                    </div>
                    <h5>Giờ làm việc</h5>
                    <p>
                        Thứ 2 - Thứ 6: 8:00 - 18:00<br>
                        Thứ 7 - CN: 8:00 - 17:00
                    </p>
                </div>
            </div>
            
            <!-- Social Links -->
            <div class="social-links">
                <a href="https://facebook.com/district7bds" target="_blank" class="social-link">
                    <i class="fab fa-facebook-f"></i>
                </a>
                <a href="https://zalo.me/district7bds" target="_blank" class="social-link">
                    <i class="fas fa-comments"></i>
                </a>
                <a href="https://youtube.com/district7bds" target="_blank" class="social-link">
                    <i class="fab fa-youtube"></i>
                </a>
                <a href="https://linkedin.com/company/district7bds" target="_blank" class="social-link">
                    <i class="fab fa-linkedin-in"></i>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-8">
                <!-- Contact Form -->
                <div class="contact-form">
                    <h2 class="section-title">
                        <i class="fas fa-envelope"></i>Gửi tin nhắn cho chúng tôi
                    </h2>
                    
                    <form id="contactForm" action="${pageContext.request.contextPath}/contact" method="post">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="fullName" class="form-label">Họ và tên *</label>
                                <input type="text" class="form-control" id="fullName" name="fullName" 
                                       value="${param.fullName}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="phone" class="form-label">Số điện thoại *</label>
                                <input type="tel" class="form-control" id="phone" name="phone" 
                                       value="${param.phone}" required>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="email" class="form-label">Email *</label>
                                <input type="email" class="form-control" id="email" name="email" 
                                       value="${param.email}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="subject" class="form-label">Chủ đề</label>
                                <select class="form-select" id="subject" name="subject">
                                    <option value="">Chọn chủ đề</option>
                                    <option value="buy" ${param.subject == 'buy' ? 'selected' : ''}>Mua bán bất động sản</option>
                                    <option value="rent" ${param.subject == 'rent' ? 'selected' : ''}>Cho thuê</option>
                                    <option value="invest" ${param.subject == 'invest' ? 'selected' : ''}>Tư vấn đầu tư</option>
                                    <option value="valuation" ${param.subject == 'valuation' ? 'selected' : ''}>Định giá BDS</option>
                                    <option value="legal" ${param.subject == 'legal' ? 'selected' : ''}>Tư vấn pháp lý</option>
                                    <option value="other" ${param.subject == 'other' ? 'selected' : ''}>Khác</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="budget" class="form-label">Ngân sách dự kiến</label>
                            <select class="form-select" id="budget" name="budget">
                                <option value="">Chọn mức ngân sách</option>
                                <option value="under-2" ${param.budget == 'under-2' ? 'selected' : ''}>Dưới 2 tỷ</option>
                                <option value="2-3" ${param.budget == '2-3' ? 'selected' : ''}>2 - 3 tỷ</option>
                                <option value="3-5" ${param.budget == '3-5' ? 'selected' : ''}>3 - 5 tỷ</option>
                                <option value="5-10" ${param.budget == '5-10' ? 'selected' : ''}>5 - 10 tỷ</option>
                                <option value="over-10" ${param.budget == 'over-10' ? 'selected' : ''}>Trên 10 tỷ</option>
                            </select>
                        </div>
                        
                        <div class="mb-3">
                            <label for="location" class="form-label">Khu vực quan tâm</label>
                            <select class="form-select" id="location" name="location">
                                <option value="">Chọn khu vực</option>
                                <option value="Phường Tân Thuận Đông" ${param.location == 'Phường Tân Thuận Đông' ? 'selected' : ''}>Phường Tân Thuận Đông</option>
                                <option value="Phường Tân Thuận Tây" ${param.location == 'Phường Tân Thuận Tây' ? 'selected' : ''}>Phường Tân Thuận Tây</option>
                                <option value="Phường Tân Kiểng" ${param.location == 'Phường Tân Kiểng' ? 'selected' : ''}>Phường Tân Kiểng</option>
                                <option value="Phường Tân Hưng" ${param.location == 'Phường Tân Hưng' ? 'selected' : ''}>Phường Tân Hưng</option>
                                <option value="Phường Bình Thuận" ${param.location == 'Phường Bình Thuận' ? 'selected' : ''}>Phường Bình Thuận</option>
                                <option value="Phường Tân Phong" ${param.location == 'Phường Tân Phong' ? 'selected' : ''}>Phường Tân Phong</option>
                                <option value="Phường Tân Phú" ${param.location == 'Phường Tân Phú' ? 'selected' : ''}>Phường Tân Phú</option>
                                <option value="Phường Tân Quy" ${param.location == 'Phường Tân Quy' ? 'selected' : ''}>Phường Tân Quy</option>
                                <option value="Phường Phú Thuận" ${param.location == 'Phường Phú Thuận' ? 'selected' : ''}>Phường Phú Thuận</option>
                                <option value="Phường Phú Mỹ" ${param.location == 'Phường Phú Mỹ' ? 'selected' : ''}>Phường Phú Mỹ</option>
                            </select>
                        </div>
                        
                        <div class="mb-4">
                            <label for="message" class="form-label">Nội dung tin nhắn *</label>
                            <textarea class="form-control" id="message" name="message" rows="6" 
                                      placeholder="Vui lòng mô tả chi tiết nhu cầu của bạn..." required>${param.message}</textarea>
                        </div>
                        
                        <div class="mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="agreeTerms" name="agreeTerms" required>
                                <label class="form-check-label" for="agreeTerms">
                                    Tôi đồng ý với <a href="${pageContext.request.contextPath}/terms" target="_blank">điều khoản sử dụng</a> 
                                    và <a href="${pageContext.request.contextPath}/privacy" target="_blank">chính sách bảo mật</a>
                                </label>
                            </div>
                        </div>
                        
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-paper-plane me-2"></i>Gửi tin nhắn
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="col-lg-4">
                <!-- Office Hours -->
                <div class="office-hours">
                    <h6><i class="fas fa-clock me-2"></i>Giờ làm việc</h6>
                    <ul class="hours-list">
                        <li>
                            <span>Thứ 2 - Thứ 6</span>
                            <span class="text-success fw-bold">8:00 - 18:00</span>
                        </li>
                        <li>
                            <span>Thứ 7</span>
                            <span class="text-success fw-bold">8:00 - 17:00</span>
                        </li>
                        <li>
                            <span>Chủ nhật</span>
                            <span class="text-warning fw-bold">8:00 - 17:00</span>
                        </li>
                        <li>
                            <span>Lễ - Tết</span>
                            <span class="text-muted">Nghỉ</span>
                        </li>
                    </ul>
                </div>
                
                <!-- Quick Contact -->
                <div class="contact-section">
                    <h5 class="section-title">
                        <i class="fas fa-headset"></i>Liên hệ nhanh
                    </h5>
                    
                    <div class="d-grid gap-2">
                        <a href="tel:1900123456" class="btn btn-outline-primary">
                            <i class="fas fa-phone me-2"></i>Gọi ngay: 1900 123 456
                        </a>
                        <a href="https://zalo.me/0901234567" target="_blank" class="btn btn-info text-white">
                            <i class="fas fa-comments me-2"></i>Chat Zalo
                        </a>
                        <a href="mailto:info@district7bds.com" class="btn btn-outline-secondary">
                            <i class="fas fa-envelope me-2"></i>Gửi email
                        </a>
                    </div>
                </div>
                
                <!-- Emergency Contact -->
                <div class="contact-section">
                    <h5 class="section-title text-danger">
                        <i class="fas fa-exclamation-triangle"></i>Hỗ trợ khẩn cấp
                    </h5>
                    <p class="text-muted small mb-3">
                        Dành cho các trường hợp cần hỗ trợ gấp ngoài giờ hành chính
                    </p>
                    
                    <div class="d-grid">
                        <a href="tel:0909123456" class="btn btn-danger">
                            <i class="fas fa-phone-alt me-2"></i>SOS: 0909 123 456
                        </a>
                    </div>
                </div>
            </div>
        </div>
        

        <!-- FAQ Section -->
        <div class="faq-section" style="margin-top: 20px">
            <h2 class="section-title">
                <i class="fas fa-question-circle"></i>Câu hỏi thường gặp
            </h2>
            
            <div class="accordion" id="faqAccordion">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#faq1">
                            Công ty có những dịch vụ gì?
                        </button>
                    </h2>
                    <div id="faq1" class="accordion-collapse collapse show" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Chúng tôi cung cấp đầy đủ các dịch vụ bất động sản tại Quận 7: mua bán, cho thuê, tư vấn đầu tư, 
                            định giá, tư vấn pháp lý, và quản lý tài sản.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq2">
                            Làm thế nào để đặt lịch xem nhà?
                        </button>
                    </h2>
                    <div id="faq2" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Bạn có thể đặt lịch xem nhà qua hotline 1900 123 456, gửi email, hoặc điền form trên website. 
                            Đội ngũ tư vấn sẽ liên hệ xác nhận lịch hẹn trong vòng 30 phút.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq3">
                            Chi phí dịch vụ như thế nào?
                        </button>
                    </h2>
                    <div id="faq3" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Tư vấn ban đầu hoàn toàn miễn phí. Chi phí dịch vụ sẽ được thông báo rõ ràng trước khi thực hiện, 
                            tuân theo quy định của pháp luật về môi giới bất động sản.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq4">
                            Thời gian xử lý giao dịch bao lâu?
                        </button>
                    </h2>
                    <div id="faq4" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Thời gian xử lý phụ thuộc vào loại giao dịch. Thông thường: mua bán từ 15-30 ngày, 
                            cho thuê từ 3-7 ngày. Chúng tôi cam kết hỗ trợ tối đa để rút ngắn thời gian.
                        </div>
                    </div>
                </div>
                
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faq5">
                            Có hỗ trợ vay ngân hàng không?
                        </button>
                    </h2>
                    <div id="faq5" class="accordion-collapse collapse" data-bs-parent="#faqAccordion">
                        <div class="accordion-body">
                            Có, chúng tôi có quan hệ đối tác với nhiều ngân hàng uy tín, hỗ trợ khách hàng 
                            thủ tục vay vốn với lãi suất ưu đãi và thời gian duyệt nhanh chóng.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
<%@ include file="layout/footer.jsp" %>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
 // Lấy context path từ JSP
    var contextPath = '${pageContext.request.contextPath}';
    </script>
    	<script src="${pageContext.request.contextPath}/js/contact.js"></script>
</body>
</html>