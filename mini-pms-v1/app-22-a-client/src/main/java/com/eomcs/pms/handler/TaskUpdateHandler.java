package com.eomcs.pms.handler;

import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskUpdateHandler implements Command {

  TaskDao taskDao;
  ProjectPrompt projectPrompt;

  public TaskUpdateHandler(TaskDao taskDao) {
    this.taskDao = taskDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 변경]");

    Project project = (Project) request.getAttribute("project");

    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("이 프로젝트의 관리자가 아닙니다.");
      return;
    }

    Task task = (Task) request.getAttribute("task");
    task.setContent(Prompt.inputString(String.format("내용(%s)? ", task.getContent())));
    task.setDeadline(Prompt.inputDate(String.format("마감일(%s)? ", task.getDeadline())));
    task.setStatus(new TaskHandlerHelper(taskDao).promptStatus(task.getStatus().getNo()));

    Member owner = MemberPrompt.promptMember(
        String.format("담당자(%s)?(취소: 빈 문자열) ", task.getOwner().getName()), 
        project.getMembers());
    if (owner == null) {
      System.out.println("작업 변경을 취소합니다.");
      return;
    }

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("작업 변경을 취소하였습니다.");
      return;
    }

    taskDao.update(task);

    System.out.println("작업를 변경하였습니다.");
  }
}





