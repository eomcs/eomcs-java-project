package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

// 역할
// - 회원 데이터를 컬렉션 객체를 이용하여 관리한다.
//
public class ListMemberDao implements MemberDao {
  List<Member> list = new ArrayList<>();

  @Override
  public void insert(Member member) throws Exception {
    list.add(member);
  }

  @Override
  public List<Member> findAll() throws Exception {
    return list;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    for (Member member : list) {
      if (member.getNo() == no) {
        return member;
      }
    }
    return null;
  }

  @Override
  public Member findByName(String name) throws Exception {
    for (Member member : list) {
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }

  @Override
  public void update(Member member) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == member.getNo()) {
        list.set(i, member);
        return;
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        return;
      }
    }
  }
}



