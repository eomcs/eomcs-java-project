package com.eomcs.pms.handler;

// Generalization을 통해 수퍼 클래스를 정의한 경우 
// 그 수퍼 클래스는 서브 클래스의 공통 분모를 모아두는 용도로 만든 것이다.
// 직접 사용하기 위해 만든 클래스가 아니다.
// 즉 서브 클래스에게 공통 분모를 상속해주기 위한 역할을 한다.
// 이런 클래스의 경우 직접 인스턴스를 만들어 사용하지 않도록 
// 추상 클래스로 선언하는 것이 유지보수에 좋다.
// 특히 추상 메서드를 갖는 경우에는 더더욱 무조건 추상 클래스로 선언해야 한다.
public abstract class List {
  // 여기서 구현할까?
  // => 어차피 서브 클래스에서 목록을 다루는 방식에 따라 구현 방법이 다르다.
  // => 따라서 수퍼 클래스에서 구현해봐야 소용없다.
  // => 그러니 구현하지 않은채로 두자!
  // => 즉 추상 메서드로 선언한다.
  public abstract void add(Object item);

  public abstract Object[] toArray();

  public abstract boolean remove(Object obj);
}
