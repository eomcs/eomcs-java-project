package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    System.out.println("[프로젝트]");

    Scanner keyboardScan = new Scanner(System.in);

    System.out.print("번호? ");
    int no = keyboardScan.nextInt();
    keyboardScan.nextLine(); // 번호 뒤에 남아 있는 줄바꿈 코드를 제거한다.

    System.out.print("프로젝트명? ");
    String title = keyboardScan.nextLine();

    System.out.print("내용? ");
    String content = keyboardScan.nextLine();

    System.out.print("시작일? ");
    Date startDate = Date.valueOf(keyboardScan.nextLine());

    System.out.print("종료일? ");
    Date endDate = Date.valueOf(keyboardScan.nextLine());

    System.out.print("만든이? ");
    String owner = keyboardScan.nextLine();

    System.out.print("팀원? ");
    String members = keyboardScan.nextLine();

    keyboardScan.close();

    System.out.println("--------------------------------");

    System.out.printf("번호: %d\n", no);
    System.out.printf("프로젝트명: %s\n", title);
    System.out.printf("내용: %s\n", content);
    System.out.printf("시작일: %s\n", startDate);
    System.out.printf("종료일: %s\n", endDate);
    System.out.printf("만든이: %s\n", owner);
    System.out.printf("팀원: %s\n", members);
  }
}
