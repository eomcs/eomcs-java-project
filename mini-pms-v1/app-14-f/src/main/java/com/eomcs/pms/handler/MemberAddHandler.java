package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);

    Member testUser = new Member();
    testUser.setNo(1);
    testUser.setName("aaa");
    testUser.setEmail("aaa@test.com");
    testUser.setPassword("1111");
    testUser.setPhoto("aaa.gif");
    testUser.setTel("010-1111-1111");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testUser);

    testUser = new Member();
    testUser.setNo(2);
    testUser.setName("bbb");
    testUser.setEmail("bbb@test.com");
    testUser.setPassword("1111");
    testUser.setPhoto("bbb.gif");
    testUser.setTel("010-1111-2222");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testUser);

    testUser = new Member();
    testUser.setNo(3);
    testUser.setName("ccc");
    testUser.setEmail("ccc@test.com");
    testUser.setPassword("1111");
    testUser.setPhoto("ccc.gif");
    testUser.setTel("010-1111-3333");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testUser);

    testUser = new Member();
    testUser.setNo(4);
    testUser.setName("ddd");
    testUser.setEmail("ddd@test.com");
    testUser.setPassword("1111");
    testUser.setPhoto("ddd.gif");
    testUser.setTel("010-1111-4444");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(testUser);
  }

  @Override
  public void execute() {
    System.out.println("[회원 등록]");

    Member member = new Member();

    member.setNo(Prompt.inputInt("번호? "));
    member.setName(Prompt.inputString("이름? "));
    member.setEmail(Prompt.inputString("이메일? "));
    member.setPassword(Prompt.inputString("암호? "));
    member.setPhoto(Prompt.inputString("사진? "));
    member.setTel(Prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    memberList.add(member);
  }
}







