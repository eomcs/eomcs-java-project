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


@WebServlet("/member/add")
public class MemberAddHandler extends HttpServlet {
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원등록결과</h1>");

    Member member = new Member();

    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));

    try {
      memberDao.insert(member);
      sqlSession.commit();

      out.println("회원을 등록했습니다.<br>");

      out.println("<a href='list'>[목록]</a><br>");

    } catch (Exception e) {
      out.println("목록 조회 오류!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

    // 리프래시(refresh)
    // 웹브라우저에게 서버가 보내준 HTML을 출력한 후 
    // 1초가 경과하면 지정한 URL을 다시 요청하도록 명령한다.
    response.setHeader("Refresh", "1;url=list");
  }
}







