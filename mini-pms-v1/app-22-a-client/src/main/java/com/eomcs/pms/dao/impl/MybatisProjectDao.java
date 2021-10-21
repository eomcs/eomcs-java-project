package com.eomcs.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;

// 역할
// - 프로젝트 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisProjectDao implements ProjectDao {

  SqlSession sqlSession;

  public MybatisProjectDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Project project) throws Exception {
    sqlSession.insert("ProjectMapper.insert", project);

    for (Member m : project.getMembers()) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("projectNo", project.getNo());
      params.put("memberNo", m.getNo());

      sqlSession.insert("ProjectMapper.insertMember", params);
    }

    sqlSession.commit();
  }

  @Override
  public List<Project> findAll() throws Exception {
    return sqlSession.selectList("ProjectMapper.findAll");
  }


  @Override
  public Project findByNo(int no) throws Exception {
    return sqlSession.selectOne("ProjectMapper.findByNo", no);
  }

  @Override
  public void update(Project project) throws Exception {
    sqlSession.update("ProjectMapper.update", project);
    sqlSession.delete("ProjectMapper.deleteMember", project.getNo());

    for (Member m : project.getMembers()) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("projectNo", project.getNo());
      params.put("memberNo", m.getNo());

      sqlSession.insert("ProjectMapper.insertMember", params);
    }
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("ProjectMapper.deleteMember", no);
    sqlSession.delete("ProjectMapper.delete", no);
    sqlSession.commit();
  }
}



