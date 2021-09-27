package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 클라이언트]");

    System.out.println("1) 서버와 연결 중");
    Socket socket = new Socket("127.0.0.1", 8888); // 서버와 접속이 이루어지면 리턴한다.

    System.out.println("2) 서버와 연결되었음");

    System.out.println("입출력 스트림 객체 준비");
    PrintWriter out = new PrintWriter(socket.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    System.out.println("서버에 데이터 보내기");
    out.println("Hello!");
    out.flush();

    System.out.println("서버가 보낸 데이터 읽기");
    String result = in.readLine();
    System.out.println(">>> " + result);

    System.out.println("3) 서버와의 접속을 끊음");
    out.close();
    socket.close();
  }

}












