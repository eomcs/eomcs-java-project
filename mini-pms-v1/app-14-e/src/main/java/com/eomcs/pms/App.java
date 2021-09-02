package com.eomcs.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.handler.AuthLoginHandler;
import com.eomcs.pms.handler.AuthLogoutHandler;
import com.eomcs.pms.handler.AuthUserInfoHandler;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.MemberAddHandler;
import com.eomcs.pms.handler.MemberDeleteHandler;
import com.eomcs.pms.handler.MemberDetailHandler;
import com.eomcs.pms.handler.MemberListHandler;
import com.eomcs.pms.handler.MemberPrompt;
import com.eomcs.pms.handler.MemberUpdateHandler;
import com.eomcs.pms.handler.ProjectAddHandler;
import com.eomcs.pms.handler.ProjectDeleteHandler;
import com.eomcs.pms.handler.ProjectDetailHandler;
import com.eomcs.pms.handler.ProjectListHandler;
import com.eomcs.pms.handler.ProjectUpdateHandler;
import com.eomcs.pms.handler.TaskAddHandler;
import com.eomcs.pms.handler.TaskDeleteHandler;
import com.eomcs.pms.handler.TaskDetailHandler;
import com.eomcs.pms.handler.TaskListHandler;
import com.eomcs.pms.handler.TaskUpdateHandler;
import com.eomcs.util.Prompt;

public class App {
  List<Board> boardList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();
  List<Project> projectList = new ArrayList<>();

  BoardAddHandler boardAddHandler = new BoardAddHandler(boardList);
  BoardListHandler boardListHandler = new BoardListHandler(boardList);
  BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardList);
  BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardList);
  BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardList);
  BoardSearchHandler boardSearchHandler = new BoardSearchHandler(boardList);

  MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
  MemberListHandler memberListHandler = new MemberListHandler(memberList);
  MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList);
  MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);
  MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList);
  MemberPrompt memberPromptHandler = new MemberPrompt(memberList);

  ProjectAddHandler projectAddHandler = new ProjectAddHandler(projectList, memberPromptHandler);
  ProjectListHandler projectListHandler = new ProjectListHandler(projectList);
  ProjectDetailHandler projectDetailHandler = new ProjectDetailHandler(projectList);
  ProjectUpdateHandler projectUpdateHandler = new ProjectUpdateHandler(projectList, memberPromptHandler);
  ProjectDeleteHandler projectDeleteHandler = new ProjectDeleteHandler(projectList);

  TaskAddHandler taskAddHandler = new TaskAddHandler(projectListHandler);
  TaskListHandler taskListHandler = new TaskListHandler(projectListHandler);
  TaskDetailHandler taskDetailHandler = new TaskDetailHandler(projectListHandler);
  TaskUpdateHandler taskUpdateHandler = new TaskUpdateHandler(projectListHandler);
  TaskDeleteHandler taskDeleteHandler = new TaskDeleteHandler(projectListHandler);

  AuthLoginHandler authLoginHandler = new AuthLoginHandler(memberList);
  AuthLogoutHandler authLogoutHandler = new AuthLogoutHandler();
  AuthUserInfoHandler authUserInfoHandler = new AuthUserInfoHandler();

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authLoginHandler.execute(); 
      }
    });

    mainMenuGroup.add(new Menu("내정보", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        authUserInfoHandler.execute(); 
      }
    });

    mainMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        authLogoutHandler.execute(); 
      }
    });

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        boardAddHandler.execute(); 
      }});
    boardMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        boardListHandler.execute(); 
      }});
    boardMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        boardDetailHandler.execute(); 
      }});
    boardMenu.add(new Menu("변경", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        boardUpdateHandler.execute(); 
      }});
    boardMenu.add(new Menu("삭제", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        boardDeleteHandler.execute(); 
      }});
    boardMenu.add(new Menu("검색") {
      @Override
      public void execute() {
        boardSearchHandler.execute(); 
      }});

    MenuGroup memberMenu = new MenuGroup("회원");
    mainMenuGroup.add(memberMenu);

    memberMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        memberAddHandler.execute(); 
      }});
    memberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        memberListHandler.execute(); 
      }});
    memberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberDetailHandler.execute(); 
      }});
    memberMenu.add(new Menu("변경", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        memberUpdateHandler.execute(); 
      }});
    memberMenu.add(new Menu("삭제", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        memberDeleteHandler.execute(); 
      }});

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    mainMenuGroup.add(projectMenu);

    projectMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        projectAddHandler.execute(); 
      }});
    projectMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        projectListHandler.execute(); 
      }});
    projectMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        projectDetailHandler.execute(); 
      }});
    projectMenu.add(new Menu("변경", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        projectUpdateHandler.execute(); 
      }});
    projectMenu.add(new Menu("삭제", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        projectDeleteHandler.execute(); 
      }});

    MenuGroup taskMenu = new MenuGroup("작업");
    mainMenuGroup.add(taskMenu);

    taskMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        taskAddHandler.execute(); 
      }});
    taskMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        taskListHandler.execute(); 
      }});
    taskMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        taskDetailHandler.execute(); 
      }});
    taskMenu.add(new Menu("변경", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        taskUpdateHandler.execute(); 
      }});
    taskMenu.add(new Menu("삭제", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        taskDeleteHandler.execute(); 
      }});


    return mainMenuGroup;
  }
}












