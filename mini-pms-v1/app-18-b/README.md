# 18-b. `Observer` 디자인 패턴 : 옵저버 패턴으로 구조를 바꾸기

이번 훈련에서는,
- **Observer 디자인 패턴** 을 프로젝트에 적용하는 것을 연습할 것이다.

**Observer 디자인 패턴**은,
- 특정 객체의 상태 변화에 따라 수행해야 하는 작업이 있을 경우,
  기존 코드를 손대지 않고(또는 최소한으로 손대거나) 손쉽게 기능을 추가하거나 제거할 수 있는 설계 기법이다.
- **발행(publish)/구독(subscribe) 모델** 이라고 부르기도 한다.
- 발행 측(publisher)에서는 구독 객체(subscriber)의 목록을 유지할 컬렉션을 가지고 있다.
- 또한 구독 객체를 등록하거나 제거하는 메서드가 있다.
- 구독 객체를 **리스너(listener)** 또는 **관찰자(observer)** 라 부르기도 한다.

## 훈련 목표
- `Observer` 디자인 패턴의 용도와 이점을 이해한다.
- Observer 디자인 패턴으로 구조를 바꾸는 것을 연습한다.

## 훈련 내용
- 인터페이스를 활용하여 옵저버 호출 규칙을 정의한다.
- 옵저버 구현체를 등록하고 제거하는 메서드와 컬렉션을 추가한다.
- 특정 상태가 되면 옵저버에게 통지하게 한다.


## 실습


### 1단계 - 애플리케이션을 시작하거나 종료할 때 실행할 옵저버의 메서드 호출 규칙을 정의한다.

- `ApplicationContextListener` 인터페이스 생성
  - Observer가 갖춰야 할 규칙을 정의한다.
  - 애플리케이션이 시작할 때 자동으로 호출할 메서드의 규칙을 정의한다.
  - 애플리케이션을 종료하기 전에 자동으로 호출할 메서드의 규칙을 정의한다.

#### 작업 파일
- com.eomcs.context.ApplicationContextListener 생성

### 2단계 - 옵저버를 저장할 컬렉션 객체와 옵저버를 추가하고 제거하는 메서드를 추가한다.

- com.eomcs.pms.App 클래스 변경
  - 옵저버를 보관할 컬렉션 객체를 추가한다.
  - 옵저버를 등록하는 메서드(`addApplicationContextListener()`)를 추가한다.
  - 옵저버를 제거하는 메서드(`removeApplicationContextListener()`)를 추가한다.

#### 작업 파일
- com.eomcs.pms.App 변경
  - 백업: App01.java

### 3단계 - 애플리케이션의 service() 실행 전/후에 옵저버에게 통지하는 코드를 추가한다.

- com.eomcs.pms.App 클래스 변경
  - service() 변경: 옵저버를 호출하는 메서드를 추가한다.
  - 백업: App02.java


### 4단계 - 애플리케이션을 시작하고 종료할 때 환영 메시지와 안내 메시지를 출력하는 코드를 옵저버로 옮긴다.

이번 단계에서는 옵저버 디자인 패턴을 적용한 후 그 사용법을 간단히 실험한다.

- com.eomcs.pms.listener.AppListener 생성
  - ApplicationContextListener를 구현한 옵저버를 만든다.
  - 애플리케이션을 시작/종료할 때 실행할 코드를 이 클래스로 옮긴다.
- com.eomcs.pms.App 변경
  - main() 변경 : 옵저버를 등록한다.
  - 백업: App03.java


### 5단계 - 리팩토링 : 옵저버를 실행하는 코드를 별도의 메서드로 분리한다.

- com.eomcs.pms.App 변경
  - notifyOnApplicationStarted() : 애플리케이션이 시작될 때 옵저버를 실행한다.
  - notifyOnApplicationEnded() : 애플리케이션이 종료될 때 옵저버를 실행한다.
  - 백업: App04.java

## 실습 결과
- src/main/java/com/eomcs/context/ApplicationContextListener.java 생성
- src/main/java/com/eomcs/pms/listener/AppInitListener.java 생성
- src/main/java/com/eomcs/pms/App.java 변경
