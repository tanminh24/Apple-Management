<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Product Manager"></c:set>
<c:set var="feature" scope="session" value="Quản lý sản phẩm"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />

<body style="background-color: #6c757d;">
	<div class="container-fluid vh-100" style="margin-top: 30px">
		<div class="" style="margin-top: 30px;">
			<div class="rounded d-flex justify-content-center">
				<!-- LEFT -->
				<div class="col-md-4 col-sm-12 bg-light"
					style="height: 860px; margin-bottom: 40px">
					<div class="p-4" style="margin-bottom: -60px;">
						<div class="text-center">
							<h4 class="text-secondary">Thông tin sản phẩm</h4>
						</div>
						<f:form modelAttribute="product" method="POST"
							action="/SOF3021_IT17103_ASSIGNMENT/"
							enctype="multipart/form-data">
							<div class="input-group mb-3">
								<img src="${product.linkImage}" class="rounded mx-auto d-block"
									style="width: 200px; border: 1px solid darkgray;">
							</div>
							<div class="input-group mb-3" style="margin-top: 30px">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-image-fill text-white" title="Image"></i></span> <input
									type="file" class="form-control" name="file" />
							</div>
							<div class="input-group mb-3">
								<f:input type="hidden" class="form-control" name="id" path="id"
									placeholder="Id" />
							</div>
							<div class="col"
								style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${idErrorMessage}</p>
							</div>
							<div class="input-group mb-3">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-person-plus-fill text-white" title="Name"></i></span>
								<f:input type="text" class="form-control" name="name"
									path="name" placeholder="Tên sản phẩm" />
							</div>
							<div
								style="color: red; margin-top: -10px; margin-left: 40px; margin-bottom: -20px;">
								<f:errors path="name" element="small" delimiter="<br>"></f:errors>
							</div>
							<div class="col"
								style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${nameErrorMessage}</p>
							</div>
							<div class="input-group mb-3" style="margin-top: 30px">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-key-fill text-white" title="Price"></i></span>
								<f:input type="text" class="form-control" name="price"
									path="price" placeholder="Giá tiền" />
							</div>
							<div
								style="color: red; margin-top: -15px; margin-left: 40px; margin-bottom: -20px;">
								<f:errors path="price" element="small" delimiter="<br>"></f:errors>
							</div>
							<div class="input-group mb-3" style="margin-top: 30px">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-key-fill text-white" title="Price"></i></span>
								<f:input type="text" class="form-control" name="quantity"
									path="quantity" placeholder="Số lượng" />
							</div>
							<div
								style="color: red; margin-top: -15px; margin-left: 40px; margin-bottom: -20px;">
								<f:errors path="quantity" element="small" delimiter="<br>"></f:errors>
							</div>
							<div class="input-group mb-3" style="margin-top: 30px">
								<span class="input-group-text bg-secondary"><i
									class="bi bi-key-fill text-white" title="Price"></i></span>
								<f:select path="categoryId" class="form-select">
									<f:options items="${categories}" itemValue="id"
										itemLabel="name" value="" />
								</f:select>
							</div>
							<div style="color: red; margin-top: -15px; margin-left: 40px;">
								<f:errors path="categoryId" element="small" delimiter="<br>"></f:errors>
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
							<div class="row" style="margin-top: 0px">
								<div class="col-md-3">
									<button style="width: 100%; height: 100%;"
										class="btn btn-outline-success"
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/product/create">Thêm
										mới</button>
								</div>
								<div class="col-md-3">
									<button style="width: 100%; height: 100%;"
										class="btn btn-outline-success"
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/product/update">Cập
										nhật</button>
								</div>
								<div class="col-md-3">
									<a style="width: 100%; height: 100%;"
										class="btn btn-outline-danger"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/delete/${product.id}"
										role="button">Xóa bỏ</a>
								</div>
								<div class="col-md-3">
									<a style="width: 100%; height: 100%;"
										class="btn btn-outline-primary"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/reset" role="button">Làm
										mới</a>
								</div>
							</div>
							<div style="margin-top: 20px">
								<ul class="list-group">
									<li class="list-group-item"><b>Ngày tạo:</b> <fmt:formatDate
											value="${product.createdDate}" pattern="HH:mm:ss dd/MM/yyyy" />
									</li>
									<li class="list-group-item"><b>Người tạo:</b>
										${product.createdUserId.username}</li>
									<li class="list-group-item"><b>Ngày cập nhật gần nhất:</b>
										<fmt:formatDate value="${product.lastModifiedDate}"
											pattern="HH:mm:ss dd/MM/yyyy" /></li>
									<li class="list-group-item"><b>Người cập nhật gần
											nhất:</b> ${product.lastModifiedUserId.username}</li>
								</ul>
							</div>
						</f:form>
					</div>
				</div>
				<!-- RIGHT -->
				<div class="col-md-8 col-sm-12 bg-light" style="height: 860px;">
					<div class="p-4" style="margin-bottom: -60px;">
						<div class="">
							<h4 class="text-secondary">Bộ lọc</h4>
						</div>
						<form action="/SOF3021_IT17103_ASSIGNMENT/admin/product/search"
							method="post">
							<div class="row g-3">
								<div class="col-sm-3">
									<input type="text" class="form-control" name="min"
										value="${param.min}" placeholder="Giá từ" />
								</div>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="max"
										value="${param.max}" placeholder="Đến" />
								</div>
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
										formaction="/SOF3021_IT17103_ASSIGNMENT/admin/product/manager">Search</button>
									<a type="button" class="btn btn-secondary"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/reset">Reset</a>
								</div>
							</div>
						</form>
						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">DANH MỤC</th>
									<th scope="col">TÊN SẢN PHẨM</th>
									<th scope="col">SỐ LƯỢNG</th>
									<th scope="col">GIÁ</th>
									<th scope="col">TRẠNG THÁI</th>
									<th scope="col">#</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${page.content}">
									<tr>
										<td>${item.id}</td>
										<td>${item.categoryId.name}</td>
										<td>${item.name}</td>
										<td>${item.quantity}</td>
										<td><fmt:setLocale value="vi_VN" scope="session" /> <fmt:formatNumber
												type="currency" value="${item.price}" /></td>
										<td><c:if test="${item.available == 0}">Đang bán</c:if> <c:if
												test="${item.available == 1}">Ngưng bán</c:if></td>
										<td><a type="button" class="btn btn-secondary"
											href="/SOF3021_IT17103_ASSIGNMENT/admin/product/detail/${item.id}">Chi
												tiết</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="row text-center">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/manager?p=0"
										class="btn btn-outline-primary bi bi-chevron-double-left"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/manager?p=${page.number-1}"
										class="btn btn-outline-primary bi bi-chevron-left"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><p style="margin: 5px 3px 0px;">${page.number + 1}
											/ ${page.totalPages}</p></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/manager?p=${page.number+1}"
										class="btn btn-outline-primary bi bi-chevron-right"
										style="margin: 0px 3px 0px;"></a></li>
									<li class="page-item"><a type="button"
										href="/SOF3021_IT17103_ASSIGNMENT/admin/product/manager?p=${page.totalPages-1}"
										class="btn btn-outline-primary bi bi-chevron-double-right"
										style="margin: 0px 3px 0px;"></a></li>
								</ul>
							</nav>
						</div>
						<div class="p-4"">
							<form method="POST" action="/SOF3021_IT17103_ASSIGNMENT/admin/product/import-from-excel"
								enctype="multipart/form-data">
								<div class="row">
									<div class="row col-md-6">
										<input type="file" class="form-control" name="fileExcel" />
									</div>
									<div class="col-md-3">
										<button class="btn btn-primary">Thêm mới từ Excel</button>
									</div>
									<div class="col-md-3">
										<a type="button" class="btn btn-success"
											href="/SOF3021_IT17103_ASSIGNMENT/admin/product/export-file-excel">Xuất file Excel</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>