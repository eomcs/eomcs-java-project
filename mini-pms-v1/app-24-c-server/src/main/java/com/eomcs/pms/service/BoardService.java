package com.eomcs.pms.service;

import java.util.List;
import com.eomcs.pms.domain.Board;

public interface BoardService {
  // 메서드는 업무 용어를 사용한다.
  void add(Board board) throws Exception;
  List<Board> list() throws Exception;
  List<Board> search(String keyword) throws Exception;
  Board get(int no) throws Exception;
  void update(Board board) throws Exception;
  void remove(int no) throws Exception;
}
