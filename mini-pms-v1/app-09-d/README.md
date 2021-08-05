# 09-d. 데이터 처리 코드를 캡슐화 : 캡슐화 후 -  배열 크기 변경

이번 훈련에서는 **High Cohesion** 설계 기법의 이점을 확인한다.
- 클래스를 역할에 따라 잘게 쪼개면 변경하기가 더 쉽다.
- 다른 클래스에 영향을 덜 끼친다.
- 부품 교체하듯이 같은 역할을 수행하는 다른 클래스로 교체가 쉽다.
- 즉 코드를 유지보수 하기가 쉬워진다.



## 훈련 목표

- High Cohesion의 이점을 확인한다.

## 훈련 내용

- 

## 실습

### 1단계 - `BoardList`의 배열을 가변 크기를 갖는 배열로 변경한다.

- 게시글을 추가할 때 배열의 크기가 작으면 새 배열을 만들어 크기를 늘린다.
  - add() 메서드 변경

#### 작업 파일

- com.eomcs.pms.handler.BoardList 클래스 변경


### 2단계 - `MemberList`, `ProjectList`, `TaskList`의 배열도 가변 크기를 갖는 배열로 변경한다.

- 값을 추가할 때 배열의 크기가 작으면 새 배열을 만들어 크기를 늘린다.
  - add() 메서드 변경

#### 작업 파일

- com.eomcs.pms.handler.MemberList 클래스 변경
- com.eomcs.pms.handler.ProjectList 클래스 변경
- com.eomcs.pms.handler.TaskList 클래스 변경



## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardList.java 변경
- src/main/java/com/eomcs/pms/handler/MemberList.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectList.java 변경
- src/main/java/com/eomcs/pms/handler/TaskList.java 변경
