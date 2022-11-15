<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<header>
    <nav class="navbar bg-light">
        <div class="container">
            <nav class="navbar navbar-expand-lg bg-light navbar-dark" style="width: 100%;margin-top: -16px;">
                <div style="text-align: center">
                    <a class="navbar-brand" href="http://localhost:8080/SOF3021_IT17103_ASSIGNMENT/home">
                        <i class="bi bi-apple" style="font-size: 65px; color: #6c757d;"></i>
                        <span style="font-size: 40px; margin-left: 8%; color: #6c757d;"><c:out value="${feature}"/></span>
                    </a>
                </div>
            </nav>
        </div>
    </nav>
</header>
