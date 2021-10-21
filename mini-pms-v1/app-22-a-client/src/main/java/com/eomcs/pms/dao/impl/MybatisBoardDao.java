package com.eomcs.pms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

// 역할
// - 게시글을 DBMS 서버를 통해 관리한다.
//
public class MybatisBoardDao implements BoardDao {

  SqlSession sqlSession;

  public MybatisBoardDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Board board) throws Exception {
    sqlSession.insert("BoardMapper.insert", board);
    sqlSession.commit();
  }

  @Override
  public List<Board> findAll() throws Exception {
    return sqlSession.selectList("BoardMapper.findAll");
  }

  @Override
  public List<Board> findByKeyword(String keyword) throws Exception {
    return sqlSession.selectList("BoardMapper.findByKeyword", keyword);
  }

  @Override
  public Board findByNo(int no) throws Exception {
    return sqlSession.selectOne("BoardMapper.findByNo", no);
  }

  @Override
  public void update(Board board) throws Exception {
    sqlSession.update("BoardMapper.update", board);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("BoardMapper.delete", no);
    sqlSession.commit();
  }
}



