# 17-b. 메뉴 리팩토링: Command 인터페이스의 execute()에 파라미터 넘기기

이번 훈련에서는 메서드의 파라미터 활용을 연습한다.


## 훈련 목표

-

## 훈련 내용

- 


## 실습


### 1단계 - 의존 객체에게 일을 시킬 때 특별한 값을 넘기고 싶으면 파라미터를 활용하라.

- 게시글 상세 보기에서 게시글 변경, 삭제를 수행할 때
  - 현재 방식 : 변경이나 삭제할 게시글 번호를 또 묻는다.
  - 개선 방식 : 변경이나 삭제할 게시글 번호를 파라미터로 핸들러에게 넘긴다.

- com.eomcs.pms.handler.CommandRequest 클래스 정의
  - 핸들러를 실행할 때 다양한 값을 전달하기 위해 사용하는 객체이다.
  - 값을 보관하는 일을 한다.
- com.eomcs.pms.handler.Command 인터페이스 변경
  - execute(CommandRequest) 변경
    - CommandRequest를 파라미터로 받는다.
- com.eomcs.pms.handler.XxxHandler 변경
  - Command 인터페이스의 변경에 맞춰 코드를 변경한다.
- com.eomcs.pms.handler.BoardDetailHandler 변경
  - BoardUpdateHandler와 BoardDeleteHandler를 실행할 때,
    - CommandRequest에 게시글 번호를 저장한다.
- com.eomcs.pms.handler.BoardDeleteHandler 변경
  - CommandReuest 파라미터에서 게시글 번호를 꺼낸다.
- com.eomcs.pms.handler.BoardUpdateHandler 변경
  - CommandReuest 파라미터에서 게시글 번호를 꺼낸다.
- com.eomcs.pms.App 클래스 변경
  - Command 인터페이스 변경에 커맨드 객체를 실행할 때 CommandRequest 객체를 파라미터로 넘긴다.

