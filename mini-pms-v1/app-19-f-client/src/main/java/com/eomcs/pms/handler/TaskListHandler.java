package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;

public class TaskListHandler implements Command {

  ProjectPrompt projectPrompt;

  public TaskListHandler(ProjectPrompt projectPrompt) {
    this.projectPrompt = projectPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 목록]");

    Project project = projectPrompt.promptProject();
    if (project == null) {
      System.out.println("작업 조회를 취소합니다.");
      return;
    }

    TaskHandlerHelper.printTasks(project);
  }
}





