package com.eomcs.server;

// 역할
// - 데이터를 처리하는 객체 사용법을 정의한다.
//
public interface DataProcessor {
  void execute(Request request, Response response) throws Exception;
}
