<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${contextPath}/home">PMS</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${contextPath}/board/list">게시글</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${contextPath}/member/list">회원</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${contextPath}/project/list">프로젝트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${contextPath}/task/list">작업</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="검색어" aria-label="Search">
        <button class="btn btn-outline-success btn-sm" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
</header>