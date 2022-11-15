<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Change Password"></c:set>
<c:set var="feature" scope="session" value="Đổi mật khẩu"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />

<body style="background-color: #6c757d;">
	<div class="container-fluid vh-100" style="margin-top: 30px">
		<div class="" style="margin-top: 30px;">
			<div class="rounded d-flex justify-content-center">
				<jsp:include page="../layout/acc-manager-body.jsp" />
				<div class="col-md-5 col-sm-12 shadow-lg p-5 bg-light"
					style="margin-bottom: 10px;">
					<div class="text-center">
						<h4 class="text-secondary">Đổi mật khẩu</h4>
					</div>
					<div class="p-4">
						<form method="POST"
							action="/SOF3021_IT17103_ASSIGNMENT/account/change-password">
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-person-plus-fill text-white" title="Username"></i></span>
								<input type="text" class="form-control" name="username"
									placeholder="Tên đăng nhập" />
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-key-fill text-white" title="Password"></i></span> <input
									type="password" class="form-control" name="password"
									placeholder="Mật khẩu" />
							</div>
							<div class="col" style="color: red; margin-left: 40px;">
								<p style="font-size: 20px">${loginErrorMessage}</p>
							</div>
							<div class="d-grid col-12 mx-auto">
								<button class="btn btn-secondary">Đăng nhập</button>
							</div>
						</form>
						<p class="mt-3">
							<a class="text-secondary" style="text-decoration: none;"
								href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/forgot-password/form">
								Quên mật khẩu</a>
						</p>
						<hr>
						<div class="row">
							<div class="col-md-4">
								<a style="width: 100%" class="btn btn-outline-secondary bi bi-facebook" href="#" role="button"> Facebook</a>
							</div> 
							<div class="col-md-4">
								<a style="width: 100%" class="btn btn-outline-secondary bi bi-google" href="#" role="button"> Google</a>
							</div>
							<div class="col-md-4">
								<a style="width: 100%" class="btn btn-outline-secondary bi bi-apple" href="#" role="button"> Apple</a>
							</div>
						</div>
						<p class="text-center mt-3">
							Chưa có tài khoản? <a class="text-secondary"
								style="text-decoration: none;"
								href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/register/form">
								Đăng ký</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>