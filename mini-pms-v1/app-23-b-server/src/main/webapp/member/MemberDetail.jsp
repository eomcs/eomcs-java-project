<%@page import="com.eomcs.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원상세</title>
  <style>
  label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
  }
  </style>
</head>
<body>
<h1>회원 상세(MVC)</h1>
<%
Member member = (Member) request.getAttribute("member");
%>
<form action='update'>
    <label for='f-no'>번호</label> 
    <input id='f-no' type='text' name='no' value='<%=member.getNo()%>' readonly><br>
    
    <label for='f-name'>이름</label>
    <input id='f-name' type='text' name='name' value='<%=member.getName()%>'><br>
    
    <label for='f-email'>이메일</label> 
    <input id='f-email' type='email' name='email' value='<%=member.getEmail()%>'><br>
    
    <label for='f-password'>암호</label> 
    <input id='f-password' type='password' name='password'><br>
    
    <label for='f-photo'>사진</label> 
    <input id='f-photo' type='text' name='photo' value='<%=member.getPhoto()%>'><br>
    
    <label for='f-tel'>전화</label> 
    <input id='f-tel' type='tel' name='tel' value='<%=member.getTel()%>'><br>
    
    <label for='f-registeredDate'>등록일</label> 
    <span id='f-registeredDate'><%=member.getRegisteredDate()%></span><br>
<button>변경</button>
 <a href='delete?no=<%=member.getNo()%>'>[삭제]</a> <a href='list'>[목록]</a><br>
</form>

</body>
</html>
