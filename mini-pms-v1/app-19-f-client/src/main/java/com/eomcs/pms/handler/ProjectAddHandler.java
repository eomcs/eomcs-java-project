package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ProjectAddHandler implements Command {

  RequestAgent requestAgent;
  MemberPrompt memberPrompt;

  public ProjectAddHandler(RequestAgent requestAgent, MemberPrompt memberPrompt) {
    this.requestAgent = requestAgent;
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.setNo(Prompt.inputInt("번호? "));
    project.setTitle(Prompt.inputString("프로젝트명? "));
    project.setContent(Prompt.inputString("내용? "));
    project.setStartDate(Prompt.inputDate("시작일? "));
    project.setEndDate(Prompt.inputDate("종료일? "));
    project.setOwner(AuthLoginHandler.getLoginUser());
    project.setMembers(memberPrompt.promptMembers("팀원?(완료: 빈 문자열) "));

    requestAgent.request("project.insert", project);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("프로젝트를 저장했습니다!");
    } else {
      System.out.println("프로젝트 저장 실패!");
    }
  }
}





