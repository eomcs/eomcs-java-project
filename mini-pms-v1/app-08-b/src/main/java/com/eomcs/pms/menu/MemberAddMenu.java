package com.eomcs.pms.menu;

import com.eomcs.pms.handler.MemberHandler;

// Composite 패턴에서 Leaf 역할을 할 메뉴 항목을 정의한다.
public class MemberAddMenu extends Menu {

  MemberHandler memberHandler;

  public MemberAddMenu(MemberHandler memberHandler) {
    super("등록");
    this.memberHandler = memberHandler;
  }

  @Override 
  public void execute() {
    memberHandler.add(); 
  } 
}
