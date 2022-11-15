<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<c:set var="title" scope="session" value="Register"></c:set>
<c:set var="feature" scope="session" value="Đăng ký"></c:set>
<jsp:include page="../layout/head.jsp" />
<jsp:include page="../layout/acc-manager-header.jsp" />

<body style="background-color: #6c757d;">
    <div class="container-fluid vh-100" style="margin-top:30px">
        <div class="" style="margin-top:30px;">
            <div class="rounded d-flex justify-content-center">
                <jsp:include page="../layout/acc-manager-body.jsp" />
                <div class="col-md-5 col-sm-12 shadow-lg p-5 bg-light"
                    style="margin-bottom: 50px;">
                    <div class="text-center">
            			<h4 class="text-secondary">Đăng ký</h4>
         			</div>
                    <div class="p-4" style="margin-bottom: -60px;">
                        <f:form modelAttribute="acc" method="POST" action="/SOF3021_IT17103_ASSIGNMENT/account/register" enctype="multipart/form-data">
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-secondary"><i class="bi bi-person-plus-fill text-white"
                                        title="Username"></i></span>
                                <f:input type="text" class="form-control" name="username" path="username"
                                    placeholder="Tên đăng nhập" />
                            </div>
                            <div style="color: red; margin-top: -10px; margin-left: 40px; margin-bottom: -20px; ">
                                <f:errors path="username" element="small" delimiter="<br>"></f:errors>
                            </div>
                            <div class="col" style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${usernameErrorMessage}</p>
							</div>
                            <div class="input-group mb-3" style="margin-top: 30px">
                                <span class="input-group-text bg-secondary"><i class="bi bi-key-fill text-white"
                                        title="Password"></i></span>
                                <f:input type="password" class="form-control" name="password" path="password"
                                    placeholder="Mật khẩu" />
                            </div>
                            <div style="color: red; margin-top: -15px; margin-left: 40px; margin-bottom: -20px; ">
                                <f:errors path="password" element="small" delimiter="<br>"></f:errors>
                            </div>
                            <div class="input-group mb-3" style="margin-top: 30px">
                                <span class="input-group-text bg-secondary"><i class="bi bi-key-fill text-white"
                                        title="Repassword"></i></span>
                                <input type="password" class="form-control" name="rePassword"
                                    placeholder="Nhập lại mật khẩu" />
                            </div>
                            <div class="col" style="color: red; margin-top: -13px; margin-left: 40px; margin-bottom: -10px">
								<p style="font-size: 14px">${repasswordErrorMessage}</p>
							</div>
                            <div class="input-group mb-3" style="margin-top: 22px">
                                <span class="input-group-text bg-secondary"><i class="bi bi-filter-square-fill text-white"
                                        title="Fullname"></i></span>
                                <f:input type="text" class="form-control" name="fullname" path="fullname"
                                    placeholder="Họ tên" />
                            </div>
                            <div style="color: red; margin-top: -15px; margin-left: 40px; margin-bottom: -20px; ">
                                <f:errors path="fullname" element="small" delimiter="<br>"></f:errors>
                            </div>
                            <fieldset class="row mb-3" style="margin-top: 30px">
                                <div class="col-sm-10" style="margin-left: 45px">
                                    <div class="form-check-inline">
                                        <f:radiobutton name="gender" path="gender"
                                            id="gridRadios1" value="0" />
                                        <label class="form-check-label" for="gridRadios1"> Nam </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <f:radiobutton name="gender" path="gender"
                                            id="gridRadios2" value="1" />
                                        <label class="form-check-label" for="gridRadios2"> Nữ </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <f:radiobutton name="gender" path="gender"
                                            id="gridRadios3" value="2" />
                                        <label class="form-check-label" for="gridRadios3"> Khác </label>
                                    </div>
                                </div>
                                <div style="color: red; margin-top: -5px; margin-left: 40px; margin-bottom: -10px; ">
                                    <f:errors path="gender" element="small" delimiter="<br>"></f:errors>
                                </div>
                            </fieldset>
                            <div class="input-group mb-3">
                                <span class="input-group-text bg-secondary"><i class="bi bi-envelope-fill text-white"
                                        title="Email"></i></span>
                                <f:input type="text" class="form-control" name="email" path="email"
                                    placeholder="Email" />
                            </div>
                            <div style="color: red; margin-top: -15px; margin-left: 40px; margin-bottom: -20px; ">
                                <f:errors path="email" element="small" delimiter="<br>"></f:errors>
                            </div>
                            <div class="col" style="color: red; margin-top: 24px; margin-left: 40px; margin-bottom: -17px">
								<p style="font-size: 14px">${emailErrorMessage}</p>
							</div>
                            <div class="input-group mb-3" style="margin-top: 30px">
                                <span class="input-group-text bg-secondary"><i class="bi bi-image-fill text-white"
                                        title="Image"></i></span>
                                <input type="file" class="form-control" name="file" />
                            </div>
                            <div class="d-grid col-12 mx-auto">
                                <button class="btn btn-secondary">Đăng ký</button>
                            </div>
                            <p class="text-center mt-3">Đã có tài khoản?
                                <a class="text-secondary" style="text-decoration: none;" href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/account/login/form">
                                Đăng nhập</a>
                            </p>
                        </f:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>