# 10-a. 리팩토링 : Generalization

이번 훈련에서는 서브 클래스들의 공통 필드와 메서드를 추출하여 수퍼 클래스로 정의하는 것을 연습한다.

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - `BoardList`, `MemberList`, `ProjectList`, `TaskList` 의 공통점을 뽑아 수퍼 클래스 `ArrayList`를 정의한다.

- com.eomcs.pms.handler.ArrayList 클래스 추가
- com.eomcs.pms.handler.BoardList 클래스 변경
  - ArrayList를 상속 받는다.
- com.eomcs.pms.handler.MemberList 클래스 변경
  - ArrayList를 상속 받는다.
- com.eomcs.pms.handler.ProjectList 클래스 변경
  - ArrayList를 상속 받는다.
- com.eomcs.pms.handler.TaskList 클래스 변경
  - ArrayList를 상속 받는다.


### 2단계 - `XxxHandler` 클래스는 `XxxList2` 대신에 `XxxList`를 사용한다.

- com.eomcs.pms.handler.BoardHandler 클래스 변경
- com.eomcs.pms.handler.MemberHandler 클래스 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskHandler 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/handler/ArrayList.java 추가
- src/main/java/com/eomcs/pms/handler/BoardList.java 변경
- src/main/java/com/eomcs/pms/handler/MemberList.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectList.java 변경
- src/main/java/com/eomcs/pms/handler/TaskList.java 변경
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
