package com.eomcs.pms;

import java.sql.Date;

//한 회원의 데이터를 저장할 수 있도록
//새 구조의 데이터 타입(user defined data type)을 정의한다.
public class Member {
  int no;
  String name;
  String email;
  String password;
  String photo;
  String tel;
  Date registeredDate;
}