package com.eomcs.pms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

  @Autowired BoardDao boardDao;

  @Transactional
  @Override
  public void add(Board board) throws Exception {
    boardDao.insert(board);
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Transactional
  @Override
  public Board get(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if (board != null) {
      boardDao.updateCount(no);
    }
    return board;
  };

  @Override
  public List<Board> search(String keyword) throws Exception {
    return boardDao.findByKeyword(keyword);
  }

  @Transactional
  @Override
  public void update(Board board) throws Exception {
    boardDao.update(board);
  }

  @Transactional
  @Override
  public void remove(int no) throws Exception {
    boardDao.delete(no);
  }
}















