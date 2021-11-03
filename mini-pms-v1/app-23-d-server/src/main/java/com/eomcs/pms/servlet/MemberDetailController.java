package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@WebServlet("/member/detail")
public class MemberDetailController extends GenericServlet {
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

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findByNo(no);

      if (member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      request.setAttribute("member", member);
      request.getRequestDispatcher("/member/MemberDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







