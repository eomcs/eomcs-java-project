package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskUpdateHandler extends AbstractTaskHandler {

  public TaskUpdateHandler(ProjectPrompt projectPrompt) {
    super(projectPrompt);
  }

  @Override
  public void execute() {
    System.out.println("[작업 변경]");

    Project project = projectPrompt.promptProject();
    if (project == null) {
      System.out.println("작업 변경을 취소합니다.");
      return;
    }

    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("이 프로젝트의 관리자가 아닙니다.");
      return;
    }

    printTasks(project);

    System.out.println("-------------------------------------");

    int taskNo = Prompt.inputInt("변경할 작업 번호? ");

    Task task = project.findTaskByNo(taskNo);
    if (task == null) {
      System.out.println("해당 번호의 작업이 없습니다.");
      return;
    }

    String content = Prompt.inputString(String.format("내용(%s)? ", task.getContent()));
    Date deadline = Prompt.inputDate(String.format("마감일(%s)? ", task.getDeadline()));
    int status = promptStatus(task.getStatus());
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

    task.setContent(content);
    task.setDeadline(deadline);
    task.setStatus(status);
    task.setOwner(owner);

    System.out.println("작업를 변경하였습니다.");
  }
}





