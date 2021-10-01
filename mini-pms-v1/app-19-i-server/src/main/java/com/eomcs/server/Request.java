package com.eomcs.server;

import java.util.Map;
import com.google.gson.Gson;

// 역할
// - 클라이언트 보낸 명령과 JSON 데이터를 보관하는 일을 한다.
// - 클라이언트가 보낸 데이터를 특정 타입의 객체로 리턴하는 일을 한다.
//
public class Request {

  String command;
  String jsonData;
  Map<String,String> params;

  public Request(String command, String jsonData) {
    this.command = command;
    this.jsonData = jsonData;
  }

  public String getCommand() {
    return command;
  }

  public <T> T getObject(Class<T> type) {
    if (jsonData == null || jsonData.length() == 0) {
      return null;
    }
    return new Gson().fromJson(jsonData, type);
  }

  @SuppressWarnings("unchecked")
  public String getParameter(String name) {
    try {
      if (params == null) {
        params = new Gson().fromJson(jsonData, Map.class);
      }
      return params.get(name);

    } catch (Exception e) {
      // 클라이언트가 보낸 JSON 데이터를 맵 객체로 환원(deserialize)하지 못한다면
      // 그냥 null을 리턴한다.
      return null;
    }
  }
}






