<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="${ product.name }"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/header.jsp" />

<body>
	<div class="container" style="margin-top: 20px">
		<div class="card mb-12" style="margin-bottom: 30px">
			<div class="row g-0">
				<div class="col-md-4" style="padding: 20px">
					<img src="${ product.linkImage }" class="img-fluid rounded-start"
						alt="...">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h1 class="card-title">${ product.name }</h1>
						<h3 style="color: rgb(177, 4, 4);" class="card-text">
							<fmt:setLocale value="vi_VN" scope="session" />
							<fmt:formatNumber type="currency" value="${product.price}" />
						</h3>
						<p class="card-text">
							<small class="text-muted"> Flash sale duy nhất ngày 29/02
								- giới hạn 50 suất : Giảm giá chỉ còn 15.690.000đ (Chỉ áp dụng
								thanh toán online 100% áp dụng cùng ưu đãi và các chương trình
								khác). Chỉ áp dụng thanh toán online 100% hoặc mua trực tiếp tại
								cửa hàng </small>
						</p>
						<div class="row">
							<div class="col-2">
								<a href="/SOF3021_IT17103_ASSIGNMENT/cart/order-detail/${product.id}" class="btn btn-success">Mua ngay</a>
							</div>
							<div class="col-4">
								<a href="/SOF3021_IT17103_ASSIGNMENT/cart/add/${product.id}" class="btn btn-primary">Thêm vào giỏ hàng</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../layout/footer.jsp" />
</html>