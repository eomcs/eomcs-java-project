package com.eomcs.pms.handler;

import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDetailHandler implements Command {

  TaskDao taskDao;

  public TaskDetailHandler(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 상세보기]");
    int no = (int) request.getAttribute("no");

    Task task = taskDao.findByNo(no);
    if (task == null) {
      System.out.println("해당 번호의 작업이 없습니다.");
      return;
    }

    System.out.printf("내용: %s\n", task.getContent());
    System.out.printf("마감일: %s\n", task.getDeadline());
    System.out.printf("상태: %s\n", task.getStatus().getTitle());
    System.out.printf("담당자: %s\n", task.getOwner().getName());
    System.out.println();

    Member loginUser = AuthLoginHandler.getLoginUser(); 
    if (loginUser == null) {
      return;
    }

    Project project = (Project) request.getAttribute("project");
    if (!loginUser.getEmail().equals("root@test.com")) {
      if (project.getOwner().getNo() != loginUser.getNo()) {
        return;
      }
    }

    request.setAttribute("task", task);

    while (true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)> ");
      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/task/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/task/delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}





