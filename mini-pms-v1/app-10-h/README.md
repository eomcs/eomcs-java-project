# 10-h. 리팩토링 : 세터(setter), 게터(getter) 적용

이번 훈련에서는 **캡슐화(encapsulation)** 를 다룰 것이다.

기존에 정의한 도메인 클래스에 접근 제어 문법을 적용하여
인스턴스 필드의 값을 외부에서 직접 접근하지 못하게 막고,
세터(setter)/게터(getter) 메서드를 통해 값을 조회하고 변경하게 할 것이다.

**캡슐화** 는,

- 특정 역할을 수행하는 코드를 *클래스* 라는 **캡슐** 에 감추고
- **메서드** 라는 도구를 통해 해당 코드를 이용하게 하는 기법이다.
- 이를 통해 **기능 구현에 대한 코드의 상세 내용을 감춤** 으로써 코드의 유지보수가 쉬워지고, 이용하기가 편해진다.

캡슐화 예로 **컴퓨터** 를 들 수 있다.

- 컴퓨터는 CPU, 메인보드, 메모리, 하드디스크 등 복잡한 부품을 케이스에 감춘다.
- 대신 키보드, 마우스, 모니터를 통해 컴퓨터의 계산 기능을 이용한다.
- 컴퓨터를 이용하는 사용자 입장에서는 복잡한 구조를 알 필요가 없어서 편하다.
- 컴퓨터를 만드는 입장에서도 상황에 맞춰 언제든 내부 부품을 바꿀 수 있어 편하다.

일반적으로 클래스는 다음의 구성 요소를 포함한다.

- 데이터를 보관할 **필드(field)** : 인스턴스 필드와 클래스 필드
- 필드를 다루는 **메서드(method)** : 생성자, 인스턴스 메서드, 클래스 메서드

캡슐화를 수행할 때 핵심은,

- 이들 **구성 요소에 대한 접근을 적절하게 통제하는** 것이다.
- 즉 **권한이 없는 접근을 차단하여 잘못된 동작을 막는 것** 이다.

접근을 통제하는 일반적인 방법은,

- **필드는 내부에서만 접근** 하도록 제한하고
- 필드를 다루는 **메서드는 외부에서 호출할 수 있도록 공개** 한다.
- 단 메서드가 내부에서만 사용된다면 공개하지 않는다.

메서드 중에서 특히 필드의 값을 설정하고 조회하는 메서드를 **세터/게터** 라 부른다.

- **게터(getter)** : 필드 값을 조회하는 메서드. 보통 메서드 이름이 `get` 으로 시작한다.
  - 예) `getName()` , `getAge()`
- **세터(setter)** : 필드 값을 설정하는 메서드. 보통 메서드 이름이 `set` 으로 시작한다.
  - 예) `setName(String name)` , `setAge(int age)`

참고적으로 OOA/D(Object-Oriented Analysis/Design)에서는,

- **인스턴스 필드** 를 다루는 **인스턴스 메서드** 를 **연산자(operator)** 라 부른다.

자바에서 접근을 통제하는 문법은 다음과 같다.

- `private` : 클래스 내부
- (default) : 클래스 내부 + 같은 패키지 소속
- `protected` : 클래스 내부 + 같은 패키지 소속 + 상속받은 필드를 소유한 서브 클래스
- `public` : 모든 클래스
- 이 문법은 **추상화** 가 무너지지 않게 도와준다.

## 훈련 목표

- 변수를 다루는 연산자의 관점에서 인스턴스 필드와 인스턴스 메서드를 이해한다.
- 메서드를 활용하여 인스턴스 값을 다루는 연산자를 정의하는 방법을 배운다.
- 캡슐화의 의미를 이해하고, 셋터/겟터를 만드는 것을 연습한다.

## 훈련 내용

- 게시글, 회원, 프로젝트, 작업 정보를 다루는 도메인 클래스의 인스턴스 필드에 대한 접근을 제한한다.
- 인스턴스 필드의 값을 설정하고 조회하는 세터/게터를 정의한다.  


## 실습

### 1단계 - Board, Member, Project, Task 도메인 클래스에 캡슐화 문법을 적용한다.

- `Board` 의 인스턴스 필드를 비공개(`private`)로 전환한다.
- 대신 인스턴스 필드 값을 설정하고 조회하는 setter/getter 를 추가한다.
- `BoardHandler` 에서 세터/게터를 통해 인스턴스 필드 값을 다루도록 변경한다.

#### 작업 파일

- com.eomcs.pms.domain.Board 클래스 변경
- com.eomcs.pms.domain.Member 클래스 변경
- com.eomcs.pms.domain.Project 클래스 변경
- com.eomcs.pms.domain.Task 클래스 변경


### 2단계 - XxxList 클래스에 캡슐화 문법을 적용한다

- XxxList 클래스의 인스턴스 필드를 비공개(`private`)로 전환한다.
- 인스턴스 필드 값을 다루는 메서드를 공개한다.
- 도메인 객체의 필드를 사용할 때 게터와 세터를 호출한다.

#### 작업 파일

- com.eomcs.pms.handler.BoardList 클래스 변경
- com.eomcs.pms.handler.MemberList 클래스 변경
- com.eomcs.pms.handler.ProjectList 클래스 변경
- com.eomcs.pms.handler.TaskList 클래스 변경


### 3단계 - XxxHandler 클래스에 캡슐화 문법을 적용한다

- XxxList 클래스의 인스턴스 필드를 비공개(`private`)로 전환한다.
- 인스턴스 필드 값을 다루는 메서드를 공개한다.
- 회원 여부를 검사하여 회원 이름을 입력 받는 메서드를 MemberHandler 클래스로 옮긴다.
  - ProjectHandler와 TaskHandler에 있던 inputMember(), inputMembers()를 가져온다.

#### 작업 파일

- com.eomcs.pms.handler.BoardHandler 클래스 변경
- com.eomcs.pms.handler.MemberHandler 클래스 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/domain/Board.java 변경
- src/main/java/com/eomcs/pms/domain/Member.java 변경
- src/main/java/com/eomcs/pms/domain/Project.java 변경
- src/main/java/com/eomcs/pms/domain/Task.java 변경
- src/main/java/com/eomcs/pms/handler/BoardList.java 변경
- src/main/java/com/eomcs/pms/handler/MemberList.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectList.java 변경
- src/main/java/com/eomcs/pms/handler/TaskList.java 변경
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
