<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Home"></c:set>
<jsp:include page="./layout/head.jsp" />
<jsp:include page="./layout/header.jsp" />

<body>
	<div class="container" style="margin-top: 20px">

		<c:if test="${ role == 1}">
			<div class="card border-secondary mb-3">
				<div class="card-header">
					<h4>Quản lý hệ thống</h4>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-2" style="margin-bottom: 10px;">
							<a style="width: 100%" class="btn btn-outline-secondary" href="#"
								role="button">Tài khoản</a>
						</div>
						<div class="col-2" style="margin-bottom: 10px;">
							<a style="width: 100%" class="btn btn-outline-secondary"
								href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/admin/category/manager"
								role="button">Danh mục</a>
						</div>
						<div class="col-2" style="margin-bottom: 10px;">
							<a style="width: 100%" class="btn btn-outline-secondary"
								href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/admin/product/manager"
								role="button">Sản phẩm</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<div class="card border-secondary mb-3">
			<div class="card-header">
				<h4>Danh mục</h4>
			</div>
			<div class="card-body">
				<div class="row">
					<c:forEach var="cate" items="${cates}">
						<div class="col-2" style="margin-bottom: 10px;">
							<a style="width: 100%" class="btn btn-outline-secondary" href="#"
								role="button">${cate.name}</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="card border-secondary mb-3">
			<div class="card-header">
				<h4>Sản phẩm mới</h4>
			</div>
			<div class="card-body text-secondary">
				<div class="row row-cols-1 row-cols-md-5 g-4">
					<c:forEach var="p" items="${prods}">
						<div class="col">
							<div class="card h-100">
								<a
									href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/product/detail/${p.id}">
									<img src="${ p.linkImage }" class="card-img-top" alt="...">
								</a>
								<div class="card-body">
									<a
										href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/product/detail/${p.id}"
										style="text-decoration: none; color: black">
										<h5 class="card-title">${ p.name }</h5>
									</a>
									<p class="card-text">
										<fmt:setLocale value="vi_VN" scope="session" />
										<fmt:formatNumber type="currency" value="${p.price}" />
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="card border-secondary mb-3">
			<div class="card-header">
				<h4>Bán chạy</h4>
			</div>
			<div class="card-body text-secondary">
				<div class="row row-cols-1 row-cols-md-5 g-4">
					<c:forEach var="p" items="${prods}">
						<div class="col">
							<div class="card h-100">
								<a
									href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/product/detail/${p.id}">
									<img src="${ p.linkImage }" class="card-img-top" alt="...">
								</a>
								<div class="card-body">
									<a
										href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/product/detail/${p.id}"
										style="text-decoration: none; color: black">
										<h5 class="card-title">${ p.name }</h5>
									</a>
									<p class="card-text">
										<fmt:setLocale value="vi_VN" scope="session" />
										<fmt:formatNumber type="currency" value="${p.price}" />
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</div>
</body>
<jsp:include page="./layout/footer.jsp" />
</html>