package com.eomcs.pms.handler;

public class RequestDispatcher {

  Command command;

  public RequestDispatcher(Command command) {
    this.command = command;
  }

  public void forward(CommandRequest request) throws Exception {
    // 요청 배달자(RequestDispatcher)에게 `앞으로 가라(forward)'라고 요구하면,
    // 그 요청을 처리할 커맨드 객체에게 실행을 전달한다.
    command.execute(request);
  }
}
