<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<header>
	<nav class="navbar bg-secondary">
		<div class="container">
			<nav class="navbar navbar-expand-lg bg-secondary navbar-dark"
				style="width: 100%;">
				<div class="container-fluid">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"><i
									class="bi bi-globe"></i> Tiếng Việt </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">Tiếng Việt</a></li>
									<li><a class="dropdown-item" href="#">Tiếng Anh</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="bi bi-puzzle"></i> Giới thiệu</a></li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="bi bi-telephone"></i> Liên hệ</a></li>
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="bi bi-question-circle"></i> Hỗ trợ</a></li>
						</ul>
						<ul class="navbar-nav ms-auto">
							<li class="nav-item"><a class="nav-link" href="#"><i
									class="bi bi-bell"></i> Thông báo</a></li>

							<c:if test="${empty user }">
								<li class="nav-item"><a class="nav-link"
									href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/login"><i
										class="bi bi-box-arrow-in-right"></i> Đăng nhập</a></li>
							</c:if>
							<c:if test="${not empty user }">
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false"><i
										class="bi bi-globe"></i> Tài khoản </a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">${user.fullname}</a></li>
										<li><a class="dropdown-item" href="#">Đổi mật khẩu</a></li>
										<li><a class="dropdown-item"
											href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/logout">
												Đăng xuất</a></li>
									</ul></li>
							</c:if>
						</ul>
					</div>
				</div>
			</nav>
			<nav class="navbar navbar-expand-lg bg-secondary navbar-dark"
				style="width: 100%; margin-top: -16px;">
				<div style="text-align: center;">
					<a class="navbar-brand"
						href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/home">
						<i class="bi bi-apple" style="font-size: 60px;"></i><br> <span
						style="font-size: 20px; margin-left: 8%;">Mobile Store</span>
					</a>
				</div>
				<ul class="nav justify-content-center"
					style="width: 100%; margin-top: 20px;">
					<form class="d-flex" role="search">
						<input class="form-control me-3" type="search"
							placeholder="Search" style="width: 600px;">
						<button class="btn btn-light" type="submit">
							<i class="bi bi-search" style="font-size: 20px;"></i>
						</button>
						<a class="position-relative"
							href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/cart/view"
							style="margin-left: 10%; color: #ffffff;">
							<c:if test="${ cartSize != 0}">
								<span
									class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-light"
									style="color: black">
									${cartSize} </span>
							</c:if>
							<h2>
								<i class="bi bi-cart"></i>
							</h2>
						</a>
					</form>
				</ul>
			</nav>
		</div>
	</nav>
</header>