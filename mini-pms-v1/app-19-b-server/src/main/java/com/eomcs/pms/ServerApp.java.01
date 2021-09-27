package com.eomcs.pms;

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

    System.out.println("4) 클라이언트와의 접속을 끊음");
    socket.close(); 

    System.out.println("5) 서버 소켓 종료");
    serverSocket.close(); // 더 이상 클라이언트의 접속을 수용하지 않는다.
  }

}












