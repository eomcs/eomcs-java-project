# 05-c. 인스턴스 사용법 : 인스턴스 메서드가 필요한 이유와 사용법

**인스턴스 필드(non-static field)** 는 new 명령을 통해 Heap 영역에 생성된다.
개별적으로 다뤄야 할 값이라면 인스턴스 필드로 선언하라.

인스턴스 필드를 다루는 메서드는 **인스턴스 메서드(non-static method)** 로 선언한다.
인스턴스 메서드는 호출할 때 반드시 유효한 레퍼런스(인스턴스 주소)가 있어야 한다.
레퍼런스는 인스턴스 메서드의 내장(built-in) 로컬 변수인 this에 저장된다.

이번 훈련에서는 클래스 필드/메서드 대신 인스턴스 필드/메서드를 사용하여
여러 개의 게시글을 다루는 연습을 할 것이다.
이를 통해 인스턴스 필드/메서드의 쓰임새를 확인한다.

## 훈련 목표

- 인스턴스 필드와 인스턴스 메서드를 사용할 수 있다.
- 스태틱 필드와 인스턴스 필드의 차이점과 용도를 이해한다.
- 스태틱 메서드와 인스턴스 메서드의 차이점과 용도를 이해한다.

## 훈련 내용

- 여러 개의 게시판을 다루기 위해 BoardHandler의 필드와 메서드를 인스턴스 멤버로 전환한다.
- 기존의 MemberHandler와 ProjectHandler, TaskHandler도 필드와 메서드를 인스턴스 멤버로 전환한다.

## 실습

### 1단계 - BoardHandler의 메서드를 인스턴스 메서드로 전환한다.

- add(), list() 메서드를 인스턴스 메서드로 전환한다.
  -  BoardHandler의 인스턴스 주소를 파라미터로 받지 않고 내장 변수 this로 받는다.
- App 클래스 변경
  - BoardHandler의 add(), list() 호출할 때 인스턴스 주소를 지정한다.

#### 작업 파일
- com.eomcs.pms.handler.BoardHandler 클래스 변경
  - add(), list() 메서드 변경
- com.eomcs.pms.App 클래스 변경

### 2단계 - MemberHandler, ProjectHandler, TaskHandler 클래스도 BoardHandler처럼 패키지 멤버 클래스와 인스턴스 필드, 인스턴스 메서드를 적용한다.

당장은 한 개의 목록을 유지하면 되지만,
나중에 여러 개의 목록을 유지할 경우를 대비해서
목록을 다루는 배열도 인스턴스 배열로 전환한다.


#### 작업 파일
- com.eomcs.pms.handler.MemberHandler 클래스 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskHandler 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
