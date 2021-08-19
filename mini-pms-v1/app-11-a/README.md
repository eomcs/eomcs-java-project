# 11-a. 자료 구조 다루기 : 스택 구현과 사용

이번 훈련에서는 **스택(stack)** 방식으로 데이터를 저장하는 자료 구조를 만들어보자.

**스택(stack)** 은
- LIFO(Last In First Out) 방식으로 데이터를 넣고 꺼낸다.
- 데이터를 넣는 것을 `push`라고 하고, 데이터를 꺼내는 것을 `pop`이라 한다.
- 보통 입력한 역순으로 데이터를 꺼내야 하는 상황에서 이 자료구조를 사용한다.
- 예)
  - JVM 스택 메모리 영역에서 메서드 호출을 관리할 때
  - 웹 브라우저에서 이전 페이지로 따라 올라 갈 때
  - 자바스크립트에서 이벤트를 처리할 때 버블링 단계를 수행(부모 엘리먼트를 따라 올라가면서 처리하는 것)


## 훈련 목표

- 스택(stack) 자료구조를 구현하고 구동 원리를 이해한다.

## 훈련 내용

- `java.util.Stack` 을 모방하여 `Stack` 클래스를 구현한다.
- 사용자가 선택한 메뉴를 스택에 보관한다.
- 메뉴를 출력할 때 마다 스택에 보관된 메뉴 경로를 출력한다.

## 실습

### 1단계 - List 인터페이스에 인덱스로 항목을 꺼내는 메서드를 추가한다.

인덱스로 목록에서 값을 꺼내거나 삭제하는 메서드를 준비한다.

- com.eomcs.pms.handler.List 인터페이스 변경
  - get(int) 메서드 추가
  - remove(int) 메서드 추가
- com.eomcs.pms.handler.ArrayList 클래스 변경
  - get(int) 메서드 구현
  - remove(int) 메서드 구현
- com.eomcs.pms.handler.LinkedList 클래스 변경
  - get(int) 메서드 구현
  - remove(int) 메서드 구현


### 2단계 - `java.util.Stack` 를 모방하여 `Stack` 클래스를 구현한다.

**스택(stack)** 자료 구조를 직접 구현해본다.

- com.eomcs.pms.handler.Stack 클래스 추가
  - 상속 문법을 이용하여 기존 코드를 확장한다.
    - `com.eomcs.pms.List` 클래스를 상속 받아 정의한다.
  - push(), pop() 메서드를 추가한다.


### 3단계 - 사용자가 메뉴를 선택하면 현재 메뉴의 경로를 bread crumb 으로 출력한다.

- com.eomcs.pms.menu.MenuGroup 클래스 변경
  - `Stack` 객체를 준비한다.
  - 사용자가 메뉴를 선택하면 해당 메뉴를 스택에 push 한다.
  - 사용자가 이전 메뉴로 돌아가면 현재 메뉴를 스택에서 pop 한다.

```
[메인]  <--- breadcrumb
1. 게시판
2. 회원
3. 프로젝트
4. 작업
5. 관리1
0. 종료
선택> 5

[메인 / 관리1]  <--- breadcrumb
1. 관리2
0. 이전 메뉴
선택> 1

[메인 / 관리1 / 관리2]  <--- breadcrumb
1. 관리3
0. 이전 메뉴
선택> 1

[메인 / 관리1 / 관리2 / 관리3]  <--- breadcrumb
0. 이전 메뉴
선택> 0

[메인 / 관리1 / 관리2]  <--- breadcrumb
1. 관리3
0. 이전 메뉴
선택> 0

[메인 / 관리1]  <--- breadcrumb
1. 관리2
0. 이전 메뉴
선택> 0

[메인]  <--- breadcrumb
1. 게시판
2. 회원
3. 프로젝트
4. 작업
5. 관리1
0. 종료
선택> 

```

## 실습 결과

- src/main/java/com/eomcs/pms/handler/List.java 변경
- src/main/java/com/eomcs/pms/handler/ArrayList.java 변경
- src/main/java/com/eomcs/pms/handler/LinkedList.java 변경
- src/main/java/com/eomcs/pms/handler/Stack.java 추가
- src/main/java/com/eomcs/pms/menu/MenuGroup.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
