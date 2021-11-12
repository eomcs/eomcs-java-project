<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<aside>

<c:if test="${empty sessionScope.loginUser}">
<a href="${contextPath}/auth/loginForm" class="btn btn-primary btn-sm">로그인</a>
</c:if>

<c:if test="${not empty sessionScope.loginUser}">
    <c:if test="${empty sessionScope.loginUser.photo}">
        <img src="https://via.placeholder.com/100">
    </c:if>
    
    <c:if test="${not empty sessionScope.loginUser.photo}">
		<img src="${contextPath}/upload/member/${sessionScope.loginUser.photo}_100x100.jpg">
    </c:if>
    ${sessionScope.loginUser.name}<br>
    <a href="${contextPath}/auth/logout" class="btn btn-primary btn-sm">로그아웃</a>
</c:if>

</aside>