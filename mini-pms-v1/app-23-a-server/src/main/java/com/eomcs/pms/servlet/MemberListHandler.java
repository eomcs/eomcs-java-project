package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;


@WebServlet("/member/list")
public class MemberListHandler extends GenericServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록</h1>");
    out.println("<a href='form'>새회원</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>이름</th>");
    out.println("    <th>이메일</th>");
    out.println("    <th>전화</th>");
    out.println("    <th>등록일</th>");
    out.println("  </tr>");
    out.println("</thead>");
    out.println("<tbody>");
    try {
      Collection<Member> memberList = memberDao.findAll();

      for (Member member : memberList) {
        out.printf("<tr>"
            + "<td>%d</td>"
            + " <td><a href='detail?no=%1$d'>%s</a></td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + "</tr>\n", 
            member.getNo(), 
            member.getName(), 
            member.getEmail(), 
            member.getTel(), 
            member.getRegisteredDate());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}







