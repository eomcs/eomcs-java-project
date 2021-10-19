package com.eomcs.menu;

// 역할
// - MenuGroup 에서 출력할 메뉴를 선별할 때, 그 판단을 내릴 객체의 사용법.
//
public interface MenuFilter {
  boolean accept(Menu menu);
}
