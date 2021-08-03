package com.eomcs.pms.menu;

// 역할
// - 메뉴 항목을 표현하는 역할을 한다.
// - 메뉴에 작업 객체를 등록해 놓으면 
//   해당 메뉴를 실행할 때 그 작업 객체를 실행한다.
//
// ActionListener 규칙에 따라 리스너 객체를 실행하는 코드를 추가한다.
//   - 리스너를 보관할 배열 준비
//   - 리스너를 등록하는 메서드 추가
public class MenuItem extends Menu {

  // 리스너를 보관하는 배열을 준비한다.
  ActionListener[] listeners = new ActionListener[10];
  int size;

  public MenuItem(String title) {
    super(title);
  }

  // 리스너를 등록하는 일을 한다.
  public void addActionListener(ActionListener listener) {
    if (this.size == this.listeners.length) {
      return;
    }
    this.listeners[this.size++] = listener;
  }

  @Override
  public void execute() {
    // 메뉴를 실행하면 이 메뉴에 등록된 모든 리스너에게 알린다.
    for (int i = 0; i < this.size; i++) {
      // 배열에 보관된 각각의 리스너 객체에 대해 
      // ActionListener 규칙에 따라 메서드를 호출한다.
      // => 이것이 리스너에게 알린다는 의미다.
      this.listeners[i].doAction();
    }
  }
}









