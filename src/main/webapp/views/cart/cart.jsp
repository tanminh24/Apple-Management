<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Home"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/header.jsp" />

<body>
	<div class="container" style="margin-top: 20px; height: 500px">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">TÊN SẢN PHẨM</th>
					<th scope="col">SỐ LƯỢNG</th>
					<th scope="col">ĐƠN GIÁ</th>
					<th scope="col">THÀNH TIỀN</th>
					<th scope="col">#</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${cart.items}">
					<form action="/SOF3021_IT17103_ASSIGNMENT/cart/update/${item.id}"
						method="post">
						<input type="hidden" name="id" value="${item.id}">
						<tr>
							<td>${item.name}</td>
							<td><input name="quantity" value="${item.quantity}"
								onblur="this.form.submit()" style="width: 50px;"></td>
							<td><fmt:setLocale value="vi_VN" scope="session" /> <fmt:formatNumber
									type="currency" value="${item.price}" /></td>
							<td><fmt:setLocale value="vi_VN" scope="session" /> <fmt:formatNumber
									type="currency" value="${item.price * item.quantity}" /></td>
							<td><a  style="width: 60%" class="btn btn-warning"
								href="/SOF3021_IT17103_ASSIGNMENT/cart/remove/${item.id}">Remove</a></td>
						</tr>
					</form>
				</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td><b>Tổng tiền:</b></td>
						<td><fmt:setLocale value="vi_VN" scope="session" /> <fmt:formatNumber
									type="currency" value="${cart.amount}" /></td>
						<td><a style="width: 60%" class="btn btn-success"
								href="/SOF3021_IT17103_ASSIGNMENT/cart/pay/${user.username}">Thanh toán</a></td>
					</tr>
			</tbody>
		</table>
	</div>
</body>
<jsp:include page="../layout/footer.jsp" />
</html>