# 07-b. 의존 객체 다루기 : 생성자로 주입

**생성자(constructor)** 는 인스턴스를 사용하는데 문제가 없도록,

- 인스턴스 필드를 **유효한 값** 으로 초기화시키는 일을 한다.
- **의존 객체** 주입을 강제할 수 있다.


**의존 객체 주입(DI; Dependency Injection)** 이란,

- 어떤 객체가 필요로하는 의존 객체를 외부에서 제공하는 것을 말한다.
- 보통 *생성자*나 *세터 메서드*를 통해 주입한다.

이번 훈련에서는 *생성자* 를 사용하지 않는 경우의 문제점을 확인하고,
*생성자* 를 적용해야 하는 상황을 경험해보자.

## 훈련 목표

- 인스턴스의 필드를 유효한 값으로 초기화시키지 않을 때 문제점을 확인한다.
- 생성자의 용도를 이해한다.
- 생성자를 이용하여 인스턴스를 사용하기 전에 필요한 값들을 준비한다.

## 훈련 내용

- 생성자를 통해 의존 객체를 강제로 주입하게 만든다.
- `ProjectHandler` 와 `TaskHandler` 에서 사용할 `MemberHandler` 를 강제로 주입하게 한다.


## 실습

### 1단계 - 생성자를 이용하여 ProjectHandler의 의존 객체 주입을 강제시킨다.

- com.eomcs.pms.handler.ProjectHandler 클래스 변경
  - `ProjectHandler` 의 인스턴스를 생성할 때 의존 객체를 반드시 주입하도록 생성자를 선언한다.
  - 인스턴스 필드를 전체 공개에서 패키지 멤버에게만 공개하는 모드로 바꾼다.


### 2단계 - 생성자를 이용하여 TaskHandler의 의존 객체 주입을 강제시킨다.

- com.eomcs.pms.handler.TaskHandler의 클래스 변경
  - `TaskHandler` 의 인스턴스를 생성할 때 의존 객체를 반드시 생성자를 선언한다.   
  - 인스턴스 필드를 전체 공개에서 패키지 멤버에게만 공개하는 모드로 바꾼다.


### 3단계 - 객체를 생성할 때 생성자 파라미터로 의존 객체를 주입한다.

- com.eomcs.pms.App 클래스 변경
  - `ProjectHandler` 객체를 생성할 때, 생성자 파라미터에 의존 객체를 넘겨준다.
  - `TaskHandler` 객체를 생성할 때, 생성자 파라미터에 의존 객체를 넘겨준다.


## 실습 결과

- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
