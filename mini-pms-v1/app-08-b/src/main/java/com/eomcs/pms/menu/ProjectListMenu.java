package com.eomcs.pms.menu;

import com.eomcs.pms.handler.ProjectHandler;

// Composite 패턴에서 Leaf 역할을 할 메뉴 항목을 정의한다.
public class ProjectListMenu extends Menu {

  ProjectHandler projectHandler;

  public ProjectListMenu(ProjectHandler projectHandler) {
    super("목록");
    this.projectHandler = projectHandler;
  }

  @Override 
  public void execute() {
    projectHandler.list(); 
  } 
}
