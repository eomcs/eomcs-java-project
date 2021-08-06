# 10-d. 리팩토링 : LinkedList를 직접 사용하기

이번 훈련에서는 서브 클래스들의 공통 필드와 메서드를 추출하여 수퍼 클래스로 정의하는 것을 연습한다.

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - `ArrayList` 대신 `LinkedList`를 사용한다.

- com.eomcs.pms.handler.XxxHandler 클래스 변경
  - ArrayList 대신 LinkedList 를 사용한다.
- com.eomcs.pms.handler.XxxList2 클래스 삭제



## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardList2.java 삭제
- src/main/java/com/eomcs/pms/handler/MemberList2.java 삭제
- src/main/java/com/eomcs/pms/handler/ProjectList2.java 삭제
- src/main/java/com/eomcs/pms/handler/TaskList2.java 삭제
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
