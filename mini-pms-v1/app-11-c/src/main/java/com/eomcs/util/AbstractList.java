package com.eomcs.util;

// 추상 클래스에서 List에 적용할 타입 이름을 E 변수에 받는다.
// 그 변수의 값을 List 인터페이스에 적용한다.
// 이때 추상 클래스에 최종적인 타입 이름은 
// 서브 클래스를 정의할 때 전달한다.
public abstract class AbstractList<E> implements List<E> {
  protected int size;

  @Override
  public int size() {
    return this.size;
  }
}
