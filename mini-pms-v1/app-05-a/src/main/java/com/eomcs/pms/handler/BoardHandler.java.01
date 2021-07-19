package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {

  static final int MAX_LENGTH = 5;

  // Board 인스턴스의 주소를 저장할 레퍼런스 배열을 생성한다.
  static Board[] boards = new Board[MAX_LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("[새 게시글]");

    Board board = new Board();

    board.no = Prompt.inputInt("번호? ");
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");

    boards[size++] = board;
  }

}







