package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.List;
import com.eomcs.util.Prompt;

public class ProjectHandler {

  List<Project> projectList;
  MemberHandler memberHandler;

  public ProjectHandler(List<Project> projectList, MemberHandler memberHandler) {
    this.projectList = projectList;
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.setNo(Prompt.inputInt("번호? "));
    project.setTitle(Prompt.inputString("프로젝트명? "));
    project.setContent(Prompt.inputString("내용? "));
    project.setStartDate(Prompt.inputDate("시작일? "));
    project.setEndDate(Prompt.inputDate("종료일? "));

    project.setOwner(memberHandler.promptMember("만든이?(취소: 빈 문자열) "));
    if (project.getOwner() == null) {
      System.out.println("프로젝트 등록을 취소합니다.");
      return;
    }

    project.setMembers(memberHandler.promptMembers("팀원?(완료: 빈 문자열) "));

    projectList.add(project);
  }

  //다른 패키지에 있는 App 클래스가 다음 메서드를 호출할 수 있도록 공개한다.
  public void list() {
    System.out.println("[프로젝트 목록]");

    Project[] list = new Project[projectList.size()];
    list = projectList.toArray(list); // 혹시 파라미터로 넘겨준 배열이 작을 경우를 대비한다.

    for (Project project : list) {
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n",
          project.getNo(), 
          project.getTitle(), 
          project.getStartDate(), 
          project.getEndDate(), 
          project.getOwner(),
          project.getMembers());
    }
  }

  public void detail() {
    System.out.println("[프로젝트 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Project project = findByNo(no);

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("내용: %s\n", project.getContent());
    System.out.printf("시작일: %s\n", project.getStartDate());
    System.out.printf("종료일: %s\n", project.getEndDate());
    System.out.printf("만든이: %s\n", project.getOwner());
    System.out.printf("팀원: %s\n", project.getMembers());
  }

  public void update() {
    System.out.println("[프로젝트 변경]");
    int no = Prompt.inputInt("번호? ");

    Project project = findByNo(no);

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("프로젝트명(%s)? ", project.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", project.getContent()));
    Date startDate = Prompt.inputDate(String.format("시작일(%s)? ", project.getStartDate()));
    Date endDate = Prompt.inputDate(String.format("종료일(%s)? ", project.getEndDate()));

    String owner = memberHandler.promptMember(String.format(
        "만든이(%s)?(취소: 빈 문자열) ", project.getOwner()));
    if (owner == null) {
      System.out.println("프로젝트 변경을 취소합니다.");
      return;
    }

    String members = memberHandler.promptMembers(String.format(
        "팀원(%s)?(완료: 빈 문자열) ", project.getMembers()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("프로젝트 변경을 취소하였습니다.");
      return;
    }

    project.setTitle(title);
    project.setContent(content);
    project.setStartDate(startDate);
    project.setEndDate(endDate);
    project.setOwner(owner);
    project.setMembers(members);

    System.out.println("프로젝트를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[프로젝트 삭제]");
    int no = Prompt.inputInt("번호? ");

    Project project = findByNo(no);

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("프로젝트 삭제를 취소하였습니다.");
      return;
    }

    projectList.remove(project);

    System.out.println("프로젝트를 삭제하였습니다.");
  }

  public Project findByNo(int no) {
    Project[] arr = projectList.toArray(new Project[0]);
    for (Project project : arr) {
      if (project.getNo() == no) {
        return project;
      }
    }
    return null;
  }

}





