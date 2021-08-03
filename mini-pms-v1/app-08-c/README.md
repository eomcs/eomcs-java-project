# 08-c. `Composite` 디자인 패턴 : 익명 클래스 활용

각 메뉴를 구현할 때 패키지 멤버 클래스 대신에 익명 클래스를 사용한다.

## 훈련 목표

- 익명 클래스의 활용법을 익힌다.

## 훈련 내용

- 각 메뉴를 처리할 클래스를 정의할 때 익명 클래스 문법을 사용한다.

## 실습

### 1단계 - 게시판의 각 메뉴를 처리하는 클래스를 익명 클래스로 전환한다.

중첩 클래스(예: 익명 클래스)도 바깥 클래스의 멤버이기 때문에 
다른 멤버(필드, 메서드 등)를 자유롭게 사용할 수 있어서 코딩이 간결해진다.
특히 코드가 간단한 경우 익명 클래스로 정의하는 것이 읽기 쉽고 유지보수를 쉽게 만든다.

- com.eomcs.pms.App 클래스 변경 
  - BoardXxxMenu 객체를 생성할 때 익명 클래스를 적용한다.
- com.eomcs.pms.menu.BoardXxxMenu 클래스 삭제

### 2단계 - 나머지 메뉴를 처리하는 클래스도 익명 클래스로 전환한다.

- com.eomcs.pms.App 클래스 변경 
  - MemberXxxMenu 객체를 생성할 때 익명 클래스를 적용한다.
  - ProjectXxxMenu 객체를 생성할 때 익명 클래스를 적용한다.
  - TaskXxxMenu 객체를 생성할 때 익명 클래스를 적용한다.
- com.eomcs.pms.menu.MemberXxxMenu 클래스 삭제
- com.eomcs.pms.menu.ProjectXxxMenu 클래스 삭제
- com.eomcs.pms.menu.TaskXxxMenu 클래스 삭제

## 실습 결과

- src/main/java/com/eomcs/pms/App.java 변경
- src/main/java/com/eomcs/pms/menu/BoardXxxMenu.java 삭제
- src/main/java/com/eomcs/pms/menu/MemberXxxMenu.java 삭제
- src/main/java/com/eomcs/pms/menu/ProjectXxxMenu.java 삭제
- src/main/java/com/eomcs/pms/menu/TaskXxxMenu.java 삭제