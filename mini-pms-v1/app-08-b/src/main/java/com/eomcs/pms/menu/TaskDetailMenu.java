package com.eomcs.pms.menu;

import com.eomcs.pms.handler.TaskHandler;

// Composite 패턴에서 Leaf 역할을 할 메뉴 항목을 정의한다.
public class TaskDetailMenu extends Menu {

  TaskHandler taskHandler;

  public TaskDetailMenu(TaskHandler taskHandler) {
    super("상세보기");
    this.taskHandler = taskHandler;
  }

  @Override 
  public void execute() {
    taskHandler.detail(); 
  } 
}
