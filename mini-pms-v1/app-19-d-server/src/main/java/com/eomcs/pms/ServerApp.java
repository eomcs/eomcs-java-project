package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;
import com.google.gson.Gson;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("1) 서버 소캣 준비");
    ServerSocket serverSocket = new ServerSocket(8888);

    System.out.println("2) 클라이언트의 접속을 기다림");
    Socket socket = serverSocket.accept(); // 클라이언트가 접속하면 리턴한다.

    System.out.println("3) 클라이언트가 접속했음");

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream());

    while (true) {
      String command = in.readLine(); // 클라이언트에서 한 줄의 문자열을 보낼 때까지 기다린다.
      System.out.println("===> " + command);

      if (command.equalsIgnoreCase("quit")) {
        out.println("goodbye");
        out.flush();
        break;

      } else if (command.equals("/board/add")) {
        addBoard(out, in);

      } else if (command.equals("/board/detail")) {
        detailBoard(out, in);

      } else {
        out.println(command);
        out.flush();
      }
    }

    System.out.println("4) 클라이언트와의 접속을 끊음");
    out.close();
    in.close();
    socket.close(); 

    System.out.println("5) 서버 소켓 종료");
    serverSocket.close(); // 더 이상 클라이언트의 접속을 수용하지 않는다.
  }

  private static void addBoard(PrintWriter out, BufferedReader in) throws Exception {
    String jsonStr = in.readLine();

    Board board = new Gson().fromJson(jsonStr, Board.class);

    System.out.println(board);

    out.println("success");
    out.flush();
  }

  @SuppressWarnings("unchecked")
  private static void detailBoard(PrintWriter out, BufferedReader in) throws Exception {
    String jsonStr = in.readLine();

    HashMap<String,Object> map = new Gson().fromJson(jsonStr, HashMap.class);
    System.out.println(map);

    Board board = new Board();
    board.setNo(2);
    board.setTitle("제목1xx");
    board.setContent("내용1xx");
    board.setRegisteredDate(Date.valueOf("2021-1-1"));

    Member m = new Member();
    m.setNo(101);
    m.setName("aaax");
    m.setEmail("aaax@test.com");

    board.setWriter(m);

    out.println(new Gson().toJson(board));
    out.flush();
  }
}












