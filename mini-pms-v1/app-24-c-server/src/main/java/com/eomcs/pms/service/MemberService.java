package com.eomcs.pms.service;

import java.util.List;
import com.eomcs.pms.domain.Member;

public interface MemberService {
  // 서비스 객체의 메서드 이름은 업무 용어를 사용한다.
  void add(Member member) throws Exception;
  List<Member> list() throws Exception;
  Member get(int no) throws Exception;
  Member get(String name) throws Exception;
  boolean isDuplicated(String email) throws Exception;
  int exist(String email, String password) throws Exception;
  void update(Member member) throws Exception;
  void remove(int no) throws Exception;
}
