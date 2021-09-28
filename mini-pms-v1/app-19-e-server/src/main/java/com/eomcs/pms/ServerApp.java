package com.eomcs.pms;

import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.server.RequestProcessor;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트가 접속했음");

    RequestProcessor requestProcessor = new RequestProcessor(socket);
    requestProcessor.service();
    requestProcessor.close();

    System.out.println("서버 종료");
    serverSocket.close();
  }
}












