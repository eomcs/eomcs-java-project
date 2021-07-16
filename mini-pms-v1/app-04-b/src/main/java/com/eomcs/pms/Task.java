package com.eomcs.pms;

import java.sql.Date;

// 한 개의 작업 정보를 저장할 변수를 구성한다.
// => 작업 정보를 저장할 새 데이터 타입을 정의한다.
public class Task {
  int no;
  String content;
  Date deadline;
  String owner;
  int status;
}
