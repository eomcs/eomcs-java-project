package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/member/delete")
public class MemberDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  MemberDao memberDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
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
      }

      memberDao.delete(no);
      sqlSession.commit();

      request.setAttribute("contentUrl", "redirect:list");

    } catch (Exception e) {
      request.setAttribute("error", e);
    }
  }
}







