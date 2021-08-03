package com.eomcs.pms.menu;

import com.eomcs.pms.handler.ProjectHandler;

// Composite 패턴에서 Leaf 역할을 할 메뉴 항목을 정의한다.
public class ProjectDetailMenu extends Menu {

  ProjectHandler projectHandler;

  public ProjectDetailMenu(ProjectHandler projectHandler) {
    super("상세보기");
    this.projectHandler = projectHandler;
  }

  @Override 
  public void execute() {
    projectHandler.detail(); 
  } 
}
