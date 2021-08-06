# 10-c. 리팩토링 : ArrayList를 직접 사용하기

이번 훈련에서는 서브 클래스들의 공통 필드와 메서드를 추출하여 수퍼 클래스로 정의하는 것을 연습한다.

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - `BoardList` 대신 `ArrayList`를 직접 사용한다.

- com.eomcs.pms.handler.BoardHandler 클래스 변경
  - BoardList 대신 ArrayList 를 직접 사용한다.
  - BoardList에 있던 findByNo() 를 이 클래스로 가져온다.
- com.eomcs.pms.handler.BoardList 클래스 삭제


### 2단계 - `MemberList`, `ProjectList`, `TaskList` 대신 `ArrayList`를 직접 사용한다.

- com.eomcs.pms.handler.XxxHandler 클래스 변경
  - XxxList 대신 ArrayList 를 직접 사용한다.
  - XxxList에 있던 findByNo() 등의 메서드를 이 클래스로 가져온다.
- com.eomcs.pms.handler.XxxList 클래스 삭제


## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardList.java 삭제
- src/main/java/com/eomcs/pms/handler/MemberList.java 삭제
- src/main/java/com/eomcs/pms/handler/ProjectList.java 삭제
- src/main/java/com/eomcs/pms/handler/TaskList.java 삭제
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
