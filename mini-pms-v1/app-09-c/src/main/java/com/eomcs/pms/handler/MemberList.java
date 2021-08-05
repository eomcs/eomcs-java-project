package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;

public class MemberList {

  static final int MAX_LENGTH = 5;
  Member[] members = new Member[MAX_LENGTH];
  int size = 0;

  public void add(Member member) {
    this.members[this.size++] = member;
  }

  public Member[] toArray() {
    Member[] arr = new Member[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = members[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  public Member findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (members[i].no == no) {
        return members[i];
      }
    }
    return null;
  }

  public boolean remove(Member member) {
    int index = indexOf(member);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.size] = null;

    return true;
  }

  private int indexOf(Member member) {
    for (int i = 0; i < this.size; i++) {
      if (this.members[i] == member) {
        return i;
      }
    }
    return -1;
  }

  public boolean exist(String name) {
    for (int i = 0; i < this.size; i++) {
      if (this.members[i].name.equals(name)) {
        return true;
      }
    }
    return false;
  }
}








