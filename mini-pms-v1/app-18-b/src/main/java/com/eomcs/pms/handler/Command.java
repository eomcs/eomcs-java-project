package com.eomcs.pms.handler;

public interface Command {
  // 커맨드 객체를 실행할 때 필요한 값을 담아서 전달할 수 있도록 
  // CommandRequest 파라미터를 추가한다.
  void execute(CommandRequest request) throws Exception;
}
