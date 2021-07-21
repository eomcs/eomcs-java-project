package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberHandler {

  static final int MAX_LENGTH = 5;

  Member[] members = new Member[MAX_LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[회원 등록]");

    Member member = new Member();

    member.no = Prompt.inputInt("번호? ");
    member.name = Prompt.inputString("이름? ");
    member.email = Prompt.inputString("이메일? ");
    member.password = Prompt.inputString("암호? ");
    member.photo = Prompt.inputString("사진? ");
    member.tel = Prompt.inputString("전화? ");
    member.registeredDate = new Date(System.currentTimeMillis());

    this.members[this.size++] = member;
  }

  public void list() {
    System.out.println("[회원 목록]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          this.members[i].no, 
          this.members[i].name, 
          this.members[i].email, 
          this.members[i].tel, 
          this.members[i].registeredDate);
    }
  }

  boolean exist(String name) {
    for (int i = 0; i < this.size; i++) {
      if (this.members[i].name.equals(name)) {
        return true;
      }
    }
    return false;
  }

}







