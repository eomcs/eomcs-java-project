package com.eomcs.pms.domain;

import java.sql.Date;

// 한 개의 작업 정보를 저장할 변수를 구성한다.
// => 작업 정보를 저장할 새 데이터 타입을 정의한다.
public class Task {
  public int no;
  public String content;
  public Date deadline;
  public String owner;
  public int status;
}
