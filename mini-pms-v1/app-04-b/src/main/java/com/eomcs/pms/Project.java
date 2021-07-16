package com.eomcs.pms;

import java.sql.Date;

//한 개의 프로젝트 정보를 저장할 변수를 구성한다.
//=> 프로젝트 정보를 저장할 새 데이터 타입을 정의한다.
public class Project {
  int no;
  String title;
  String content;
  Date startDate;
  Date endDate;
  String owner;
  String members;
}
