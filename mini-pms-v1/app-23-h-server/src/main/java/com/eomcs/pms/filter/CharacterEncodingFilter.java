package com.eomcs.pms.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // 서블릿에서 getParameter()로 데이터를 꺼내기 전에 
    // 웹브라우저가 보낸 데이터의 인코딩을 알려준다.
    request.setCharacterEncoding("UTF-8");

    // 다음 필터 또는 서블릿 실행
    chain.doFilter(request, response);

  }
}
