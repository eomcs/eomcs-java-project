package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectAddHandler extends AbstractProjectHandler {

  MemberPrompt memberPrompt;

  public ProjectAddHandler(List<Project> projectList, MemberPrompt memberPrompt) {
    super(projectList);
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.setNo(Prompt.inputInt("번호? "));
    project.setTitle(Prompt.inputString("프로젝트명? "));
    project.setContent(Prompt.inputString("내용? "));
    project.setStartDate(Prompt.inputDate("시작일? "));
    project.setEndDate(Prompt.inputDate("종료일? "));
    project.setOwner(AuthLoginHandler.getLoginUser());
    project.setMembers(memberPrompt.promptMembers("팀원?(완료: 빈 문자열) "));

    projectList.add(project);

    System.out.println("프로젝트를 저장했습니다!");
  }
}





