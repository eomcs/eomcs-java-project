package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/form")
public class MemberFormHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>새회원</title>");
    out.println("  <style>");
    out.println("  label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: right;");
    out.println("    display: inline-block;");
    out.println("    width: 60px;");
    out.println("  }");
    out.println("  </style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>새회원</h1>");

    out.println("<form action='add'>");
    out.println("<label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>");
    out.println("<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>");
    out.println("<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>");
    out.println("<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>");
    out.println("<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel'><br>");
    out.println("<button>등록</button><br>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}







