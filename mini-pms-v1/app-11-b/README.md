# 11-b. 자료 구조 다루기 : 리팩토링 - 클래스 및 패키지 이동

이번 훈련에서는 **리팩토링** 기법 중에서 **클래스 이동**을 연습해 보자. 

**클래스 이동** 은
- 서로 관련되거나 유사한 역할을 하는 클래스를 패키지를 이용하여 분류하는 것

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - 객체 목록 관리와 관련된 인터페이스 및 클래스를 별도의 패키지로 분류한다.

- com.eomcs.pms.handler.List 인터페이스 이동
  - com.eomcs.util 패키지로 이동
  - List 관련 클래스 및 인터페이스 모두 이동


### 2단계 - 메뉴 관련 패키지를 이동한다.

- com.eomcs.pms.menu 패키지 변경
  - 메뉴 관련 클래스는 여러 프로젝트가 공유할 수 있다.
  - com.eomcs.menu 패키지로 이동한다.

## 실습 결과

- src/main/java/com/eomcs/util/List.java 변경
- src/main/java/com/eomcs/util/AbstractList.java 변경
- src/main/java/com/eomcs/util/ArrayList.java 변경
- src/main/java/com/eomcs/util/LinkedList.java 변경
- src/main/java/com/eomcs/util/Stack.java 변경
- src/main/java/com/eomcs/menu/Menu.java 변경
- src/main/java/com/eomcs/menu/MenuGroup.java 변경
- src/main/java/com/eomcs/pms/handler/XxxHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
