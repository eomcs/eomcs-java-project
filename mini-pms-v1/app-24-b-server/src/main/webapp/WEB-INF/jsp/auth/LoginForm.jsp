<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>로그인</h1>
<form action='login' method='post'>
<div class="mb-3 row">
  <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
  <div class="col-sm-10">
    <input id='f-email' type='email' name='email' class="form-control" value="${cookie.email.value}">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">암호</label>
  <div class="col-sm-6">
    <input id='f-password' type='password' name='password' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-saveEmail' class="col-sm-2 col-form-label"></label>
  <div class="col-sm-10">
	<div class="form-check">
	  <input id="f-saveEmail" class="form-check-input" 
	  type="checkbox" name="saveEmail" ${not empty cookie.email ? "checked" : ""}>
	  <label class="form-check-label" for="flexCheckDefault">이메일 저장</label>
	</div>
  </div>
</div>
<button class="btn btn-primary btn-sm">로그인</button><br>
</form>










