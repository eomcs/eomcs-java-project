package com.eomcs.context;

import java.util.Map;

// 규칙
// - 애플리케이션이 시작하거나 종료할 때 호출할 리스너(옵저버)의 메서드를 정의한다.
//
public interface ApplicationContextListener {
  void contextInitialized(Map<String,Object> params); // 애플리케이션이 시작한 후 즉시 호출된다.
  void contextDestroyed(Map<String,Object> params); // 애플리케이션이 종료하기 직전에 호출된다.
}
