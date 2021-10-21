package com.eomcs.pms.handler;

import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDeleteHandler implements Command {

  TaskDao taskDao;

  public TaskDeleteHandler(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 삭제]");

    Project project = (Project) request.getAttribute("project");
    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("이 프로젝트의 관리자가 아닙니다.");
      return;
    }

    Task task = (Task) request.getAttribute("task");

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("작업 삭제를 취소하였습니다.");
      return;
    }

    taskDao.delete(task.getNo());

    System.out.println("작업를 삭제하였습니다.");
  }
}





