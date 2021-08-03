package com.eomcs.pms.menu;

// 게시글 등록 메뉴를 선택했을 때 작업을 수행할 객체를 정의한다.
// 단 ActionListener 규칙에 따라 메서드를 정의한다.
// 그래야만 MenuItem에 이 객체를 등록할 수 있다.
// 그리고 MenuItem도 이 규칙에 따라 호출할 수 있다.
//
public class BoardListListener implements ActionListener {
  @Override
  public void doAction() {
    System.out.println("=====> 게시글 목록!!!");

  }
}
