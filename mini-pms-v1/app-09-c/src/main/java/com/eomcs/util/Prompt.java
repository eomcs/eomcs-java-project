package com.eomcs.util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScan = new Scanner(System.in);

  // 메서드의 접근 범위를 설정하지 않으면 
  // 기본 접근 범위는 같은 패키지 및 하위 클래스 만이 접근할 수 있다.
  // => 다른 패키지에서도 접근할 수 있도록 하려면 public 으로 공개해야 한다.
  public static String inputString(String title) {
    System.out.print(title);
    return keyboardScan.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date inputDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void close() {
    keyboardScan.close();
  }
}







