package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectHandler {

  static final int MAX_LENGTH = 5;

  Project[] projects = new Project[MAX_LENGTH];
  int size = 0;

  public void add(MemberHandler memberHandler) {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.no = Prompt.inputInt("번호? ");
    project.title = Prompt.inputString("프로젝트명? ");
    project.content = Prompt.inputString("내용? ");
    project.startDate = Prompt.inputDate("시작일? ");
    project.endDate = Prompt.inputDate("종료일? ");

    while (true) {
      String owner = Prompt.inputString("만든이?(취소: 빈 문자열) ");
      if (memberHandler.exist(owner)) {
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
      if (memberHandler.exist(member)) {
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

    this.projects[this.size++] = project;
  }

  //다른 패키지에 있는 App 클래스가 다음 메서드를 호출할 수 있도록 공개한다.
  public void list() {
    System.out.println("[프로젝트 목록]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n",
          this.projects[i].no, 
          this.projects[i].title, 
          this.projects[i].startDate, 
          this.projects[i].endDate, 
          this.projects[i].owner,
          this.projects[i].members);
    }
  }

  public void detail() {
    System.out.println("[프로젝트 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Project project = null;

    for (int i = 0; i < this.size; i++) {
      if (this.projects[i].no == no) {
        project = this.projects[i];
        break;
      }
    }

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    System.out.printf("프로젝트명: %s\n", project.title);
    System.out.printf("내용: %s\n", project.content);
    System.out.printf("시작일: %s\n", project.startDate);
    System.out.printf("종료일: %s\n", project.endDate);
    System.out.printf("만든이: %s\n", project.owner);
    System.out.printf("팀원: %s\n", project.members);
  }

  public void update(MemberHandler memberHandler) {
    System.out.println("[프로젝트 변경]");
    int no = Prompt.inputInt("번호? ");

    Project project = null;

    for (int i = 0; i < this.size; i++) {
      if (this.projects[i].no == no) {
        project = this.projects[i];
        break;
      }
    }

    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("프로젝트명(%s)? ", project.title));
    String content = Prompt.inputString(String.format("내용(%s)? ", project.content));
    Date startDate = Prompt.inputDate(String.format("시작일(%s)? ", project.startDate));
    Date endDate = Prompt.inputDate(String.format("종료일(%s)? ", project.endDate));

    String owner = null;
    while (true) {
      owner = Prompt.inputString(String.format(
          "만든이(%s)?(취소: 빈 문자열) ", project.owner));
      if (memberHandler.exist(owner)) {
        break;
      } else if (owner.length() == 0) {
        System.out.println("프로젝트 변경을 취소합니다.");
        return; // 메서드 실행을 즉시 종료!
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    String members = "";
    while (true) {
      String member = Prompt.inputString(String.format(
          "팀원(%s)?(완료: 빈 문자열) ", project.members));
      if (memberHandler.exist(member)) {
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


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("프로젝트 변경을 취소하였습니다.");
      return;
    }

    project.title = title;
    project.content = content;
    project.startDate = startDate;
    project.endDate = endDate;
    project.owner = owner;
    project.members = members;

    System.out.println("프로젝트를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[프로젝트 삭제]");
    int no = Prompt.inputInt("번호? ");

    int index = -1;

    for (int i = 0; i < this.size; i++) {
      if (this.projects[i].no == no) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("프로젝트 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.projects[i - 1] = this.projects[i];
    }
    this.projects[--this.size] = null;

    System.out.println("프로젝트를 삭제하였습니다.");
  }


}
