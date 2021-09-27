package com.eomcs.pms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;
import com.google.gson.Gson;

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

      if (input.equals("/board/add")) {
        addBoard(out, in);

      } else if (input.equals("/board/detail")) {
        detailBoard(out, in);

      } else {
        out.println(input);
        out.flush();

        String result = in.readLine();  // 서버에서 한 줄의 문자열을 보낼 때까지 기다린다.
        System.out.println(">>> " + result);
      }

      if (input.equalsIgnoreCase("quit")) {
        break;
      }
    }

    System.out.println("3) 서버와의 접속을 끊음");
    out.close();
    socket.close();

    Prompt.close();
  }

  private static void addBoard(PrintWriter out, BufferedReader in) throws Exception {
    out.println("/board/add");

    Board board = new Board();
    board.setNo(1);
    board.setTitle("제목1");
    board.setContent("내용1");
    board.setRegisteredDate(Date.valueOf("2021-1-1"));

    Member m = new Member();
    m.setNo(100);
    m.setName("aaa");
    m.setEmail("aaa@test.com");

    board.setWriter(m);

    out.println(new Gson().toJson(board));

    out.flush();

    String result = in.readLine();
    System.out.println("===>" + result);
  }

  private static void detailBoard(PrintWriter out, BufferedReader in) throws Exception {
    out.println("/board/detail");

    HashMap<String,Object> map = new HashMap<>();
    map.put("no", 100);

    String jsonStr = new Gson().toJson(map);
    System.out.println(jsonStr);

    out.println(jsonStr);
    out.flush();

    System.out.println(in.readLine());

    String result = in.readLine();
    Board board = new Gson().fromJson(result, Board.class);
    System.out.println(board);

  }

}












