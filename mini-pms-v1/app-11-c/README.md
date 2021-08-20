# 11-c. 자료 구조 다루기 : 제네릭이 필요한 이유와 사용법

**제네릭(generic)** 문법을 이용하면,

- 같은 일을 하는 클래스를 정의할 때 타입 별로 중복해서 정의할 필요가 없기 때문에 코드의 재사용성을 높인다.
- 지정된 타입의 객체만 다루도록 제한할 수 있어 코드의 안정성을 높인다.
- 사용할 객체의 타입을 지정한 후 잘못된 타입의 객체를 사용할 때 컴파일 오류가 발생한다.
- 컴파일 할 때 타입 검사를 진행하기 때문에 빠른 시점에 타입 안정성을 어긴 오류를 찾아 낼 수 있다.
  - 가능한 실행할 때 발생된 오류 보다는 컴파일 할 때 발생된 오류를 잡는 것이 더 낫다.


## 훈련 목표

- 제네릭 문법을 이용하여 타입 정보를 파라미터로 주고 받는 방법을 배운다.
- 제네릭 문법으로 특정 타입의 값만 다루도록 제한하는 것을 연습한다.

## 훈련 내용

- ArrayList 클래스에 특정 타입의 객체를 다룰 수 있도록 제네릭을 적용한다.
- 기존의 XxxHandler 에 제네릭이 적용된 ArrayList을 사용하도록 코드를 변경한다.


## 실습

### 1단계 - `List` 에 제네릭(generic) 문법을 적용한다.

- `com.eomcs.util.List` 인터페이스 변경

### 2단계 - `List` 인터페이스를 구현한 클래스에 제네릭을 적용한다.

- `com.eomcs.util.AbstractList` 클래스 변경
- `com.eomcs.util.ArrayList` 클래스 변경
- `com.eomcs.util.LinkedList` 클래스 변경

### 3단계 - `ArrayList` 또는 `LinkedList` 클래스를 사용하는 쪽에 제네릭을 적용한다.

- `com.eomcs.pms.App` 클래스 변경
- `com.eomcs.pms.handler.XxxHandler` 클래스 변경

### 4단계 - 제네릭이 적용된 `toArray()` 추가한다.

- `com.eomcs.util.List` 인터페이스 변경
  - `E[] toArray(E[])` 메서드 추가
- `com.eomcs.util.ArrayList` 클래스 변경
  - `E[] toArray(E[])` 메서드 구현
- `com.eomcs.util.LinkedList` 클래스 변경
  - `E[] toArray(E[])` 메서드 구현

### 5단계 - 제네릭이 적용된 `toArray()`를 사용하는 핸들러를 변경한다.

- `com.eomcs.pms.handler.XxxHandler` 클래스 변경


## 실습 결과

- src/main/java/com/eomcs/util/List.java 변경
- src/main/java/com/eomcs/util/AbstractList.java 변경
- src/main/java/com/eomcs/util/ArrayList.java 변경
- src/main/java/com/eomcs/util/LinkedListList.java 변경
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
