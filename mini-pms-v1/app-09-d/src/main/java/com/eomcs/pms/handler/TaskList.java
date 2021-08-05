package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Task;

public class TaskList {

  static final int MAX_LENGTH = 5;
  Task[] tasks = new Task[MAX_LENGTH];
  int size = 0;

  public void add(Task task) {
    if (size == tasks.length) {
      Task[] arr = new Task[tasks.length + (tasks.length >> 1)];
      for (int i = 0; i < size; i++) {
        arr[i] = tasks[i];
      }
      tasks = arr; // tasks에 저장된 옛날 배열 주소를 버리고 새 배열 주소를 저장한다.
    }
    this.tasks[this.size++] = task;
  }

  public Task[] toArray() {
    Task[] arr = new Task[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = tasks[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  public Task findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (tasks[i].no == no) {
        return tasks[i];
      }
    }
    return null;
  }

  public boolean remove(Task task) {
    int index = indexOf(task);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.tasks[i - 1] = this.tasks[i];
    }
    this.tasks[--this.size] = null;

    return true;
  }

  private int indexOf(Task task) {
    for (int i = 0; i < this.size; i++) {
      if (this.tasks[i] == task) {
        return i;
      }
    }
    return -1;
  }
}








