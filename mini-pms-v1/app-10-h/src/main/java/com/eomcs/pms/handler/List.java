package com.eomcs.pms.handler;

// List 클래스
// - ArrayList와 LinkedList를 한 타입으로 묶는 일을 한다.
// - 상속해 줄 필드는 없다.
// - 상속해 줄 메서드도 없다.
// - 다만 서브 클래스에게 반드시 구현하라고 강요하는 추상 메서드는 있다.
// - 이런 경우 차라리 "객체 사용 규칙"을 전문적으로 정의하는 인터페이스를 사용하는 것이 낫다.
// 
// 인터페이스
// - 모든 필드는 public, static, final 필드이다.
// - 모든 메서드는 public, abstract 메서드이다.
// - default 키워드를 이용하여 구현 메서드를 만들수는 있다.
// - 객체 사용 규칙을 정의할 때 사용하는 문법이다.
// - 당연히 인스턴스를 생성할 수 없다.
// 
public interface List {
  // 인터페이스의 메서드는 기본적으로 public이고 abstract 이다.
  void add(Object item);
  Object[] toArray();
  boolean remove(Object obj);
}









