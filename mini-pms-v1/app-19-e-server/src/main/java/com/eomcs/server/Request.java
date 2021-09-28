package com.eomcs.server;

import com.google.gson.Gson;

// 역할
// - 클라이언트 보낸 명령과 JSON 데이터를 보관하는 일을 한다.
// - 클라이언트가 보낸 데이터를 특정 타입의 객체로 리턴하는 일을 한다.
//
public class Request {

  String command;
  String jsonData;

  public Request(String command, String jsonData) {
    this.command = command;
    this.jsonData = jsonData;
  }

  public String getCommand() {
    return command;
  }

  public <T> T getValue(Class<T> type) {
    if (jsonData == null || jsonData.length() == 0) {
      return null;
    }
    return new Gson().fromJson(jsonData, type);
  }
}






