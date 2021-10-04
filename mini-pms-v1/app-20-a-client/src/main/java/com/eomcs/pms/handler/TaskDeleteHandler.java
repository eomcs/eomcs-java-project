package com.eomcs.pms.handler;

import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDeleteHandler implements Command {

  ProjectDao projectDao;
  ProjectPrompt projectPrompt;

  public TaskDeleteHandler(ProjectDao projectDao, ProjectPrompt projectPrompt) {
    this.projectDao = projectDao;
    this.projectPrompt = projectPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 삭제]");

    Task task = (Task) request.getAttribute("task");

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("작업 삭제를 취소하였습니다.");
      return;
    }

    projectDao.deleteTask(task.getProject().getNo(), task.getNo());
    System.out.println("작업를 삭제하였습니다.");
  }
}





