package com.eomcs.util;

// 인터페이스에 제네릭(generic)의 타입 파라미터를 적용하면,
// 그 타입의 최종적인 이름은 
// 이 인터페이스를 구현하는 클래스에서 전달한다.
public interface List<E> {
  void add(E item);
  Object[] toArray();
  boolean remove(E obj);
  int size();
  E get(int index);
  E remove(int index);
  E[] toArray(E[] arr);
}









