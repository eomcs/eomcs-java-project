package com.eomcs.pms.handler;

public class AuthLogoutHandler implements Command {
  @Override
  public void execute() {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    System.out.println("로그아웃 하였습니다.");
  }
}







