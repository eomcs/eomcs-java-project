package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/member/update")
public class MemberUpdateHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberDao.findByNo(no);

      if (member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");

      } else {

        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));
        member.setPhoto(request.getParameter("photo"));
        member.setTel(request.getParameter("tel"));

        memberDao.update(member);
        sqlSession.commit();
      }

      // 리다이렉트(redirect)
      // 서버의 응답을 받는 즉시 지정한 URL로 요청하도록 웹브라우저에게 명령한다.
      // 리다이렉트의 경우 서버는 응답할 때 내용을 보내지 않는다.
      response.sendRedirect("list");

    } catch (Exception e) {
      // 변경 오류가 발생했을 때만 출력하게 한다.
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("  <title>회원변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원변경결과</h1>");
      out.println("회원 변경 오류!");
      out.println("<a href='list'>[목록]</a><br>");
      out.println("</body>");
      out.println("</html>");
      e.printStackTrace();
    }
  }
}







