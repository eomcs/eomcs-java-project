package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Project;

public abstract class AbstractProjectHandler implements Command {

  protected List<Project> projectList;

  public AbstractProjectHandler(List<Project> projectList) {
    this.projectList = projectList;
  }

  protected Project findByNo(int no) {
    for (Project project : projectList) {
      if (project.getNo() == no) {
        return project;
      }
    }
    return null;
  }
}





