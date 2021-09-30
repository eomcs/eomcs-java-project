package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProjectDetailHandler implements Command {

  RequestAgent requestAgent;

  public ProjectDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[프로젝트 상세보기]");
    int no = Prompt.inputInt("번호? ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("project.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    Project project = requestAgent.getObject(Project.class);

    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("내용: %s\n", project.getContent());
    System.out.printf("시작일: %s\n", project.getStartDate());
    System.out.printf("종료일: %s\n", project.getEndDate());
    System.out.printf("만든이: %s\n", project.getOwner().getName());
    System.out.printf("팀원: %s\n", project.getMemberNames());

    Member loginUser = AuthLoginHandler.getLoginUser(); 
    if (loginUser == null || 
        (project.getOwner().getNo() != loginUser.getNo() && 
        !loginUser.getEmail().equals("root@test.com"))) {
      return;
    }

    request.setAttribute("no", no);

    while (true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/project/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/project/delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}





