<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원목록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<h1>회원 목록(MVC + EL + JSTL + Bootstrap)</h1>
<a href='form' class="btn btn-outline-primary btn-sm">새회원</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${memberList}" var="member">
<tr>
    <td>${member.no}</td>
    <td><a href='detail?no=${member.no}'>${member.name}</a></td> 
    <td>${member.email}</td> 
    <td>${member.tel}</td> 
    <td>${member.registeredDate}</td>
</tr>
</c:forEach>

</tbody>
</table>
</div><!-- .container -->
</body>
</html>








