package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardDetailHandler implements Command {

  RequestAgent requestAgent;

  public BoardDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board board = requestAgent.getObject(Board.class);

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter().getName());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("조회수: %d\n", board.getViewCount());
    System.out.println();

    //    Member loginUser = AuthLoginHandler.getLoginUser(); 
    //    if (loginUser == null || 
    //        (board.getWriter().getNo() != loginUser.getNo() && !loginUser.getEmail().equals("root@test.com"))) {
    //      return;
    //    }

    request.setAttribute("no", no);

    while (true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/board/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/board/delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}







