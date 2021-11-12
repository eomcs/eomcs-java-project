package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Board;

// 역할
// - 게시글 데이터를 처리하는 객체 사용법을 정의한다.
public interface BoardDao {
  void insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  List<Board> findByKeyword(String keyword) throws Exception;
  Board findByNo(int no) throws Exception;
  void updateCount(int no) throws Exception;
  void update(Board board) throws Exception;
  void delete(int no) throws Exception;
}
