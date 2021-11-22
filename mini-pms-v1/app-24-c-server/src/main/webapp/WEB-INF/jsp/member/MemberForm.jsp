<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<h1>새회원</h1>
<form action='add' method='post' enctype="multipart/form-data">
<div class="mb-3 row">
  <label for='f-name' class="col-sm-2 col-form-label">이름</label>
  <div class="col-sm-6">
    <input id='f-name' type='text' name='name' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
  <div class="col-sm-7">
    <input id='f-email' type='email' name='email' class="form-control">
    <div class="invalid-feedback">
        이미 존재하는 이메일입니다.
    </div>
  </div>
  <div class="col-auto">
    <button id="x-email-check-btn" type="button" class="btn btn-primary form-control">중복검사</button>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">암호</label>
  <div class="col-sm-6">
    <input id='f-password' type='password' name='password' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-photo' class="col-sm-2 col-form-label">사진</label> 
  <div class="col-sm-10">
    <input id='f-photo' type='file' name='photoFile' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-tel' class="col-sm-2 col-form-label">전화</label> 
  <div class="col-sm-6">
    <input id='f-tel' type='tel' name='tel' class="form-control">
  </div>
</div>
<button id="x-add-btn" class="btn btn-primary btn-sm">등록</button><br>
</form>
<script>
var addBtn = document.querySelector("#x-add-btn");
var emailTag = document.querySelector("#f-email");
addBtn.setAttribute("disabled", "disabled");

document.querySelector("#x-email-check-btn").onclick = () => {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
    	if (this.responseText == "false") {
    	    addBtn.removeAttribute("disabled");
    	    emailTag.classList.remove("is-invalid");
    	} else {
    		addBtn.setAttribute("disabled", "disabled");
    		emailTag.classList.add("is-invalid");
    	}
    })
    xhr.open("get", "checkEmail?email=" + emailTag.value);
    xhr.send();
};
</script>








