package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Board;

public class BoardList {

  static final int MAX_LENGTH = 5;
  Board[] boards = new Board[MAX_LENGTH];
  int size = 0;

  public void add(Board board) {
    if (size == boards.length) {
      Board[] arr = new Board[boards.length + (boards.length >> 1)];
      for (int i = 0; i < size; i++) {
        arr[i] = boards[i];
      }
      boards = arr; // boards에 저장된 옛날 배열 주소를 버리고 새 배열 주소를 저장한다.
    }
    this.boards[this.size++] = board;
  }

  public Board[] toArray() {
    Board[] arr = new Board[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = boards[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  public Board findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (boards[i].no == no) {
        return boards[i];
      }
    }
    return null;
  }

  public boolean remove(Board board) {
    int index = indexOf(board);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.size] = null;

    return true;
  }

  private int indexOf(Board board) {
    for (int i = 0; i < this.size; i++) {
      if (this.boards[i] == board) {
        return i;
      }
    }
    return -1;
  }
}








