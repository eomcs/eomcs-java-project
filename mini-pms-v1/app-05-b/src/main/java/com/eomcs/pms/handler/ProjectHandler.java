package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectHandler {

  static final int MAX_LENGTH = 5;
  static Project[] projects = new Project[MAX_LENGTH];
  static int size = 0;

  //다른 패키지에 있는 App 클래스가 다음 메서드를 호출할 수 있도록 공개한다.
  public static void add() {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.no = Prompt.inputInt("번호? ");
    project.title = Prompt.inputString("프로젝트명? ");
    project.content = Prompt.inputString("내용? ");
    project.startDate = Prompt.inputDate("시작일? ");
    project.endDate = Prompt.inputDate("종료일? ");

    while (true) {
      String owner = Prompt.inputString("만든이?(취소: 빈 문자열) ");
      if (MemberHandler.exist(owner)) {
        project.owner = owner;
        break;
      } else if (owner.length() == 0) {
        System.out.println("프로젝트 등록을 취소합니다.");
        return; // 메서드 실행을 즉시 종료!
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    String members = "";
    while (true) {
      String member = Prompt.inputString("팀원?(완료: 빈 문자열) ");
      if (MemberHandler.exist(member)) {
        if (members.length() > 0) {
          members += ",";
        }
        members += member;
        continue;
      } else if (member.length() == 0) {
        break;
      } 
      System.out.println("등록된 회원이 아닙니다.");
    }
    project.members = members;

    projects[size++] = project;
  }

  //다른 패키지에 있는 App 클래스가 다음 메서드를 호출할 수 있도록 공개한다.
  public static void list() {
    System.out.println("[프로젝트 목록]");
    for (int i = 0; i < size; i++) {
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n",
          projects[i].no, 
          projects[i].title, 
          projects[i].startDate, 
          projects[i].endDate, 
          projects[i].owner,
          projects[i].members);
    }
  }


}
