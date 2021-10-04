package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.menu.Menu;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class AuthLoginHandler implements Command {

  RequestAgent requestAgent;

  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT; // 기본은 로그아웃 된 상태이다.

  public static Member getLoginUser() {
    return loginUser;
  }
  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLoginHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일? ");
    String password = Prompt.inputString("암호? ");

    if (email.equals("root@test.com") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("root@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      return;
    } 

    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    requestAgent.request("member.selectOneByEmailPassword", params);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      Member member = requestAgent.getObject(Member.class);
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
      userAccessLevel = Menu.ACCESS_GENERAL;

    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }
  }
}







