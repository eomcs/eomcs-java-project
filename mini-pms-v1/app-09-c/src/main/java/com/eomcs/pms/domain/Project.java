package com.eomcs.pms.domain;

import java.sql.Date;

//한 개의 프로젝트 정보를 저장할 변수를 구성한다.
//=> 프로젝트 정보를 저장할 새 데이터 타입을 정의한다.
public class Project {
  public int no;
  public String title;
  public String content;
  public Date startDate;
  public Date endDate;
  public String owner;
  public String members;
}
