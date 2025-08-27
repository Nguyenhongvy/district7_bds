<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav
	class="navbar navbar-expand-lg navbar-light bg-white shadow-sm sticky-top">
	<div class="container">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
			<i class="fas fa-building me-2"></i>District 7 BDS
		</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/"> <i
						class="fas fa-home me-1"></i>Trang chủ
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/about"> <i
						class="fas fa-info-circle me-1"></i>Giới thiệu
				</a></li>
				<li class="nav-item"><a class="nav-link active"
					href="${pageContext.request.contextPath}/projects"> <i
						class="fas fa-list me-1"></i>Dự án
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/map"> <i
						class="fas fa-map-marked-alt me-1"></i>Bản đồ
				</a></li>
			</ul>

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/contact"> <i
						class="fas fa-phone me-1"></i>Liên hệ
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin"> <i
						class="fas fa-cog me-1"></i>Quản trị
				</a></li>
			</ul>
		</div>
	</div>
</nav>