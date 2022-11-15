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
				<div class="col-md-5 col-sm-12"
					style="margin-bottom: 10px; margin-right: 3%; background-color: #6c757d;">
					<div style="text-align: center; margin-top: 10%; color: #ffffff;">
						<span class="navbar-brand"> <i class="bi bi-apple"
							style="font-size: 200px;"></i><br> <span
							style="font-size: 30px;">Authorized Reseller</span>
						</span>
					</div>
					<div style="text-align: center; margin-top: 5%; color: #ffffff;">
						<h4>
							Đơn vị ủy quyền của Apple <br> cung cấp các sản phẩm chính
							hãng Apple.
						</h4>
					</div>
				</div>
				<div class="col-md-5 col-sm-12 shadow-lg p-5 bg-light"
					style="margin-bottom: 10px;">
					<div class="text-center">
						<h4 class="text-secondary">Quên mật khẩu</h4>
					</div>
					<div class="p-4">
						<form method="POST"
							action="/SOF3021_IT17103_ASSIGNMENT/account/verify-password">
							<div class="input-group mb-3">
								<h4>Mã xác nhận đã được gửi tới email</h4>
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-puzzle-fill text-white" title="Verify Code"></i></span> <input
									type="text" class="form-control" name="verifyCode"
									placeholder="Mã xác nhận" />
							</div>
							<div class="col" style="color: red; margin-left: 40px;">
								<p style="font-size: 20px">${fPasswordErrorMessage}</p>
							</div>
							<div class="d-grid col-12 mx-auto">
								<button class="btn btn-secondary">Submit</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>