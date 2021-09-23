package com.eomcs.csv;

// 도메인 객체의 CSV 값을 다룰 때 호출하는 메서드의 규칙
public interface CsvValue {

  // 객체 스스로 CSV 형식의 문자열을 리턴하는 메서드를 갖고 있어야 한다.
  String toCsvString();

  // 파라미터로 받은 CSV 문자열을 분석하여 객체의 필드에 그 값을 저장한다.
  void loadCsv(String csv); 
}
