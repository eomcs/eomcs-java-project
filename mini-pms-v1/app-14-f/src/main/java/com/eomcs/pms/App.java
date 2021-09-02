package com.eomcs.pms;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.eomcs.pms.handler.Command;
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
import com.eomcs.pms.handler.ProjectPrompt;
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

  HashMap<String,Command> commandMap = new HashMap<>();

  MemberPrompt memberPrompt = new MemberPrompt(memberList);
  ProjectPrompt projectPrompt = new ProjectPrompt(projectList);

  // Menu 추상 클래스를 상속 받아서 PMS 시스템에 맞게 기능을 추가한다.
  class MenuItem extends Menu {

    // inner 클래스는 컴파일할 때 바깥 클래스의 인스턴스를 저장할 필드가 자동 생성된다.
    // 개발자가 따로 선언할 필요가 없다.
    // 예) App this$0;

    // 1) 메뉴의 ID를 저장할 필드를 선언한다.
    // - 이 메뉴 아이디는 커맨드 객체를 찾을 때 사용할 것이다.
    String menuId;

    // inner 클래스의 생성자를 컴파일 할 때 
    // 바깥 클래스의 인스턴스를 받는 파라미터가 자동으로 추가된다.
    // 개발자가 따로 파라미터를 추가할 필요가 없다.
    public MenuItem(/*App outer,*/ String title, String menuId) {
      this(/*outer,*/ title, ENABLE_ALL, menuId); // 다른 생성자와 코드가 중복 된다면 다른 생성자를 호출하라.
    }

    public MenuItem(/*App outer,*/String title, int enableState, String menuId) {
      super(title, enableState);
      this.menuId = menuId;
      //this$0 = outer;
    }

    @Override
    public void execute() {
      // inner 클래스는 바깥 클래스의 인스턴스를 내부 필드로 갖고 있기 때문에
      // inner 클래스의 멤버를 마음대로 사용할 수 있다.

      // 메뉴가 실행될 때 메뉴 아이디를 사용하여 Map에서 Command 객체를 찾아 실행한다.
      Command command = commandMap.get(menuId);
      command.execute();
    }
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  public App() {
    commandMap.put("/board/add", new BoardAddHandler(boardList));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/search", new BoardSearchHandler(boardList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList));

    commandMap.put("/project/add", new ProjectAddHandler(projectList, memberPrompt));
    commandMap.put("/project/list", new ProjectListHandler(projectList));
    commandMap.put("/project/detail", new ProjectDetailHandler(projectList));
    commandMap.put("/project/update", new ProjectUpdateHandler(projectList, memberPrompt));
    commandMap.put("/project/delete", new ProjectDeleteHandler(projectList));

    commandMap.put("/task/add", new TaskAddHandler(projectPrompt));
    commandMap.put("/task/list", new TaskListHandler(projectPrompt));
    commandMap.put("/task/detail", new TaskDetailHandler(projectPrompt));
    commandMap.put("/task/update", new TaskUpdateHandler(projectPrompt));
    commandMap.put("/task/delete", new TaskDeleteHandler(projectPrompt));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
  }

  void service() {
    createMainMenu().execute();
    Prompt.close();
  }

  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ENABLE_LOGIN, "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/auth/logout"));

    mainMenuGroup.add(createBoardMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createProjectMenu());
    mainMenuGroup.add(createTaskMenu());

    return mainMenuGroup;
  }

  private Menu createBoardMenu() {
    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/board/add"));
    boardMenu.add(new MenuItem("목록", "/board/list"));
    boardMenu.add(new MenuItem("상세보기", "/board/detail"));
    boardMenu.add(new MenuItem("변경", Menu.ENABLE_LOGIN, "/board/update"));
    boardMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/board/delete"));
    boardMenu.add(new MenuItem("검색", "/board/search"));
    return boardMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/member/add"));
    memberMenu.add(new MenuItem("목록", "/member/list"));
    memberMenu.add(new MenuItem("상세보기", "/member/detail"));
    memberMenu.add(new MenuItem("변경", Menu.ENABLE_LOGIN, "/member/update"));
    memberMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/member/delete"));
    return memberMenu;
  }

  private Menu createProjectMenu() {
    MenuGroup projectMenu = new MenuGroup("프로젝트");
    projectMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/project/add"));
    projectMenu.add(new MenuItem("목록", "/project/list"));
    projectMenu.add(new MenuItem("상세보기", "/project/detail"));
    projectMenu.add(new MenuItem("변경", Menu.ENABLE_LOGIN, "/project/update"));
    projectMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/project/delete"));
    return projectMenu;
  }

  private Menu createTaskMenu() {
    MenuGroup taskMenu = new MenuGroup("작업");
    taskMenu.add(new MenuItem("등록", Menu.ENABLE_LOGIN, "/task/add"));
    taskMenu.add(new MenuItem("목록", "/task/list"));
    taskMenu.add(new MenuItem("상세보기", "/task/detail"));
    taskMenu.add(new MenuItem("변경", Menu.ENABLE_LOGIN, "/task/update"));
    taskMenu.add(new MenuItem("삭제", Menu.ENABLE_LOGIN, "/task/delete"));
    return taskMenu;
  }
}












