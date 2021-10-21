package com.eomcs.pms.handler;

import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskAddHandler implements Command {

  TaskDao taskDao;

  public TaskAddHandler(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 등록]");

    Project project = (Project) request.getAttribute("project");
    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("이 프로젝트의 관리자가 아닙니다.");
      return;
    }

    Task task = new Task();

    task.setProjectNo(project.getNo());
    task.setContent(Prompt.inputString("내용? "));
    task.setDeadline(Prompt.inputDate("마감일? "));
    task.setStatus(new TaskHandlerHelper(taskDao).promptStatus());
    task.setOwner(MemberPrompt.promptMember("담당자?(취소: 빈 문자열) ", project.getMembers()));
    if (task.getOwner() == null) {
      System.out.println("작업 등록을 취소합니다.");
      return; 
    }

    taskDao.insert(task);

    System.out.println("작업을 등록했습니다.");
  }
}





