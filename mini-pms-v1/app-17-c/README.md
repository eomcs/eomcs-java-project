# 17-c. 메뉴 리팩토링: Command 객체 간에 종속성 제거하기

이번 훈련에서는 객체 간의 종속성을 줄이는 방법을 알아본다.


## 훈련 목표

-

## 훈련 내용

- 


## 실습


### 1단계 - 커맨드 객체끼리의 종속성을 제거하기 위해 커맨드 객체 실행을 다른 객체에게 맡겨라.

- 게시글 상세 보기에서 게시글 변경, 삭제를 수행할 때
  - 현재 방식 : 직접 해당 커맨드 객체의 메서드를 호출한다.
  - 개선 방식 : RequestDispatcher 에게 실행을 위임한다.

- com.eomcs.pms.handler.RequestDispatcher 클래스 정의
  - 객체를 생성할 때 파라미터로 받은 커맨드 객체를 실행하는 일을 한다.
- com.eomcs.pms.handler.CommandRequest 클래스 변경
  - getRequestDispatcher(커맨드 아이디) 메서드 추가
    - 지정된 아이디의 커맨드 객체를 찾아 RequestDispatcher에 담아서 리턴해준다.
- com.eomcs.pms.handler.BoardDetailHandler 클래스 변경
  - BoardUpdateHandler와 BoardDeleteHandler 와의 의존성을 제거한다.
  - 게시글 변경과 삭제는 RequestDispatcher 를 통해 처리한다.
- com.eomcs.pms.App 클래스 변경
  - BoardDetailHandler의 변경에 맞춰 의존 객체 주입을 제거한다.

### 2단계 - 나머지 메뉴도 게시글과 같이 처리한다.
