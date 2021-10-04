package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_GENERAL;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuFilter;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.dao.impl.NetBoardDao;
import com.eomcs.pms.dao.impl.NetMemberDao;
import com.eomcs.pms.dao.impl.NetProjectDao;
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
import com.eomcs.pms.handler.CommandRequest;
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
import com.eomcs.pms.listener.AppInitListener;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ClientApp {

  RequestAgent requestAgent;

  HashMap<String,Command> commandMap = new HashMap<>();

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  // 옵저버 관련 필드와 메서드
  // => 옵저버(리스너) 목록
  List<ApplicationContextListener> listeners = new ArrayList<>();

  // => 옵저버(리스너)를 등록하는 메서드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  // => 옵저버(리스너)를 제거하는 메서드
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  private void notifyOnApplicationStarted() {
    HashMap<String,Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    HashMap<String,Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }

  public ClientApp() throws Exception {

    // 서버와 통신을 담당할 객체 준비
    requestAgent = new RequestAgent("127.0.0.1", 8888);

    // 데이터 관리를 담당할 DAO 객체를 준비한다.
    NetBoardDao boardDao = new NetBoardDao(requestAgent);
    NetMemberDao memberDao = new NetMemberDao(requestAgent);
    NetProjectDao projectDao = new NetProjectDao(requestAgent);

    // Command 객체 준비
    commandMap.put("/member/add", new MemberAddHandler(memberDao));
    commandMap.put("/member/list", new MemberListHandler(memberDao));
    commandMap.put("/member/detail", new MemberDetailHandler(memberDao));
    commandMap.put("/member/update", new MemberUpdateHandler(memberDao));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberDao));

    commandMap.put("/board/add", new BoardAddHandler(boardDao));
    commandMap.put("/board/list", new BoardListHandler(boardDao));
    commandMap.put("/board/detail", new BoardDetailHandler(boardDao));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao));
    commandMap.put("/board/search", new BoardSearchHandler(boardDao));

    commandMap.put("/auth/login", new AuthLoginHandler(requestAgent));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());

    MemberPrompt memberPrompt = new MemberPrompt(memberDao);

    commandMap.put("/project/add", new ProjectAddHandler(projectDao, memberPrompt));
    commandMap.put("/project/list", new ProjectListHandler(projectDao));
    commandMap.put("/project/detail", new ProjectDetailHandler(projectDao));
    commandMap.put("/project/update", new ProjectUpdateHandler(projectDao, memberPrompt));
    commandMap.put("/project/delete", new ProjectDeleteHandler(projectDao));

    ProjectPrompt projectPrompt = new ProjectPrompt(projectDao);
    commandMap.put("/task/add", new TaskAddHandler(projectDao, projectPrompt));
    commandMap.put("/task/list", new TaskListHandler(projectPrompt));
    commandMap.put("/task/detail", new TaskDetailHandler(projectPrompt));
    commandMap.put("/task/update", new TaskUpdateHandler(projectDao, projectPrompt));
    commandMap.put("/task/delete", new TaskDeleteHandler(projectDao, projectPrompt));
  }

  // MenuGroup에서 사용할 필터를 정의한다.
  MenuFilter menuFilter = menu -> (menu.getAccessScope() & AuthLoginHandler.getUserAccessLevel()) > 0;


  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setMenuFilter(menuFilter);
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", ACCESS_LOGOUT , "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", ACCESS_GENERAL, "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", ACCESS_GENERAL, "/auth/logout"));

    mainMenuGroup.add(createBoardMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createProjectMenu());
    mainMenuGroup.add(createTaskMenu());
    mainMenuGroup.add(createAdminMenu());

    return mainMenuGroup;
  }

  private Menu createBoardMenu() {
    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.setMenuFilter(menuFilter);
    boardMenu.add(new MenuItem("등록", ACCESS_GENERAL, "/board/add"));
    boardMenu.add(new MenuItem("목록", "/board/list"));
    boardMenu.add(new MenuItem("상세보기", "/board/detail"));
    boardMenu.add(new MenuItem("검색", "/board/search"));
    return boardMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.setMenuFilter(menuFilter);
    memberMenu.add(new MenuItem("등록", ACCESS_GENERAL, "/member/add"));
    memberMenu.add(new MenuItem("목록", "/member/list"));
    memberMenu.add(new MenuItem("상세보기", "/member/detail"));
    return memberMenu;
  }

  private Menu createProjectMenu() {
    MenuGroup projectMenu = new MenuGroup("프로젝트");
    projectMenu.setMenuFilter(menuFilter);
    projectMenu.add(new MenuItem("등록", ACCESS_GENERAL, "/project/add"));
    projectMenu.add(new MenuItem("목록", "/project/list"));
    projectMenu.add(new MenuItem("상세보기", "/project/detail"));
    return projectMenu;
  }

  private Menu createTaskMenu() {
    MenuGroup taskMenu = new MenuGroup("작업");
    taskMenu.setMenuFilter(menuFilter);
    taskMenu.add(new MenuItem("등록", ACCESS_GENERAL, "/task/add"));
    taskMenu.add(new MenuItem("목록", "/task/list"));
    taskMenu.add(new MenuItem("상세보기", "/task/detail"));
    return taskMenu;
  }

  private Menu createAdminMenu() {
    MenuGroup adminMenu = new MenuGroup("관리자", ACCESS_ADMIN);
    adminMenu.setMenuFilter(menuFilter);
    adminMenu.add(new MenuItem("회원 등록", "/member/add"));
    adminMenu.add(new MenuItem("프로젝트 등록", "/project/add"));
    adminMenu.add(new MenuItem("작업 등록", "/task/add"));
    adminMenu.add(new MenuItem("게시글 등록", "/board/add"));
    return adminMenu;
  }


  void service() throws Exception {

    notifyOnApplicationStarted();

    createMainMenu().execute();

    Prompt.close();

    notifyOnApplicationEnded();

  }

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 클라이언트]");

    ClientApp app = new ClientApp(); 
    app.addApplicationContextListener(new AppInitListener());
    app.service();

    Prompt.close();
  }
}












