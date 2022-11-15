<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Forgot Password"></c:set>
<c:set var="feature" scope="session" value="Quên mật khẩu"></c:set>
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
						<h4 class="text-secondary">Quên mật khẩu</h4>
					</div>
					<div class="p-4">
						<form method="POST"
							action="/SOF3021_IT17103_ASSIGNMENT/account/forgot-password">
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-person-plus-fill text-white" title="Username"></i></span>
								<input type="text" class="form-control" name="username"
									placeholder="Tên đăng nhập" />
							</div>
							<!-- <div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-puzzle-fill text-white" title="Verify Code"></i></span> <input
									type="text" class="form-control" name="verifyCode"
									placeholder="Mã xác nhận" />
							</div> -->
							<div class="col" style="color: red; margin-left: 40px;">
								<p style="font-size: 20px">${fPasswordErrorMessage}</p>
							</div>
							<div class="d-grid col-12 mx-auto">
								<button class="btn btn-secondary">Submit</button>
							</div>
						</form>
						<hr>
						<p class="text-center mt-3">
							<a class="text-secondary"
								style="text-decoration: none;"
								href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/login/form">
								Đăng nhập</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>