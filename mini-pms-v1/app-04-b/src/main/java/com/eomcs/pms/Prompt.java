package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  static String inputString(String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  // 자원을 닫는 일은 그 자원을 가지고 있는 클래스에서 해야 한다.
  static void close() {
    keyboardScan.close();
  }
}







