# 14-b. `Command` 디자인 패턴 : 메서드를 객체로 분리

이번 훈련에서는 **커맨드 패턴(command pattern)** 을 프로젝트에 적용할 것이다.

**커맨드 디자인 패턴** 은,

- *메서드의 객체화* 설계 기법이다.
- 한 개의 명령어를 처리하는 메서드를 별개의 클래스로 분리하는 기법이다.
- 이렇게 하면 명령어가 추가될 때마다 새 클래스를 만들면 되기 때문에  
  기존 코드를 손대지 않아서 유지보수에 좋다.
- 즉 기존 소스에 영향을 끼치지 않고 새 기능을 추가하는 방식이다.
- 명령처리를 별도의 객체로 분리하기 때문에 실행 내역을 관리하기 좋고,
  각 명령이 수행했던 작업을 다루기가 편하다.
- 인터페이스를 이용하면 메서드 호출 규칙을 단일화 할 수 있기 때문에
  코딩의 일관성을 높혀줄 수 있다.
- 단 기능 추가할 때마다 해당 기능을 처리하는 새 클래스가 추가되기 때문에
  클래스 개수는 늘어난다.
- 그러나 유지보수 측면에서는 기존 코드를 변경하는 것 보다는
  클래스 개수가 늘어나는 것이 좋다.
- 유지보수 관점에서는 소스 코드를 일관성 있게 유지보수 할 수 있는게 더 중요한다.


## 훈련 목표

- **커맨드 패턴** 의 클래스 구조와 구동원리를 이해한다.
- **커맨드 패턴** 을 구현하는 방법을 배운다.


## 훈련 내용

- 사용자 명령을 처리할 때 호출할 메서드의 규칙을 인터페이스로 정의한다.
- 명령어를 처리하는 메서드를 인터페이스에 맞춰 별개의 클래스로 캡슐화 한다.

## 실습

`13-e` 소스를 기반으로 작업한다.

### 1단계 - 게시판 명령을 처리하는 BoardHandler 의 각 메서드를 별도의 클래스로 분리한다.

- 각 명령어를 처리하는 메서드를 별도의 BoardXxxHandler 클래스를 만들어 분리한다.
  - BoardAddHandler, BoardListHandler, BoardDetailHandler,BoardUpdateHandler, BoardDeleteHandler
- 게시판 데이터를 공유하기 위해 생성자를 통해 `List` 객체를 주입 받는다.

### 작업 파일

- com.eomcs.pms.handler.BoardAddHandler 생성
- com.eomcs.pms.handler.BoardListHandler 생성
- com.eomcs.pms.handler.BoardDetailHandler 생성
- com.eomcs.pms.handler.BoardUpdateHandler 생성
- com.eomcs.pms.handler.BoardDeleteHandler 생성
- com.eomcs.pms.handler.BoardHandler 삭제
- com.eomcs.pms.App 변경

### 2단계 - 여러 클래스에 공통으로 등장하는 필드와 메서드를 별도의 클래스로 분리한다.

- com.eomcs.pms.handler.AbstractBoardHandler 클래스 추가
  - BoardXxxHandler 클래스에 있던 공통 멤버인 `boardList`와 `findByNo()` 메서드를 이 클래스로 가져온다.
  - BoardXxxHandler에서 이 클래스를 상속 받도록 한다.

### 작업 파일

- com.eomcs.pms.handler.BoardAddHandler 변경
- com.eomcs.pms.handler.BoardListHandler 변경
- com.eomcs.pms.handler.BoardDetailHandler 변경
- com.eomcs.pms.handler.BoardUpdateHandler 변경
- com.eomcs.pms.handler.BoardDeleteHandler 변경
- com.eomcs.pms.handler.AbstractBoardHandler 다시 생성


### 3단계 - generalization을 통해 만든 수퍼 클래스를 추상 클래스로 변경한다.

- com.eomcs.pms.handler.AbstractBoardHandler 이름 변경
  - `BoardHandler` 클래스를 추상 클래스로 변경한다.
  - 클래스를 추상클래스로 만들어 상속 받는 용도로만 사용하게 한다.
  - 클래스의 이름을 `AbstractBoardHandler` 로 변경한다.


### 4단계 - 나머지 `MemberHandler`, `ProjectHandler`, `TaskHandler`, `AuthHandler` 도 Command 패턴을 적용한다.


#### 작업 파일

- com.eomcs.pms.handler.MemberAddHandler 생성
- com.eomcs.pms.handler.MemberListHandler 생성
- com.eomcs.pms.handler.MemberDetailHandler 생성
- com.eomcs.pms.handler.MemberUpdateHandler 생성
- com.eomcs.pms.handler.MemberDeleteHandler 생성
- com.eomcs.pms.handler.AbstractMemberHandler 생성
- com.eomcs.pms.handler.MemberHandler 삭제
- com.eomcs.pms.handler.ProjectAddHandler 생성
- com.eomcs.pms.handler.ProjectListHandler 생성
- com.eomcs.pms.handler.ProjectDetailHandler 생성
- com.eomcs.pms.handler.ProjectUpdateHandler 생성
- com.eomcs.pms.handler.ProjectDeleteHandler 생성
- com.eomcs.pms.handler.AbstractProjectHandler 생성
- com.eomcs.pms.handler.ProjectHandler 삭제
- com.eomcs.pms.handler.TaskAddHandler 생성
- com.eomcs.pms.handler.TaskListHandler 생성
- com.eomcs.pms.handler.TaskDetailHandler 생성
- com.eomcs.pms.handler.TaskUpdateHandler 생성
- com.eomcs.pms.handler.TaskDeleteHandler 생성
- com.eomcs.pms.handler.AbstractTaskHandler 생성
- com.eomcs.pms.handler.TaskHandler 삭제
- com.eomcs.pms.handler.AuthLoginHandler 생성
- com.eomcs.pms.handler.AuthLogoutHandler 생성
- com.eomcs.pms.handler.AuthUserInfoHandler 생성
- com.eomcs.pms.App 변경


## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardAddHandler.java 생성
- src/main/java/com/eomcs/pms/handler/BoardListHandler.java 생성
- src/main/java/com/eomcs/pms/handler/BoardDetailHandler.java 생성
- src/main/java/com/eomcs/pms/handler/BoardUpdateHandler.java 생성
- src/main/java/com/eomcs/pms/handler/BoardDeleteHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AbstractBoardHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberAddHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberListHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberDetailHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberUpdateHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberDeleteHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AbstractMemberHandler.java 생성
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 삭제
- src/main/java/com/eomcs/pms/handler/ProjectAddHandler.java 생성
- src/main/java/com/eomcs/pms/handler/ProjectListHandler.java 생성
- src/main/java/com/eomcs/pms/handler/ProjectDetailHandler.java 생성
- src/main/java/com/eomcs/pms/handler/ProjectUpdateHandler.java 생성
- src/main/java/com/eomcs/pms/handler/ProjectDeleteHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AbstractProjectHandler.java 생성
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 삭제
- src/main/java/com/eomcs/pms/handler/TaskAddHandler.java 생성
- src/main/java/com/eomcs/pms/handler/TaskListHandler.java 생성
- src/main/java/com/eomcs/pms/handler/TaskDetailHandler.java 생성
- src/main/java/com/eomcs/pms/handler/TaskUpdateHandler.java 생성
- src/main/java/com/eomcs/pms/handler/TaskDeleteHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AbstractTaskHandler.java 생성
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 삭제
- src/main/java/com/eomcs/pms/handler/AuthLoginHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AuthLogoutHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AuthUserInfoHandler.java 생성
- src/main/java/com/eomcs/pms/handler/AuthHandler.java 삭제
- src/main/java/com/eomcs/pms/App.java 변경
