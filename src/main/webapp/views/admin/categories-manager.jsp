<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Category Manager"></c:set>
<c:set var="feature" scope="session" value="Quản lý danh mục"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />

<body style="background-color: #6c757d;">
	<div class="container-fluid vh-100" style="margin-top: 30px">
		<div class="" style="margin-top: 30px;">
			<div class="rounded d-flex justify-content-center">
				<!-- LEFT -->
				<div class="col-md-4 col-sm-12 bg-light"
					style="height: 500px; margin-bottom: -200px">
					<div class="p-4" style="margin-bottom: -60px;">
						<div class="text-center">
							<h4 class="text-secondary">Thông tin danh mục</h4>
						</div>
						<f:form modelAttribute="category" method="POST"
							action="/SOF3021_IT17103_ASSIGNMENT/"
							enctype="multipart/form-data">
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-123 text-white" title="ID"></i></span>
								<f:input type="text" class="form-control" name="id" path="id"
									placeholder="ID" />
							</div>
							<div
								style="color: red; margin-top: -10px; margin-left: 40px; margin-bottom: -20px;">
								<f:errors path="id" element="small" delimiter="<br>"></f:errors>
							</div>
							<div class="col"
								style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${idErrorMessage}</p>
							</div>
							<div class="input-group mb-3" style="margin-top: 30px">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-card-list text-white" title="Name"></i></span>
								<f:input type="text" class="form-control" name="name"
									path="name" placeholder="Tên danh mục" />
							</div>
							<div
								style="color: red; margin-top: -10px; margin-left: 40px; margin-bottom: -20px;">
								<f:errors path="name" element="small" delimiter="<br>"></f:errors>
							</div>
							<div class="col"
								style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${nameErrorMessage}</p>
							</div>
							<fieldset class="row mb-3" style="margin-top: 30px">
								<div class="col-sm-10" style="margin-left: 45px">
									<div class="form-check-inline">
										<f:radiobutton name="available" path="available"
											id="gridRadios1" value="0" />
										<label class="form-check-label" for="gridRadios1">
											Đang bán </label>
									</div>
									<div class="form-check-inline">
										<f:radiobutton name="available" path="available"
											id="gridRadios2" value="1" />
										<label class="form-check-label" for="gridRadios2">
											Ngưng bán </label>
									</div>
								</div>
								<div
									style="color: red; margin-top: -5px; margin-left: 40px; margin-bottom: -10px;">
									<f:errors path="available" element="small" delimiter="<br>"></f:errors>
								</div>
							</fieldset>
							<div class="row" style="margin-top: 30px">
								<div class="col-md-4">
									<button style="width: 100%; height: 100%;"
										class="btn btn-outline-success"
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/category/create">Thêm
										mới</button>
								</div>
								<div class="col-md-4">
									<button style="width: 100%; height: 100%;"
										class="btn btn-outline-success"
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/category/update">Cập
										nhật</button>
								</div>
								<div class="col-md-4">
									<a style="width: 100%; height: 100%;"
										class="btn btn-outline-primary"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/reset"
										role="button">Làm mới</a>
								</div>
							</div>
							<div style="margin-top: 20px">
								<ul class="list-group">
									<li class="list-group-item"><b>Số lượng sản phẩm:</b>
										${category.quantityProduct}</li>
								</ul>
							</div>
						</f:form>
					</div>
				</div>
				<!-- RIGHT -->
				<div class="col-md-8 col-sm-12 bg-light" style="height: 500px;">
					<div class="p-4" style="margin-bottom: -60px;">
						<div class="">
							<h4 class="text-secondary">Bộ lọc</h4>
						</div>
						<form action="/SOF3021_IT17103_ASSIGNMENT/admin/category/search"
							method="post">
							<div class="row g-3">
								<div class="col-sm-3">
									<input type="text" class="form-control" name="keywords"
										value="${param.keywords}" placeholder="Từ khóa" />
								</div>
								<div class="col-sm-3">
									<select class="form-select" name="status">
										<option value=0>Đang bán</option>
										<option value=1>Ngưng bán</option>
									</select>
								</div>
								<div class="col-sm-3">
									<button type="submit" class="btn btn-primary"
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/category/manager">Search</button>
									<a type="button" class="btn btn-secondary"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/reset">Reset</a>
								</div>
							</div>
						</form>
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">TÊN DANH MỤC</th>
									<th scope="col">SỐ LƯỢNG SẢN PHẨM</th>
									<th scope="col">TRẠNG THÁI</th>
									<th scope="col">#</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${page.content}">
									<tr>
										<td>${item.id}</td>
										<td>${item.name}</td>
										<td>${item.quantityProduct}</td>
										<td><c:if test="${item.available == 0}">Đang bán</c:if> <c:if
												test="${item.available == 1}">Ngưng bán</c:if></td>
										<td><a type="button" class="btn btn-secondary"
											href="/SOF3021_IT17103_ASSIGNMENT/admin/category/detail/${item.id}">Chi
												tiết</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="row text-center">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/manager?p=0"
										class="btn btn-outline-primary bi bi-chevron-double-left"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/manager?p=${page.number-1}"
										class="btn btn-outline-primary bi bi-chevron-left"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><p style="margin: 5px 3px 0px;">${page.number + 1}
											/ ${page.totalPages}</p></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/manager?p=${page.number+1}"
										class="btn btn-outline-primary bi bi-chevron-right"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/category/manager?p=${page.totalPages-1}"
										class="btn btn-outline-primary bi bi-chevron-double-right"
										style="margin: 0px 3px 0px;"></a></li>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>