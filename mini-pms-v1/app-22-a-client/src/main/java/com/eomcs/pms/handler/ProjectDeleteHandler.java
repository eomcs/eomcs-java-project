package com.eomcs.pms.handler;

import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectDeleteHandler implements Command {

  ProjectDao projectDao;

  public ProjectDeleteHandler(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[프로젝트 삭제]");
    int no = (int) request.getAttribute("no");

    Project project = projectDao.findByNo(no);

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    if (project.getOwner().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("프로젝트 삭제를 취소하였습니다.");
      return;
    }

    projectDao.delete(no);

    System.out.println("프로젝트를 삭제하였습니다.");
  }
}





