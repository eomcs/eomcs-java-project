package com.eomcs.pms.handler;

import java.util.Collection;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;

public class MemberListHandler implements Command {

  RequestAgent requestAgent;

  public MemberListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록]");

    requestAgent.request("member.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("목록 조회 실패!");
      return;
    }

    Collection<Member> memberList = requestAgent.getObjects(Member.class);

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







