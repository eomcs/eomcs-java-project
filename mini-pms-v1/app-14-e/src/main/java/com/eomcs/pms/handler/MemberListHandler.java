package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {
    System.out.println("[회원 목록]");

    for (Member member : memberList) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          member.getNo(), 
          member.getName(), 
          member.getEmail(), 
          member.getTel(), 
          member.getRegisteredDate());
    }
  }
}







