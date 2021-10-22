package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskListHandler implements Command {

  ProjectPrompt projectPrompt;
  TaskDao taskDao;

  public TaskListHandler(ProjectPrompt projectPrompt, TaskDao taskDao) {
    this.projectPrompt = projectPrompt;
    this.taskDao = taskDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 목록]");

    Project project = projectPrompt.promptProject();
    if (project == null) {
      System.out.println("작업 조회를 취소합니다.");
      return;
    }

    System.out.printf("%s:\n\n", project.getTitle());

    List<Task> tasks = taskDao.findAll(project.getNo());

    for (Task task : tasks) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          task.getNo(), 
          task.getContent(), 
          task.getDeadline(), 
          task.getStatus().getTitle(), 
          task.getOwner().getName());
    }

    // 작업 등록과 조회할 때 사용할 수 있도록 프로젝트 정보를 Request 객체에 담는다.
    request.setAttribute("project", project);

    while (true) {
      String input = Prompt.inputString("새작업(A), 상세보기(V), 이전(0)> ");
      switch (input) {
        case "A":
        case "a":
          request.getRequestDispatcher("/task/add").forward(request);
          return;
        case "V":
        case "v":
          while (true) {
            String taskNo = Prompt.inputString("상세보기 작업 번호?(취소: 그냥 엔터)> ");
            if (taskNo.length() == 0) {
              return;
            }
            try {
              request.setAttribute("no", Integer.parseInt(taskNo));
              break;
            } catch (Exception e) {
              System.out.println("번호를 입력하세요.");
            }
          }
          request.getRequestDispatcher("/task/detail").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}





