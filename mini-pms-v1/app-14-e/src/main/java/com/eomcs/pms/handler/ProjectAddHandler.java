package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectAddHandler extends AbstractProjectHandler {

  MemberPrompt memberPrompt;

  public ProjectAddHandler(List<Project> projectList, MemberPrompt memberPrompt) {
    super(projectList);
    this.memberPrompt = memberPrompt;

    Project project = new Project();
    project.setNo(101);
    project.setTitle("프로젝트1");
    project.setContent("내용!!!");
    project.setStartDate(Date.valueOf("2021-1-1"));
    project.setEndDate(Date.valueOf("2021-2-2"));
    project.setOwner(memberPrompt.memberList.get(0));
    project.setMembers(new ArrayList<>());

    projectList.add(project);

    project = new Project();
    project.setNo(102);
    project.setTitle("프로젝트2");
    project.setContent("내용!!!");
    project.setStartDate(Date.valueOf("2021-3-1"));
    project.setEndDate(Date.valueOf("2021-4-2"));
    project.setOwner(memberPrompt.memberList.get(1));
    project.setMembers(new ArrayList<>());

    projectList.add(project);

    project = new Project();
    project.setNo(103);
    project.setTitle("프로젝트3");
    project.setContent("내용!!!");
    project.setStartDate(Date.valueOf("2021-5-1"));
    project.setEndDate(Date.valueOf("2021-6-2"));
    project.setOwner(memberPrompt.memberList.get(2));
    project.setMembers(new ArrayList<>());

    projectList.add(project);

  }

  @Override
  public void execute() {
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





