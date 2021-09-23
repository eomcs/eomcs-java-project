# 18-c. `Observer` 디자인 패턴 : 옵저버로 파일 입출력 처리하기

이번 훈련에서는,
- **Observer 디자인 패턴** 의 활용을 연습할 것이다.

## 훈련 목표
- Observer 패턴 구조에서 기능을 추가하는 방법을 연습한다.
- 발행자와 구독자 간에 데이터를 공유하는 방법을 연습한다.

## 훈련 내용
- App 클래스가 하던 데이터 로딩 및 저장 기능을 옵저버로 옮긴다.
- 옵저버와 발행자 간의 객체를 공유한다.


## 실습

### 1단계 - 데이터를 로딩하고 저장하는 일을 할 옵저버를 정의한다.

- `com.eomcs.context.FileListener` 클래스 생성
  - 옵저버의 규칙인 `ApplicationContextListener` 를 구현한다.
  - contextInitialized()에서 게시글, 회원, 프로젝트, 작업 데이터를 파일에서 로딩한다.
  - contextDestroyed()에서 그 데이터를 파일에 JSON 형식으로 저장한다.
  - App 클래스에서 파일 데이터를 로딩하고 저장하는 코드를 이 클래로 옮긴다.
- `com.eomcs.pms.App` 클래스 변경
  - `FileListener` 옵저버를 등록한다.


### 2단계 - 발행자와 옵저버(구독자) 간의 데이터를 공유할 수 있도록 코드를 변경한다.

- `App` 클래스 변경
  - 옵저버와 값을 공유하기 위해 사용할 맵 객체를 준비한다.
  - 옵저버를 호출할 때 맵 객체를 넘겨준다.
- `com.eomcs.context.ApplicationContextListener` 인터페이스 변경
  - contextInitialized(), contextDestroyed() 메서드에 Map 타입의 파라미터 추가한다.
- `com.eomcs.pms.listener.AppListener` 클래스 변경
  - 변경된 규칙에 따라 구현 메서드에 파라미터 추가한다.
- `com.eomcs.pms.listener.FileListener ` 클래스 변경
  - 변경된 규칙에 따라 구현 메서드에 파라미터 추가한다.
  - 작업 결과를 맵 객체에 보관한다.
  - 맵 객체에 보관된 데이터를 꺼낸다.
- `com.eomcs.pms.App` 클래스 변경
  - service()를 호출하기 전에 FileListener 객체를 등록한다.



## 실습 결과
- src/main/java/com/eomcs/context/ApplicationContextListener.java 변경
- src/main/java/com/eomcs/pms/listener/AppListener.java 변경
- src/main/java/com/eomcs/pms/listener/FileListener.java 추가
- src/main/java/com/eomcs/pms/App.java 변경
