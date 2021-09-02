package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDeleteHandler extends AbstractTaskHandler {

  public TaskDeleteHandler(ProjectPrompt projectPrompt) {
    super(projectPrompt);
  }

  @Override
  public void execute() {
    System.out.println("[작업 삭제]");

    Project project = projectPrompt.promptProject();
    if (project == null) {
      System.out.println("작업 삭제를 취소합니다.");
      return;
    }

    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("이 프로젝트의 관리자가 아닙니다.");
      return;
    }

    printTasks(project);

    System.out.println("-------------------------------------");

    int taskNo = Prompt.inputInt("삭제할 작업 번호? ");

    Task task = project.findTaskByNo(taskNo);
    if (task == null) {
      System.out.println("해당 번호의 작업이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("작업 삭제를 취소하였습니다.");
      return;
    }

    project.getTasks().remove(task);

    System.out.println("작업를 삭제하였습니다.");
  }
}





