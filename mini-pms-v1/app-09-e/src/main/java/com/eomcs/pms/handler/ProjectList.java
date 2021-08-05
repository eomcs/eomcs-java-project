package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;

public class ProjectList {

  static final int MAX_LENGTH = 5;
  Project[] projects = new Project[MAX_LENGTH];
  int size = 0;

  public void add(Project project) {
    if (size == projects.length) {
      Project[] arr = new Project[projects.length + (projects.length >> 1)];
      for (int i = 0; i < size; i++) {
        arr[i] = projects[i];
      }
      projects = arr; // projects에 저장된 옛날 배열 주소를 버리고 새 배열 주소를 저장한다.
    }
    this.projects[this.size++] = project;
  }

  public Project[] toArray() {
    Project[] arr = new Project[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = projects[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  public Project findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (projects[i].no == no) {
        return projects[i];
      }
    }
    return null;
  }

  public boolean remove(Project project) {
    int index = indexOf(project);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.projects[i - 1] = this.projects[i];
    }
    this.projects[--this.size] = null;

    return true;
  }

  private int indexOf(Project project) {
    for (int i = 0; i < this.size; i++) {
      if (this.projects[i] == project) {
        return i;
      }
    }
    return -1;
  }
}








