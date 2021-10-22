package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectAddHandler implements Command {

  ProjectDao projectDao;
  MemberPrompt memberPrompt;
  SqlSession sqlSession;

  public ProjectAddHandler(ProjectDao projectDao, MemberPrompt memberPrompt, SqlSession sqlSession) {
    this.projectDao = projectDao;
    this.memberPrompt = memberPrompt;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.setTitle(Prompt.inputString("프로젝트명? "));
    project.setContent(Prompt.inputString("내용? "));
    project.setStartDate(Prompt.inputDate("시작일? "));
    project.setEndDate(Prompt.inputDate("종료일? "));
    project.setOwner(AuthLoginHandler.getLoginUser());
    project.setMembers(memberPrompt.promptMembers("팀원?(완료: 빈 문자열) "));

    try {
      projectDao.insert(project);
      for (Member m : project.getMembers()) {
        projectDao.insertMember(project.getNo(), m.getNo());
      }
      sqlSession.commit();
    } catch (Exception e) {
      // 예외가 발생하기 전에 성공한 작업이 있으면 모두 취소한다.
      // 그래야 다음 작업에 영향을 끼치지 않는다.
      sqlSession.rollback();
    }

    System.out.println("프로젝트를 저장했습니다!");
  }
}





