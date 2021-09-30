package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.domain.Task;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class TaskDeleteHandler implements Command {

  RequestAgent requestAgent;
  ProjectPrompt projectPrompt;

  public TaskDeleteHandler(RequestAgent requestAgent, ProjectPrompt projectPrompt) {
    this.requestAgent = requestAgent;
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

    HashMap<String,String> params = new HashMap<>();
    params.put("taskNo", String.valueOf(task.getNo()));
    params.put("projectNo", String.valueOf(task.getProject().getNo()));

    requestAgent.request("project.task.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("작업를 삭제하였습니다.");
    } else {
      System.out.println("작업 삭제 실패!");
    }

  }
}





