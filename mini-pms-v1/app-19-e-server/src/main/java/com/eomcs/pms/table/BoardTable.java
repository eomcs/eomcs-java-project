package com.eomcs.pms.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.eomcs.pms.domain.Board;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

// 역할
// - 게시글 데이터를 저장하고 조회하는 일을 한다.
// 
public class BoardTable {

  List<Board> list = new ArrayList<>();

  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "/board/insert": insert(request, response); break;
      case "/board/selectOne": selectOne(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Board board = request.getValue(Board.class);
    list.add(board);
    response.setStatus(Response.SUCCESS);
  }

  @SuppressWarnings("unchecked")
  private void selectOne(Request request, Response response) throws Exception {
    Map<String,String> params = request.getValue(Map.class);
    int no = Integer.parseInt(params.get("no"));
    Board board = findByNo(no);
    if (board != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(board);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글이 없습니다.");
    }

  }

  private Board findByNo(int no) {
    for (Board b : list) {
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }
}













