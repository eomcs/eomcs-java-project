package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDeleteHandler extends AbstractTaskHandler {

  public TaskDeleteHandler(ProjectPrompt projectPrompt) {
    super(projectPrompt);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[작업 삭제]");

    Task task = (Task) request.getAttribute("task");
    Project project = (Project) request.getAttribute("project");

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("작업 삭제를 취소하였습니다.");
      return;
    }

    project.getTasks().remove(task);

    System.out.println("작업를 삭제하였습니다.");
  }
}





