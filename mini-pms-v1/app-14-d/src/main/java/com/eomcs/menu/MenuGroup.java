package com.eomcs.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.eomcs.pms.handler.AuthLoginHandler;
import com.eomcs.util.Prompt;

// 역할
// - 다른 메뉴를 포함하는 컨테이너 역할을 수행한다.
// 
public class MenuGroup extends Menu {

  // 메뉴의 bread crumb 목록 보관
  // 모든 메뉴가 공유할 객체이기 때문에 스태틱 멤버로 선언한다.
  static Stack<Menu> breadCrumb = new Stack<>();

  Menu[] childs = new Menu[100];
  int size;
  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";

  // 이전으로 이동시키는 메뉴를 표현하기 위해 만든 클래스
  private static class PrevMenu extends Menu {
    public PrevMenu() {
      super("");
    }
    @Override
    public void execute() {
    }
  }
  static PrevMenu prevMenu = new PrevMenu();

  // 생성자를 정의하지 않으면 컴파일러가 기본 생성자를 자동으로 추가해 준다.
  // 문제는 컴파일러가 추가한 기본 생성자는 수퍼 클래스의 기본 생성자를 호출하기 때문에
  // 컴파일 오류가 발생한다. 
  // Menu 클래스에는 기본 생성자가 없다. 
  // 따라서 개발자가 직접 생성자를 정의해야 한다.
  public MenuGroup(String title) {
    super(title);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  // MenuGroup이 포함하는 하위 Menu를 다룰 수 있도록 메서드를 정의한다.
  public void add(Menu child) {
    if (this.size == this.childs.length) {
      return; // 하위 메뉴를 저장하는 배열이 꽉 찼다면 더이상 저장해서는 안된다.
    }
    this.childs[this.size++] = child; 
  }

  // 배열에 들어 있는 Menu 객체를 찾아 제거한다.
  public Menu remove(Menu child) {
    int index = indexOf(child);
    if (index == -1) {
      return null;
    }
    for (int i = index + 1; i < this.size; i++) {
      this.childs[i - 1] = this.childs[i];
    }
    childs[--this.size] = null;
    return child;
  }

  // 배열에 들어 있는 Menu 객체의 인덱스를 알아낸다.
  public int indexOf(Menu child) {
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i] == child) {
        return i;
      }
    }
    return -1;
  }

  // 배열에 들어 있는 Menu 객체를 찾는다.
  public Menu getMenu(String title) { 
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].title.equals(title)) {
        return this.childs[i];
      }
    }
    return null;
  }

  @Override // 컴파일러에게 오버라이딩을 제대로 하는지 조사해 달라고 요구한다.
  public void execute() {

    // 현재 실행하는 메뉴를 스택에 보관한다.
    breadCrumb.push(this);

    while (true) {
      printBreadCrumbMenuTitle();
      List<Menu> menuList = getMenuList();
      printMenuList(menuList);

      try {
        Menu menu = selectMenu(menuList);
        if (menu == null) {
          System.out.println("무효한 메뉴 번호입니다.");
          continue;
        }
        if (menu instanceof PrevMenu) {
          breadCrumb.pop();
          return;
        }

        menu.execute();

      } catch (Exception e) {
        // try 블록 안에 있는 코드를 실행하다가 예외가 발생하면
        // 다음 문장을 실행한 후 시스템을 멈추지 않고 실행을 계속한다.
        System.out.println("--------------------------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        e.printStackTrace();
        System.out.println("--------------------------------------------------------------");
      }
    }
  }

  private String getBreadCrumb() {
    String path = "";

    for (int i = 0; i < breadCrumb.size(); i++) {
      if (path.length() > 0) {
        path += " / ";
      }
      Menu menu = breadCrumb.get(i); 
      path += menu.title;
    }

    return path;
  }

  // 출력될 메뉴 목록 준비
  // 왜?
  // - 메뉴 출력 속도를 빠르게 하기 위함.
  // - 메뉴를 출력할 때 출력할 메뉴와 출력하지 말아야 할 메뉴를 구분하는 시간을 줄이기 위함.
  // 
  private List<Menu> getMenuList() {
    ArrayList<Menu> menuList = new ArrayList<>();
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].enableState == Menu.ENABLE_LOGOUT && 
          AuthLoginHandler.getLoginUser() == null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_LOGIN && 
          AuthLoginHandler.getLoginUser() != null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_ALL) {
        menuList.add(this.childs[i]);
      } 
    }
    return menuList;
  }

  private void printBreadCrumbMenuTitle() {
    System.out.printf("\n[%s]\n", getBreadCrumb());
  }

  private void printMenuList(List<Menu> menuList) {
    int i = 1;
    for (Menu menu : menuList) {
      System.out.printf("%d. %-20s\n", i++, menu.title);
    }

    if (!disablePrevMenu) {
      System.out.printf("0. %s\n", this.prevMenuTitle);
    }
  }

  private Menu selectMenu(List<Menu> menuList) {
    int menuNo = Prompt.inputInt("선택> ");

    if (menuNo < 0 || menuNo > menuList.size()) {
      return null;
    }

    if (menuNo == 0 && !disablePrevMenu) {
      return prevMenu; // 호출한 쪽에 '이전 메뉴' 선택을 알리게 위해 
    } 

    return menuList.get(menuNo - 1);
  }

}







