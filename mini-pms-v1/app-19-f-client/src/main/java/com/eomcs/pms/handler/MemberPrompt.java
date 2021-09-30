package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class MemberPrompt {

  RequestAgent requestAgent;

  public MemberPrompt(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  protected Member findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("member.selectOneByName", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Member.class);
  }

  protected static Member findByName(String name, List<Member> memberList) {
    for (Member member : memberList) {
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }

  public Member promptMember(String label) throws Exception {
    while (true) {
      String memberName = Prompt.inputString(label);
      if (memberName.length() == 0) {
        return null;
      }

      Member member = findByName(memberName);
      if (member != null) {
        return member;
      }

      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public static Member promptMember(String label, List<Member> memberList) {
    while (true) {
      String memberName = Prompt.inputString(label);
      if (memberName.length() == 0) {
        return null;
      }

      Member member = findByName(memberName, memberList);
      if (member != null) {
        return member;
      }

      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public List<Member> promptMembers(String label) throws Exception {
    ArrayList<Member> members = new ArrayList<>();

    while (true) {
      String memberName = Prompt.inputString(label);
      Member member = findByName(memberName);
      if (member != null) {
        members.add(member);
        continue;
      } else if (memberName.length() == 0) {
        break;
      } 
      System.out.println("등록된 회원이 아닙니다.");
    }
    return members;
  }
}







