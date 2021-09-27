package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.eomcs.util.Prompt;

public class ClientApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 클라이언트]");

    System.out.println("1) 서버와 연결 중");
    Socket socket = new Socket("127.0.0.1", 8888); // 서버와 접속이 이루어지면 리턴한다.

    System.out.println("2) 서버와 연결되었음");

    PrintWriter out = new PrintWriter(socket.getOutputStream());
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    while (true) {
      String input = Prompt.inputString("명령> ");
      out.println(input);
      out.flush();

      String result = in.readLine();  // 서버에서 한 줄의 문자열을 보낼 때까지 기다린다.
      System.out.println(">>> " + result);

      if (input.equalsIgnoreCase("quit")) {
        break;
      }
    }

    System.out.println("3) 서버와의 접속을 끊음");
    out.close();
    socket.close();

    Prompt.close();
  }

}












