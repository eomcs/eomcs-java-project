package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class TaskUpdateHandler implements Command {

  RequestAgent requestAgent;
  ProjectPrompt projectPrompt;

  public TaskUpdateHandler(RequestAgent requestAgent, ProjectPrompt projectPrompt) {
    this.requestAgent = requestAgent;
    this.projectPrompt = projectPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 변경]");

    Task task = (Task) request.getAttribute("task");
    Project project = (Project) request.getAttribute("project");

    String content = Prompt.inputString(String.format("내용(%s)? ", task.getContent()));
    Date deadline = Prompt.inputDate(String.format("마감일(%s)? ", task.getDeadline()));
    int status = TaskHandlerHelper.promptStatus(task.getStatus());

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

    requestAgent.request("project.task.update", task);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("작업를 변경하였습니다.");
    } else {
      System.out.println("작업 변경 실패!");
    }
  }
}





