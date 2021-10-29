package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
public class MemberAddController extends HttpServlet {
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

    Member member = new Member();

    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));

    try {
      memberDao.insert(member);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("MemberAdd.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}







