package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardDeleteHandler implements Command {

  RequestAgent requestAgent;

  public BoardDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 삭제]");
    int no = (int) request.getAttribute("no");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    //    if (board.getWriter().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
    //      System.out.println("삭제 권한이 없습니다.");
    //      return;
    //    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    requestAgent.request("board.delete", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println("게시글을 삭제하였습니다.");
  }
}







