package com.eomcs.pms;

import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.ProjectHandler;
import com.eomcs.pms.handler.TaskHandler;
import com.eomcs.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 각 게시판의 게시글을 담을 메모리(boards 레퍼런스 배열과 size)를 준비한다.
    BoardHandler boardHandler = new BoardHandler();
    BoardHandler boardHandler2 = new BoardHandler();
    BoardHandler boardHandler3 = new BoardHandler();
    BoardHandler boardHandler4 = new BoardHandler();
    BoardHandler boardHandler5 = new BoardHandler();
    BoardHandler boardHandler6 = new BoardHandler();

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

      }  else if (input.equals("/board/add")) {
        // BoardHandler의 add()를 실행할 때 
        // add()에서 사용할 게시글 배열이 있는 인스턴스 주소를 넘겨준다.
        BoardHandler.add(boardHandler);

      }  else if (input.equals("/board/list")) {
        // BoardHandler의 list()를 실행할 때 
        // list()에서 사용할 게시글 배열이 있는 인스턴스 주소를 넘겨준다.
        BoardHandler.list(boardHandler);

      }  else if (input.equals("/board2/add")) {
        // BoardHandler의 add()를 실행할 때 
        // add()에서 사용할 게시글 배열이 있는 인스턴스 주소를 넘겨준다.
        BoardHandler.add(boardHandler2);

      }  else if (input.equals("/board2/list")) {
        BoardHandler.list(boardHandler2);

      }  else if (input.equals("/board3/add")) {
        BoardHandler.add(boardHandler3);

      }  else if (input.equals("/board3/list")) {
        BoardHandler.list(boardHandler3);

      }  else if (input.equals("/board4/add")) {
        BoardHandler.add(boardHandler4);

      }  else if (input.equals("/board4/list")) {
        BoardHandler.list(boardHandler4);

      }  else if (input.equals("/board5/add")) {
        BoardHandler.add(boardHandler5);

      }  else if (input.equals("/board5/list")) {
        BoardHandler.list(boardHandler5);

      }  else if (input.equals("/board6/add")) {
        BoardHandler.add(boardHandler6);

      }  else if (input.equals("/board6/list")) {
        BoardHandler.list(boardHandler6);

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }

    // Prompt 가 소유하고 관리하고 있는 자원을 닫으라고 명령한다. 
    Prompt.close();
  }
}












