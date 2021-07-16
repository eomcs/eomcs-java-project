package com.eomcs.pms;

public class TaskHandler {

  static final int MAX_LENGTH = 5;
  static Task[] tasks = new Task[MAX_LENGTH];
  static int size = 0;

  static void add() {
    System.out.println("[작업 등록]");

    Task task = new Task();

    task.no = Prompt.inputInt("번호? ");
    task.content = Prompt.inputString("내용? ");
    task.deadline = Prompt.inputDate("마감일? ");

    System.out.println("상태?");
    System.out.println("0: 신규");
    System.out.println("1: 진행중");
    System.out.println("2: 완료");
    task.status = Prompt.inputInt("> ");
    task.owner = Prompt.inputString("담당자? ");

    tasks[size++] = task;
  }

  static void list() {
    System.out.println("[작업 목록]");

    for (int i = 0; i < size; i++) {
      String stateLabel = null;
      switch (tasks[i].status) {
        case 1:
          stateLabel = "진행중";
          break;
        case 2:
          stateLabel = "완료";
          break;
        default:
          stateLabel = "신규";
      }

      System.out.printf("%d, %s, %s, %s, %s\n",
          tasks[i].no, 
          tasks[i].content, 
          tasks[i].deadline, 
          stateLabel, 
          tasks[i].owner);
    }
  }

}
