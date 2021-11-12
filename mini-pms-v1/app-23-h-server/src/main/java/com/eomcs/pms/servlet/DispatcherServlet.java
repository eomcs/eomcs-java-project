package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      // 클라이언트가 요청한 URL에서 페이지 컨트롤러의 경로를 추출한다.
      String pageControllerPath = request.getPathInfo(); // => /*

      // 페이지 컨트롤러를 인클루드 한다.
      response.setContentType("text/html;charset=UTF-8"); // 인클루드하는 쪽에서 콘텐트 타입을 설정해야 한다. 
      request.getRequestDispatcher(pageControllerPath).include(request, response);

      // 예외가 발생했는지 검사한다.
      Exception error = (Exception) request.getAttribute("error");

      if (error != null) {
        // 오류가 발생했다면(ServletRequest 보관소에 error 라는 이름으로 오류 정보가 들어 있다면)
        request.getRequestDispatcher("/Error.jsp").forward(request, response);

      } else {
        String contentUrl = (String) request.getAttribute("contentUrl");

        if (contentUrl.startsWith("redirect:")) {
          // 페이지 컨트롤러가 redirect를 요구할 경우,
          response.sendRedirect(contentUrl.substring(9));

        } else {
          // refresh 정보가 있다면 JSP를 실행하기 전에 응답 헤더에 등록한다.
          String refresh = (String) request.getAttribute("refresh");
          if (refresh != null) {
            response.setHeader("refresh", refresh);
          }
          // 페이지 컨트롤러의 작업 결과를 출력할 템플릿 뷰 컴포넌트(JSP)를 실행한다.
          request.getRequestDispatcher("/template1.jsp").include(request, response);
        }
      }      

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







