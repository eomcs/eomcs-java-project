package com.eomcs.pms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
  @GetMapping("/home")
  public ModelAndView home() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "메인화면");
    mv.addObject("contentUrl", "home.jsp");
    mv.setViewName("template1");
    return mv;
  }
}







