package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

// 역할
// - 회원 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisMemberDao implements MemberDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisMemberDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Member member) throws Exception {
    sqlSession.insert("MemberMapper.insert", member);
    sqlSession.commit();
  }

  @Override
  public List<Member> findAll() throws Exception {
    return sqlSession.selectList("MemberMapper.findAll");
  }

  @Override
  public Member findByNo(int no) throws Exception {
    return sqlSession.selectOne("MemberMapper.findByNo", no);
  }

  @Override
  public Member findByName(String name) throws Exception {
    List<Member> list = sqlSession.selectList("MemberMapper.findByName", name);
    if (list.size() > 0) {
      return list.get(0);
    } else {
      return null;
    }

  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    return sqlSession.selectOne("MemberMapper.findByEmailAndPassword", params);
  }


  @Override
  public void update(Member member) throws Exception {
    sqlSession.update("MemberMapper.update", member);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("MemberMapper.delete", no);
    sqlSession.commit();
  }
}



