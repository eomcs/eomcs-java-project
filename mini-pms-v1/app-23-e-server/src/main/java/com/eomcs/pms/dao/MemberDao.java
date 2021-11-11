package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Member;

// 역할
// - 회원 데이터를 처리하는 객체 사용법을 정의한다.
public interface MemberDao {
  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByName(String name) throws Exception;
  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password) throws Exception;
  void update(Member member) throws Exception;
  void delete(int no) throws Exception;
}
