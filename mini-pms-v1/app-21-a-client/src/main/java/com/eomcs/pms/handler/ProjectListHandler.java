package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Project;

public class ProjectListHandler implements Command {

  ProjectDao projectDao;

  public ProjectListHandler(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[프로젝트 목록]");

    Collection<Project> projectList = projectDao.findAll();

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





