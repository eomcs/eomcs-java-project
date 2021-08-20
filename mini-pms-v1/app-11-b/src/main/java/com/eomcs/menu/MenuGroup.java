package com.eomcs.menu;

import com.eomcs.util.Prompt;
import com.eomcs.util.Stack;

// 역할
// - 다른 메뉴를 포함하는 컨테이너 역할을 수행한다.
// 
public class MenuGroup extends Menu {

  // 메뉴의 bread crumb 목록 보관
  // 모든 메뉴가 공유할 객체이기 때문에 스태틱 멤버로 선언한다.
  static Stack breadCrumb = new Stack();

  Menu[] childs = new Menu[100];
  int size;
  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";

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
      System.out.printf("\n[%s]\n", getBreadCrumb());
      for (int i = 0; i < this.size; i++) {
        System.out.printf("%d. %s\n", i + 1, this.childs[i].title);
      }

      if (!disablePrevMenu) {
        System.out.printf("0. %s\n", this.prevMenuTitle);
      }

      int menuNo = Prompt.inputInt("선택> ");
      if (menuNo == 0 && !disablePrevMenu) {
        // 현재 메뉴에서 나갈 때 스택에서 제거한다.
        breadCrumb.pop();
        return;
      }

      if (menuNo < 0 || menuNo > this.size) {
        System.out.println("무효한 메뉴 번호입니다.");
        continue;
      }

      this.childs[menuNo - 1].execute();
    }
  }

  private String getBreadCrumb() {
    String path = "";

    for (int i = 0; i < breadCrumb.size(); i++) {
      if (path.length() > 0) {
        path += " / ";
      }
      Menu menu = (Menu) breadCrumb.get(i); 
      path += menu.title;
    }

    return path;
  }

}







