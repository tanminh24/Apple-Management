<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Order"></c:set>
<c:set var="feature" scope="session" value="Hóa đơn"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />
<body style="background-color: #6c757d;">
	<div class="container-fluid vh-100">
		<div class="">
			<div class="rounded d-flex justify-content-center">
				<h1>Hóa đơn</h1>
			</div>
		</div>
	</div>
</body>
</html>