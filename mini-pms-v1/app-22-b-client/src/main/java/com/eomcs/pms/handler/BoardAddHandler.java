package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardAddHandler implements Command {

  BoardDao boardDao;
  SqlSession sqlSession;

  public BoardAddHandler(BoardDao boardDao, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[새 게시글]");

    Board board = new Board();

    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(AuthLoginHandler.getLoginUser());

    boardDao.insert(board);
    sqlSession.commit();

    System.out.println("게시글을 저장했습니다.");
  }
}







