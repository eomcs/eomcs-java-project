# 14-c. `Command` 디자인 패턴 : 리팩토링

이번 훈련에서는 **커맨드 패턴(command pattern)** 을 프로젝트에 적용할 것이다.

**커맨드 디자인 패턴** 은,

- *메서드의 객체화* 설계 기법이다.
- 한 개의 명령어를 처리하는 메서드를 별개의 클래스로 분리하는 기법이다.
- 이렇게 하면 명령어가 추가될 때마다 새 클래스를 만들면 되기 때문에  
  기존 코드를 손대지 않아서 유지보수에 좋다.
- 즉 기존 소스에 영향을 끼치지 않고 새 기능을 추가하는 방식이다.
- 명령처리를 별도의 객체로 분리하기 때문에 실행 내역을 관리하기 좋고,
  각 명령이 수행했던 작업을 다루기가 편하다.
- 인터페이스를 이용하면 메서드 호출 규칙을 단일화 할 수 있기 때문에
  코딩의 일관성을 높혀줄 수 있다.
- 단 기능 추가할 때마다 해당 기능을 처리하는 새 클래스가 추가되기 때문에
  클래스 개수는 늘어난다.
- 그러나 유지보수 측면에서는 기존 코드를 변경하는 것 보다는
  클래스 개수가 늘어나는 것이 좋다.
- 유지보수 관점에서는 소스 코드를 일관성 있게 유지보수 할 수 있는게 더 중요한다.


## 훈련 목표

- **커맨드 패턴** 의 클래스 구조와 구동원리를 이해한다.
- **커맨드 패턴** 을 구현하는 방법을 배운다.


## 훈련 내용

- 사용자 명령을 처리할 때 호출할 메서드의 규칙을 인터페이스로 정의한다.
- 명령어를 처리하는 메서드를 인터페이스에 맞춰 별개의 클래스로 캡슐화 한다.

## 실습

### 1단계 - 프로젝트 멤버의 이름 목록을 리턴하는 메서드를 이동한다.

- com.eomcs.pms.handler.AbstractProjectHandler 클래스 변경
  - getMemberNames() 메서드를 `Project` 클래스로 이동한다. 
- com.eomcs.pms.domain.Project 클래스 변경
  - getMemberNames() 메서드를 가져온다.
- com.eomcs.pms.handler.ProjectDetailHandler 클래스 변경
- com.eomcs.pms.handler.ProjectListHandler 클래스 변경
- com.eomcs.pms.handler.ProjectUpdateHandler 클래스 변경

### 2단계 - 인스턴스 멤버를 스태틱 멤버로 전환한다.

- com.eomcs.pms.handler.TaskHandler 클래스 변경
  - printTasks() 변경 : 스태틱 메서드로 전환


### 3단계 - 회원 정보를 요구하는 메서드를 별도의 클래스로 분리한다.

- com.eomcs.pms.handler.AbstractMemberHandler 클래스 변경
  - promptXxx() 메서드 이동 : 서브 클래스에서 사용하는 것이 아니라 외부 클래스에서 사용하는 메서드이기 때문에 별도의 클래스로 분리한다.
- com.eomcs.pms.handler.MemberPromptHandler 클래스 생성
  - promptXxx() 메서드를 가져온다.
  - com.eomcs.pms.handler.ProjectAddHandler 클래스 변경
  - com.eomcs.pms.handler.ProjectUpdateHandler 클래스 변경
  - com.eomcs.pms.handler.TaskAddHandler 클래스 변경
  - com.eomcs.pms.handler.TaskUpdateHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경
  - MemberPromptHandler 객체 준비

### 4단계 - 프로젝트 정보에서 특정 작업을 조회하는 메서드를 이동한다.

- com.eomcs.pms.handler.AbstractTaskHandler 클래스 변경
  - findByNo(Project,int) 메서드 이동 : Project 클래스로 이동
- com.eomcs.pms.domain.Project 클래스 변경
  - findTaskByNo(int) 메서드 가져오기
  - com.eomcs.pms.handler.TaskDeleteHandler 클래스 변경
  - com.eomcs.pms.handler.TaskDetailHandler 클래스 변경
  - com.eomcs.pms.handler.TaskUpdateHandler 클래스 변경


## 실습 결과

