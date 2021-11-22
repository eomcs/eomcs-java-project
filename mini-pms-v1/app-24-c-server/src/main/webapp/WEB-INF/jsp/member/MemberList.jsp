<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
tr a {
    text-decoration: none;
    color: black;
}
tr a:visited {
    color: black;
}
tr:hover {
    cursor: pointer;
}
</style>

<h1>회원 목록</h1>
<a href='form' class="btn btn-outline-primary btn-sm">새회원</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화</th>
    <th>등록일</th>
    <th></th>
  </tr>
</thead>
<tbody>

<c:forEach items="${memberList}" var="member">
<tr data-no="${member.no}">
    <td>${member.no}</td>
    <td><a href='detail?no=${member.no}'>${member.name}</a></td> 
    <%-- 
    <td>${member.name}</td> 
    --%>
    <td>${member.email}</td> 
    <td>${member.tel}</td> 
    <td>${member.registeredDate}</td>
    <td>
        <button type="button" class="btn btn-primary x-delete-btn" 
                data-bs-toggle="modal" 
                data-bs-target="#memberModal"
                data-no="${member.no}">
            삭제
        </button>
    </td>
</tr>
</c:forEach>

</tbody>
</table>

<div class="modal fade" id="memberModal" tabindex="-1" aria-labelledby="memberModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="memberModalLabel">회원삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
<h1>회원 상세</h1>
<div class="mb-3 row">
  <label for='f-no' class="col-sm-2 col-form-label">번호</label>
  <div class="col-sm-6">
    <input id='f-no' type='text' class="form-control-plaintext" value='1' readonly>
  </div>
</div>
<div class="mb-3 row">
  <label for='f-name' class="col-sm-2 col-form-label">이름</label>
  <div class="col-sm-6">
    <input id='f-name' type='text' name='name' class="form-control" value="aaa">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
  <div class="col-sm-10">
    <input id='f-email' type='email' name='email' class="form-control" value="aaa@test.com">
  </div>
</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary">삭제</button>
      </div>
    </div>
  </div>
</div>

<script>
var modalNo = document.querySelector("#f-no");
var modalName = document.querySelector("#f-name");
var modalEmail = document.querySelector("#f-email");

document.querySelectorAll(".x-delete-btn").forEach((tag) => {
	tag.onclick = (e) => {
		e.stopPropagation();
		var no = e.target.getAttribute("data-no");
		var tr = document.querySelector("tbody tr[data-no='" + no + "']");
		var name = tr.querySelector("td:nth-child(2) a").textContent;
		var email = tr.querySelector("td:nth-child(3)").textContent;
		
		modalNo.value = no;
		modalName.value = name;
		modalEmail.value = email;
	}
});

document.querySelectorAll("tbody a").forEach((aTag) => {
	aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
	trTag.onclick = (e) => {
		//console.log(e.currentTarget.querySelector("a").href);
		//e.currentTarget.querySelector("a").click();
		window.location.href = e.currentTarget.querySelector("a").href;
		//window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
	};
});
</script>



