package com.eomcs.pms;

//1) 사용자로부터 입력 받는 일을 하는 메서드를 별도의 클래스로 분류한다. 
//2) 회원 정보를 다루는 메서드를 별도의 클래스로 분류한다.
//3) 프로젝트 정보를 다루는 메서드를 별도의 클래스로 분류한다.
//4) 작업 정보를 다루는 메서드를 별도의 클래스로 분류한다.
//5) 키보드 스캐너를 닫는 일은 그 스캐너를 갖고 있는 클래스에서 수행한다.
//   => GRASP 에서 Information Expert 설계 기법 적용
public class App {

  public static void main(String[] args) {

    while (true) {
      String input = Prompt.inputString("명령> ");

      if (input.equals("exit") || input.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (input.equals("/member/add")) {
        MemberHandler.add();

      } else if (input.equals("/member/list")) {
        MemberHandler.list();

      }  else if (input.equals("/project/add")) {
        ProjectHandler.add();

      }  else if (input.equals("/project/list")) {
        ProjectHandler.list();

      }  else if (input.equals("/task/add")) {
        TaskHandler.add();

      }  else if (input.equals("/task/list")) {
        TaskHandler.list();

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }

    // Prompt 가 소유하고 관리하고 있는 자원을 닫으라고 명령한다. 
    Prompt.close();
  }
}












