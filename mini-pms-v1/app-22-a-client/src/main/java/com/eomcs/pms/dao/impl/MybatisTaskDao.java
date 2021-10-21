package com.eomcs.pms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Task;
import com.eomcs.pms.domain.TaskStatus;

// 역할
// - 작업 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisTaskDao implements TaskDao {

  SqlSession sqlSession;

  public MybatisTaskDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Task task) throws Exception {
    sqlSession.insert("TaskMapper.insert", task);
    sqlSession.commit();
  }

  @Override
  public List<Task> findAll(int projectNo) throws Exception {
    return sqlSession.selectList("TaskMapper.findAll", projectNo);
  }

  @Override
  public Task findByNo(int taskNo) throws Exception {
    return sqlSession.selectOne("TaskMapper.findByNo", taskNo);
  }

  @Override
  public void update(Task task) throws Exception {
    sqlSession.update("TaskMapper.update", task);
    sqlSession.commit();
  }


  @Override
  public void delete(int taskNo) throws Exception {
    sqlSession.delete("TaskMapper.delete", taskNo);
    sqlSession.commit();
  }

  @Override
  public List<TaskStatus> findAllStatus() throws Exception {
    return sqlSession.selectList("TaskMapper.findAllStatus");
  }
}



