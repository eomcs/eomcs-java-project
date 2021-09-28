package com.eomcs.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

// 역할
// - 통신 프로토콜에 맞춰 서버에게 요청을 전달하고 응답을 받는 일을 한다.
//
public class RequestAgent implements AutoCloseable {

  public static final String SUCCESS = "success";
  public static final String FAIL = "fail";

  Socket socket;
  PrintWriter out;
  BufferedReader in;

  String status;
  String jsonData;

  public RequestAgent(String ip, int port) throws Exception {
    socket = new Socket(ip, port);  
    out = new PrintWriter(socket.getOutputStream());
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void request(String command, Object value) throws Exception {
    // 서버쪽으로 데이터를 보낸다.
    // - 서버에 명령어를 한 줄 보낸다.
    out.println(command);

    // - 객체를 JSON으로 변환하여 서버에 보낸다.
    if (value != null) {
      out.println(new Gson().toJson(value));
    } else {
      out.println(); // 보낼 객체가 없으면 빈 문자열을 보내 서버에게 알린다.
    }
    out.flush();

    // 서버에서 응답을 받는다.
    status = in.readLine();
    jsonData = in.readLine();
  }

  public String getStatus() {
    return status;
  }

  public <T> T getObject(Class<T> type) {
    return new Gson().fromJson(jsonData, type);
  }

  @Override
  public void close() {
    try {out.close();} catch (Exception e) {}
    try {in.close();} catch (Exception e) {}
    try {socket.close();} catch (Exception e) {}
  }
}







