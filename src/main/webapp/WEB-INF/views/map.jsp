<!-- map.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="pageTitle" value="Bản đồ Dự án - District 7 BDS" />

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/map.css">
</head>
<body>
    <%@ include file="layout/header.jsp" %>

    <div class="container-fluid py-4">
        <!-- Filter Panel -->
        <div class="filter-panel">
            <div class="row align-items-center">
                <div class="col-md-3">
                    <h5 class="mb-3 mb-md-0">
                        <i class="fas fa-filter me-2"></i>Lọc dự án
                    </h5>
                </div>
                <div class="col-md-9">
                    <div class="row g-2">
                        <!-- Project Type Filter -->
                        <div class="col-md-3">
                            <select class="form-select" id="typeFilter">
                                <option value="">Tất cả loại</option>
                                <c:forEach var="type" items="${projectTypes}">
                                    <option value="${type.name()}">${type.displayName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <!-- Ward Filter -->
                        <div class="col-md-3">
                            <select class="form-select" id="wardFilter">
                                <option value="">Tất cả phường</option>
                                <c:forEach var="ward" items="${wards}">
                                    <option value="${ward.name}">${ward.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <!-- Status Filter -->
                        <div class="col-md-3">
                            <select class="form-select" id="statusFilter">
                                <option value="">Tất cả trạng thái</option>
                                <c:forEach var="status" items="${projectStatuses}">
                                    <option value="${status.name()}">${status.displayName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <!-- Action Buttons -->
                        <div class="col-md-3">
                            <button type="button" class="btn btn-primary me-2" onclick="applyFilters()">
                                <i class="fas fa-search me-1"></i>Lọc
                            </button>
                            <button type="button" class="btn btn-outline-secondary" onclick="clearFilters()">
                                <i class="fas fa-times me-1"></i>Xóa
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Map Container -->
        <div class="map-container">
            <div id="map"></div>
            
            <!-- Loading Overlay -->
            <div class="loading-overlay" id="loadingOverlay">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Đang tải...</span>
                </div>
            </div>
            
            <!-- Project Info Panel -->
            <div class="project-info-panel" id="projectInfoPanel">
                <button class="close-panel" onclick="closeProjectPanel()">
                    <i class="fas fa-times"></i>
                </button>
                <div id="projectInfoContent">
                    <!-- Project details will be loaded here -->
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
	<script>
    var contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/map.js"></script>
 
    <!-- Google Maps API -->
    <script
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0VB4Yh823g6TWEsYCBet2WkfxuQ72Sug&libraries=marker&callback=initMap"
    async
    defer>
</script>

</body>
</html>