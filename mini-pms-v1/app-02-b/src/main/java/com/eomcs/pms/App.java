package com.eomcs.pms;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    System.out.println("[회원]");

    // Scanner?
    // 키보드에서 사용자가 입력한 값을 읽어서
    // 문자열이나 정수, 부동소수점 등으로 리턴하는 역할
    Scanner keyboardScan = new Scanner(System.in);

    System.out.print("번호? ");
    String no = keyboardScan.nextLine();

    System.out.print("이름? ");
    String name = keyboardScan.nextLine();

    System.out.print("이메일? ");
    String email = keyboardScan.nextLine();

    System.out.print("암호? ");
    String password = keyboardScan.nextLine();

    System.out.print("사진? ");
    String photo = keyboardScan.nextLine();

    System.out.print("전화? ");
    String tel = keyboardScan.nextLine();

    // 현재 일시 알아내기
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    // System.currentTimeMillis()
    //   - 1970년 1월 1일 0시 0분 0초부터 현재까지 경과된 시간을 밀리초로 리턴한다.
    // new java.sql.Date(밀리초)
    //  - 넘겨 받은 밀리초를 가지고 년,월,일,시,분,초를 계산한다.

    keyboardScan.close(); // 데이터 입출력이 끝났으면 도구를 닫는다.

    System.out.println("--------------------------------");

    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.printf("암호: %s\n", password);
    System.out.printf("사진: %s\n", photo);
    System.out.printf("전화: %s\n", tel);
    System.out.printf("가입일: %s\n", now);
  }
}
