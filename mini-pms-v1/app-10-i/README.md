# 10-i. 리팩토링 : 추상 클래스 적용

이번 훈련에서는 **추상 클래스** 를 다룰 것이다.

추상 클래스 활용,
- 인터페이스의 일부 규칙을 미리 구현할 필요가 있을 때 
- 서브 클래스에게 상속해 줄 필드나 메서드가 있을 때


## 훈련 목표

- 

## 훈련 내용

-  


## 실습

### 1단계 - List 인터페이스에 size() 규칙을 추가한다.

- com.eomcs.pms.handler.List 인터페이스 변경
  - size() 메서드 추가

### 2단계 - List 인터페이스를 구현한 AbstractList 추상 클래스를 정의한다.

- com.eomcs.pms.handler.AbstractList 추상 클래스 추가
  - size() 메서드 구현 

### 3단계 - ArrayList와 LinkedList는 AbstractList를 상속 받는다.

- com.eomcs.pms.handler.ArrayList 클래스 변경
- com.eomcs.pms.handler.LinkedList 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/handler/List.java 변경
- src/main/java/com/eomcs/pms/handler/AbstractList.java 추가
- src/main/java/com/eomcs/pms/handler/ArrayList.java 변경
- src/main/java/com/eomcs/pms/handler/LinkedList.java 변경
