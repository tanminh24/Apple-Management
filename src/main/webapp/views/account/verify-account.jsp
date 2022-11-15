<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Verify Account"></c:set>
<c:set var="feature" scope="session" value="Xác thực"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />
<body style="background-color: #6c757d;">
	<div class="container-fluid vh-100">
		<div class="">
			<div class="rounded d-flex justify-content-center">
				<div class="col-md-5 col-sm-12"
					style="margin-bottom: 50px; margin-right: 3%; background-color: #6c757d;">
					<div style="text-align: center; color: #ffffff;">
						<span class="navbar-brand"> <i class="bi bi-apple"
							style="font-size: 200px;"></i><br> <span
							style="font-size: 30px;">${verifyMessage}</span>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>