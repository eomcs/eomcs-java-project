package com.eomcs.pms.menu;

import com.eomcs.pms.handler.MemberHandler;

// Composite 패턴에서 Leaf 역할을 할 메뉴 항목을 정의한다.
public class MemberDeleteMenu extends Menu {

  MemberHandler memberHandler;

  public MemberDeleteMenu(MemberHandler memberHandler) {
    super("삭제");
    this.memberHandler = memberHandler;
  }

  @Override 
  public void execute() {
    memberHandler.delete(); 
  } 
}
