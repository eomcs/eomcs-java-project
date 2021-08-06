# 10-f. 리팩토링 : 메서드 이동

이번 훈련에서는 리팩토링 기법 중에서 메서드 이동을 연습한다.

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - `ProjectHandler`와 `TaskHandler`의 메서드를 `MemberHandler` 클래스로 이동한다.

- com.eomcs.pms.handler.MemberHandler 클래스 변경
  - `promptOwner()`, `promptMembers()` 메서드를 가져온다.
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskHandler 클래스 변경

메서드 이동, 
- 더 적절한 클래스로 이동하는 것이 코드를 이해하기 쉽고 유지보수 하기 좋게 만든다.


## 실습 결과

- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
