package com.eomcs.pms;

import java.sql.Date;

public class TaskHandler {

  static final int TASK_LENGTH = 100;
  static int[] tNo = new int[TASK_LENGTH];
  static String[] tContent = new String[TASK_LENGTH];
  static Date[] tDeadline = new Date[TASK_LENGTH];
  static String[] tOwner = new String[TASK_LENGTH];
  static int[] tStatus = new int[TASK_LENGTH];
  static int tSize = 0;

  static void add() {
    System.out.println("[작업 등록]");

    tNo[tSize] = Prompt.inputInt("번호? ");
    tContent[tSize] = Prompt.inputString("내용? ");
    tDeadline[tSize] = Prompt.inputDate("마감일? ");

    System.out.println("상태?");
    System.out.println("0: 신규");
    System.out.println("1: 진행중");
    System.out.println("2: 완료");
    tStatus[tSize] = Prompt.inputInt("> ");
    tOwner[tSize] = Prompt.inputString("담당자? ");

    tSize++;
  }

  static void list() {
    System.out.println("[작업 목록]");

    for (int i = 0; i < tSize; i++) {
      String stateLabel = null;
      switch (tStatus[i]) {
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
          tNo[i], tContent[i], tDeadline[i], stateLabel, tOwner[i]);
    }
  }

}
