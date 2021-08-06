# 10-e. 리팩토링 : Generalization + 다형성 + 의존 객체 주입(Dependecy Injection)

이번 훈련에서는 서브 클래스들의 공통 필드와 메서드를 추출하여 수퍼 클래스로 정의하는 것을 연습한다.

## 훈련 목표

- 

## 훈련 내용

- 

## 실습

### 1단계 - `ArrayList`와 `LinkedList`의 공통 분모를 추출하여 수퍼 클래스를 정의한다.

- com.eomcs.pms.handler.List 클래스 추가
  - ArrayList와 LinkedList의 공통 분모를 가져온다.
- com.eomcs.pms.handler.ArrayList 클래스 변경
  - List 클래스를 상속 받는다.
- com.eomcs.pms.handler.LinkedList 클래스 변경
  - List 클래스를 상속 받는다.

궁금?
- List는 추상 클래스이다.
- 상속해 줄 필드도 없다.
- 상속해주는 메서드 조차 추상 메서드 뿐이다.
- 이 클래스를 상속 받는 서브 클래스 입장에서 도움될 게 없다.

이점
- ArrayList와 LinkedList 를 같은 타입으로 묶을 수 있다.
  - 같은 타입으로 묶으면 두 클래스의 객체를 한 개의 레퍼런스로 다룰 수 있다.
  - List 타입에 해당하는 객체를 자유롭게 교체할 수 있다.
  - 유지보수가 더 쉬워진다.
- 추상 메서드를 통해 서브 클래스가 구현해야 할 메서드의 시그너처를 통일 할 수 있다.
  - ArrayList와 LinkedList의 사용법이 같아서 프로그래밍에 일관성을 제공한다.


### 2단계 - 핸들러에서 사용할 `List` 객체는 내부에서 만들지 말고 외부에서 주입 받는다.

- com.eomcs.pms.handler.XxxHandler 클래스 변경
  - List 객체를 외부에서 주입 받는 방식으로 변경한다.
  - 생성자를 통해서 List 객체를 주입 받는다.
- com.eomcs.pms.App 클래스 변경
  - XxxHandler 가 사용할 List 객체를 만들어 주입한다.

의존 객체를 직접 만들어 쓰지 않고 외부에서 주입 받는 방식으로 변경하면,
- 의존 객체를 교체하기가 쉬워진다.


## 실습 결과

- src/main/java/com/eomcs/pms/handler/List.java 클래스 추가
- src/main/java/com/eomcs/pms/handler/ArrayList.java 변경
- src/main/java/com/eomcs/pms/handler/LinkedList.java 변경
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경