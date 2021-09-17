package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Project;

public class ProjectListHandler extends AbstractProjectHandler {

  public ProjectListHandler(List<Project> projectList) {
    super(projectList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[프로젝트 목록]");

    for (Project project : projectList) {
      System.out.printf("%d, %s, %s ~ %s, %s, [%s]\n",
          project.getNo(), 
          project.getTitle(), 
          project.getStartDate(), 
          project.getEndDate(), 
          project.getOwner().getName(),
          project.getMemberNames());
    }
  }
}





