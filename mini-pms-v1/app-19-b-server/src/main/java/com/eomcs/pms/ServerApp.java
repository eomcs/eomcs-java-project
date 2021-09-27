package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("1) 서버 소캣 준비");
    ServerSocket serverSocket = new ServerSocket(8888);

    System.out.println("2) 클라이언트의 접속을 기다림");
    Socket socket = serverSocket.accept(); // 클라이언트가 접속하면 리턴한다.

    System.out.println("3) 클라이언트가 접속했음");

    System.out.println("클라이언트와 데이터를 주고 받기 위해 입출력 스트림 준비");
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream());

    System.out.println("클라이언트가 보낸 데이터 읽기");
    String message = in.readLine();
    System.out.println("===> " + message);

    System.out.println("클라이언트에게 데이터 보내기");
    out.println("안녕");
    out.flush();

    System.out.println("4) 클라이언트와의 접속을 끊음");
    out.close();
    in.close();
    socket.close(); 

    System.out.println("5) 서버 소켓 종료");
    serverSocket.close(); // 더 이상 클라이언트의 접속을 수용하지 않는다.
  }

}












