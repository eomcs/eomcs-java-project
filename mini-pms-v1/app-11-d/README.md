# 11-d. 자료 구조 다루기 : 자바 컬렉션 API 사용하기

이번 훈련에서는 우리가 직접 만든 `List`, `Stack` 대신에
자바 제공하는 **자바 컬렉션 API** 를 사용하여 데이터 목록을 다룰 것이다.

**자바 컬렉션 API**는,

- **컬렉션(collection)** 은 여러 개의 항목을 담는 *컨테이너(container)* 객체이다.
- 즉 데이터를 저장하고, 꺼내고, 삭제하는 등의 데이터 목록을 다룰 때 사용하는 객체다.   
- 공식적으로는 **컬렉션 프레임워크(collection framework)** 라고 부른다.

`java.util.*` 패키지,

- 컬렉션과 관련된 인터페이스와 클래스들이 들어 있다.
- 자료구조, 탐색, 정렬 등 다양한 알고리즘에 대한 객체 사용 규칙을 *인터페이스*로 정의하고 있다.
- 또한 그 인터페이스의 구현체를 제공한다.


## 훈련 목표

- 자바에서 제공하는 **컬렉션 API** 의 사용법을 익힌다.
- `java.util.*` 패키지의 주요 클래스들을 사용해보고 내부 구조를 이해한다.


## 훈련 내용

- 기존의 컬렉션 관련 클래스를 삭제한다.
- `java.util.List` 인터페이스를 사용한다.
- `java.util.ArrayList` 클래스를 사용한다.
- `java.util.LinkedList` 클래스를 사용한다.
- `java.util.Stack` 클래스를 사용한다.

## 실습

### 1단계 - `List`, `ArrayList`, `LinkedList`, `Stack` 을 자바 컬렉션 API로 교체한다.

- com.eomcs.pms.handler.XxxHandler 클래스를 변경한다.
  - 기존 컬렉션 클래스를 자바 컬렉션 API로 교체한다.
- `com.eomcs.pms.App` 클래스를 변경한다.
  - 자바에서 제공하는 같은 이름의 컬렉션 클래스로 교체한다.


## 실습 결과

- src/main/java/com/eomcs/util/List.java 삭제
- src/main/java/com/eomcs/util/AbstractList.java 삭제
- src/main/java/com/eomcs/util/ArraryList.java 삭제
- src/main/java/com/eomcs/util/LinkedList.java 삭제
- src/main/java/com/eomcs/util/Stack.java 삭제
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
