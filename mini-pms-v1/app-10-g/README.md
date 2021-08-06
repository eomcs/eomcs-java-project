# 10-g. 리팩토링 : 인터페이스 적용

이번 훈련에서는 인터페이스 활용을 연습한다.

## 훈련 목표
 
- 

## 훈련 내용

- 

## 실습

### 1단계 - `List` 추상 클래스를 인터페이스 문법으로 바꾼다.

- com.eomcs.pms.handler.List 클래스 변경
- com.eomcs.pms.handler.ArrayList 클래스 변경
  - `List`가 인터페이스이기 때문에 상속이 아닌 구현으로 변경한다.
- com.eomcs.pms.handler.LinkedList 클래스 변경
  - `List`가 인터페이스이기 때문에 상속이 아닌 구현으로 변경한다.

인터페이스 문법을 사용하는 이유,
- 필드나 메서드를 상속해주는 역할이 아니라
- 여러 객체를 한 타입으로 묶어주는 역할을 하면서,
- 각 객체가 해야할 일을 규칙으로 정의할 수 있기 때문이다.
- 예를 들면, List 의 경우가 대표적이다.
  - ArrayList, LinkedList에게 상속해 주는 필드나 메서드는 없다.
  - 단지 두 클래스가 반드시 구현해야 할 메서드를 추상 메서드로 선언하였다.
  - 그리고 두 클래스를 교차로 사용할 수 있도록 한 타입으로 묶어주는 역할을 한다.
  - 이런 경우 인터페이스로 정의하는 것이 추상 클래스로 정의하는 것 보다 낫다.

## 실습 결과

- src/main/java/com/eomcs/pms/handler/List.java 변경
- src/main/java/com/eomcs/pms/handler/ArrayList.java 변경
- src/main/java/com/eomcs/pms/handler/LinkedList.java 변경
