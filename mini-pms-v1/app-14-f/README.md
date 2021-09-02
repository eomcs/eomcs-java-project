# 14-f. `Command` 디자인 패턴 : `Map`으로 커맨드 객체 관리

이번 훈련에서는 **컬렉션 API** 중에서 `Map` 객체를 이용하여
커맨드 객체를 관리하는 것을 연습할 것이다.

## 훈련 목표

- **Map** 을 이용하여 객체를 저장하고 꺼내는 것을 연습한다.
- **인터페이스** 를 통해 일관된 방법으로 객체를 사용하는 것을 확인한다.


## 훈련 내용

- 명령어를 key로 사용하여 커맨드 객체를 `Map` 객체에 담아 저장한다.
- 명령어로 커맨드 객체를 찾아 실행한다.


## 실습

### 1단계 - 서브 클래스가 아니라 외부 클래스에서 사용하는 메서드는 별도의 클래스로 분리한다.

- com.eomcs.pms.handler.AbstractProjectHandler 클래스 변경
  - promptProject() 메서드 이동
- com.eomcs.pms.handler.ProjectPrompt 클래스 추가
  - promptProject() 메서드를 가져온다.
- com.eomcs.pms.App 클래스 변경
  - ProjectPrompt 객체 필드 추가

### 2단계 - `HashMap` 객체를 이용하여 핸들러 객체를 관리한다.

- com.eomcs.pms.App 클래스 변경
  - 낱개의 레퍼런스로 커맨드 객체를 관리하던 방식을 `Map`을 이용하여 관리한다.
  - 맵 객체를 사용하면 객체를 관리하기 쉽다.
  - key : 문자열을 이용하여 메뉴 아이디를 지정한다.
  - value : Command 구현체다.

### 3단계 - `Menu` 객체에서 `Map` 객체에 들어 있는 `Command` 구현체를 사용하도록 변경한다.

- com.eomcs.pms.App 클래스 변경
  - `Menu`를 구현한 inner class `MenuItem`을 정의한다.
    - 메뉴를 실행할 때 `Map`에서 `Command` 객체를 찾아 실행한다.
  - createMenu() 변경 : 메뉴 객체를 만들 때 MenuItem 을 사용한다. 
    - MenuItem 객체를 만들 때 메뉴 아이디를 지정한다.
    - 메뉴 아이디는 해당 메뉴를 처리할 커맨드 객체의 key 와 일치해야 한다.

### 4단계 - 리팩토링 : 메뉴 생성 코드를 정리한다.

- com.eomcs.pms.App 클래스 변경
  - createMenuItem() 변경
    - 각 메뉴 그룹 생성 코드를 별도의 메서드를 분리한다.
    - 메서드 명을 createMainMenu()로 변경한다.
  - createXxxMenu() 생성 

## 실습 결과

- src/main/java/com/eomcs/pms/handler/AbstractProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectPrompt.java 추가
- src/main/java/com/eomcs/pms/App.java 변경