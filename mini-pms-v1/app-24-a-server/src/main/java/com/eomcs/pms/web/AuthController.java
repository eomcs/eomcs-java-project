package com.eomcs.pms.web;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@Controller
public class AuthController {

  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/auth/loginForm")
  public ModelAndView loginForm() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "auth/LoginForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/auth/login")
  public ModelAndView login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {
    Cookie cookie = null;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      //cookie.setPath(sc.getContextPath() + "/app/auth"); // 예) http://localhost:8080/pms/app/auth

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }
    response.addCookie(cookie);

    Member member = memberDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (member != null) {
      session.setAttribute("loginUser", member);
      mv.setViewName("redirect:../member/list");

    } else {
      mv.addObject("refresh", "2;url=loginForm");
      mv.addObject("pageTitle", "로그인오류!");
      mv.addObject("contentUrl", "auth/LoginFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  }

  @GetMapping("/auth/logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:loginForm");
    return mv;
  }

}







