package com.eomcs.pms;

import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.ProjectHandler;
import com.eomcs.pms.handler.TaskHandler;
import com.eomcs.pms.menu.BoardAddMenu;
import com.eomcs.pms.menu.BoardDeleteMenu;
import com.eomcs.pms.menu.BoardDetailMenu;
import com.eomcs.pms.menu.BoardListMenu;
import com.eomcs.pms.menu.BoardUpdateMenu;
import com.eomcs.pms.menu.MemberAddMenu;
import com.eomcs.pms.menu.MemberDeleteMenu;
import com.eomcs.pms.menu.MemberDetailMenu;
import com.eomcs.pms.menu.MemberListMenu;
import com.eomcs.pms.menu.MemberUpdateMenu;
import com.eomcs.pms.menu.Menu;
import com.eomcs.pms.menu.MenuGroup;
import com.eomcs.pms.menu.ProjectAddMenu;
import com.eomcs.pms.menu.ProjectDeleteMenu;
import com.eomcs.pms.menu.ProjectDetailMenu;
import com.eomcs.pms.menu.ProjectListMenu;
import com.eomcs.pms.menu.ProjectUpdateMenu;
import com.eomcs.pms.menu.TaskAddMenu;
import com.eomcs.pms.menu.TaskDeleteMenu;
import com.eomcs.pms.menu.TaskDetailMenu;
import com.eomcs.pms.menu.TaskListMenu;
import com.eomcs.pms.menu.TaskUpdateMenu;
import com.eomcs.util.Prompt;

public class App {

  static BoardHandler boardHandler = new BoardHandler();
  static MemberHandler memberHandler = new MemberHandler();
  static ProjectHandler projectHandler = new ProjectHandler(memberHandler);
  static TaskHandler taskHandler = new TaskHandler(memberHandler);

  public static void main(String[] args) {

    Menu mainMenu = createMenu();
    mainMenu.execute();

    Prompt.close();
  }

  static Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new BoardAddMenu(boardHandler));
    boardMenu.add(new BoardListMenu(boardHandler));
    boardMenu.add(new BoardDetailMenu(boardHandler));
    boardMenu.add(new BoardUpdateMenu(boardHandler));
    boardMenu.add(new BoardDeleteMenu(boardHandler));

    MenuGroup memberMenu = new MenuGroup("회원");
    mainMenuGroup.add(memberMenu);

    memberMenu.add(new MemberAddMenu(memberHandler));
    memberMenu.add(new MemberListMenu(memberHandler));
    memberMenu.add(new MemberDetailMenu(memberHandler));
    memberMenu.add(new MemberUpdateMenu(memberHandler));
    memberMenu.add(new MemberDeleteMenu(memberHandler));

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    mainMenuGroup.add(projectMenu);

    projectMenu.add(new ProjectAddMenu(projectHandler));
    projectMenu.add(new ProjectListMenu(projectHandler));
    projectMenu.add(new ProjectDetailMenu(projectHandler));
    projectMenu.add(new ProjectUpdateMenu(projectHandler));
    projectMenu.add(new ProjectDeleteMenu(projectHandler));

    MenuGroup taskMenu = new MenuGroup("작업");
    mainMenuGroup.add(taskMenu);

    taskMenu.add(new TaskAddMenu(taskHandler));
    taskMenu.add(new TaskListMenu(taskHandler));
    taskMenu.add(new TaskDetailMenu(taskHandler));
    taskMenu.add(new TaskUpdateMenu(taskHandler));
    taskMenu.add(new TaskDeleteMenu(taskHandler));

    return mainMenuGroup;
  }
}












