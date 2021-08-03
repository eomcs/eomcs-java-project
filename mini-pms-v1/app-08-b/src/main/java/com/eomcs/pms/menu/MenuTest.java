package com.eomcs.pms.menu;

public class MenuTest {

  public static void main(String[] args) {
    MenuGroup rootMenu = new MenuGroup("메인", true);
    MenuGroup boardMenu = new MenuGroup("게시판");
    MenuGroup memberMenu = new MenuGroup("회원");
    MenuGroup projectMenu = new MenuGroup("프로젝트");
    MenuGroup taskMenu = new MenuGroup("작업");

    rootMenu.add(boardMenu);
    rootMenu.add(memberMenu);
    rootMenu.add(projectMenu);
    rootMenu.add(taskMenu);

    MenuItem boardAddMenu = new MenuItem("등록");
    boardAddMenu.addActionListener(new BoardAddListener());

    MenuItem boardListMenu = new MenuItem("목록");
    boardListMenu.addActionListener(new BoardListListener());

    MenuItem boardDetailMenu = new MenuItem("상세조회");
    boardDetailMenu.addActionListener(new BoardDetailListener());

    MenuItem boardUpdateMenu = new MenuItem("변경");
    MenuItem boardDeleteMenu = new MenuItem("삭제");

    boardMenu.add(boardAddMenu);
    boardMenu.add(boardListMenu);
    boardMenu.add(boardDetailMenu);
    boardMenu.add(boardUpdateMenu);
    boardMenu.add(boardDeleteMenu);

    MenuItem memberAddMenu = new MenuItem("등록");
    MenuItem memberListMenu = new MenuItem("목록");
    MenuItem memberDetailMenu = new MenuItem("상세조회");
    MenuItem memberUpdateMenu = new MenuItem("변경");
    MenuItem memberDeleteMenu = new MenuItem("삭제");

    memberMenu.add(memberAddMenu);
    memberMenu.add(memberListMenu);
    memberMenu.add(memberDetailMenu);
    memberMenu.add(memberUpdateMenu);
    memberMenu.add(memberDeleteMenu);

    rootMenu.execute();

  }

}
