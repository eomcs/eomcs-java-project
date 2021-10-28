<%@page import="com.eomcs.pms.domain.Member"%>
<%@page import="com.eomcs.pms.dao.MemberDao"%>
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
<h1>회원 상세</h1>
<%
int no = Integer.parseInt(request.getParameter("no"));
Member member = memberDao.findByNo(no);

if (member == null) {%>
    해당 번호의 회원이 없습니다.
<%
} else {
%>
<form action='MemberUpdate.jsp'>
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
 <a href='MemberDelete.jsp?no=<%=member.getNo()%>'>[삭제]</a> <a href='MemberList.jsp'>[목록]</a><br>
</form>
<%}%>
</body>
</html>
<%! // <== declaration element(tag)
// 자바 서블릿 클래스를 만들 때 그 클래스에 들어갈 변수와 메서드를 이 태그 안에 작성한다.
    MemberDao memberDao;

    public void jspInit() {
      ServletConfig config = getServletConfig();
      ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
      memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    }
%>









