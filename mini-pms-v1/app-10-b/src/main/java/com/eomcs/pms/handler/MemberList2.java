package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;

public class MemberList2 extends LinkedList {

  public Member findByNo(int no) {
    Object[] list = toArray();
    for (Object obj : list) {
      Member member = (Member) obj;
      if (member.no == no) {
        return member;
      }
    }
    return null;
  }

  public boolean exist(String name) {
    Object[] list = toArray();
    for (Object obj : list) {
      Member member = (Member) obj;
      if (member.name.equals(name)) {
        return true;
      }
    }
    return false;
  }
}








