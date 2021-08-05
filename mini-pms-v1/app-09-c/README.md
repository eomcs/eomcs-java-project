# 09-c. 데이터 처리 코드를 캡슐화 : 캡슐화 수행

객체지향 프로그래밍을 할 때 다음 두 가지 설계 원칙을 명심해야 한다.

- 낮은 결합도(Low Coupling)
- 높은 응집력(High Cohesion)

**낮은 결합도(low coupling)** 란?
한 클래스가 많은 클래스에 의존하는 구조로 작성하면
의존 클래스가 변경될 때 마다 영향을 받기 때문에 유지보수에 좋지 않다.
그래서 가능한 의존하는 클래스의 접점을 줄이는 구조로 만드는 것이 좋다.

```
결합도 낮추기
  -> 클래스 간의 관계를 줄이기
    -> 의존 클래스의 변경에 영향을 덜 받음
      -> 코드를 변경해야 하는 상황이 줄어듬
```

**높은 응집력(High Cohesion)** 이란?
한 클래스가 너무 다양한 역할을 수행하면
클래스의 코드가 커지고 변경 사항이 잦아지기 때문에 유지보수에 좋지 않다.
가능한 한 클래스가 한 개의 역할만 수행하도록 역할을 잘게 쪼개는 것이 유지보수에 더 좋다.
역할을 작게 쪼개게 되면 해당 클래스를 다른 프로젝트에서 재사용하기가 더 쉬워진다.

```
응집력 강화
  -> 역할에 따라 클래스를 더 잘게 쪼갬
    -> 교체가 쉽고 재사용성이 높아짐
```

이번 훈련의 목표는 **응집력 강화(High Cohesion)** 를 연습해 보는 것이다.

기존의 XxxHandler 클래스는,
- 텍스트 모드 UI를 통해 사용자에게서 데이터를 입력 받고 사용자에게 결과를 출력하는 일을 한다.
- 또한 사용자가 입력한 데이터를 관리하는 일도 수행한다.

이번 훈련에서는,
- XxxHandler의 역할을 더 작게 쪼개어 전문화시킬 것이다.
- 즉 사용자의 소통을 담당하는 **UI 역할** 클래스와
데이터 처리를 담당하는 **DAO(Data Access Object) 역할** 클래스로 분리할 것이다.

이렇게 분리하게 되면,
- 나중에 데이터 처리 부분을 다른 클래스로 교체하기 쉽다.
  - 지금은 데이터를 배열에 보관하고 있지만,
    나중에 파일이나 DBMS에 보관하는 것으로 손쉽게 교체할 수 있다.
- 사용자 인터페이스(User Interface; UI)를 바꾸기가 쉽다.
  - 지금은 CLI(Command Line Interface) 방식으로 UI를 처리하고 있지만,
    나중에 웹 UI로 손쉽게 교체할 수 있다.

이렇게 특정 역할을 수행하는 코드를 별도의 클래스로 분리하는 것을 **캡슐화(encapsulation)** 라 부른다.
이렇게 캡슐화를 통해,

- 역할을 수행하는데 필요한 데이터는 필드로 정의하고
- 코드는 기능 별로 묶어서 메서드로 정의해 두면
- 복잡하게 작성된 코드를 감추는 이점이 있다.

해당 기능을 이용(메서드를 호출)하는 입장에서는,

- 그 기능을 어떤 방식으로 구현했는지 이해할 필요가 없어 매우 편리하다.
- 또한 그 기능의 구현 방식이 바뀌더라도 영향을 받지 않는다.

실생활의 예를 들면, 자동차를 이용하는 입장에서는

- 시동을 걸고, 핸들을 돌리고, 브레이크와 가속기를 다루는 사용법만 이해하면 된다.
- 바퀴를 돌리는 방식을 이해할 필요는 없다.
- 즉 디젤 엔진과 가솔린 엔진이 구동되는 방식을 이해할 필요가 없다.
- 전기차인 경우 엔진 대신 모터를 통해 바퀴를 돌리는 데 이런 것도 이해할 필요가 없다.

이렇게 구체적인 구현 내용을 감추어 유지보수와 사용을 쉽게 해주는 문법이 **캡슐화(encapsulation)** 이다.

**도메인(domain) 객체**

- 업무 분석 과정에서 도출한 *핵심 개념을 표현하는 클래스* 이다.
- 도메인 클래스는 업무에서 다뤄지는 **정보** 를 *필드* 로 선언하고 그 정보를 처리하는 **행위** 를 *메서드* 로 정의한다.
- 예) Board, Member, Projet, Task

```
class Board {
  // 정보
  int no;
  String title;

  // 업무 행위
  void add(Board board) {...}
  Board[] list() {...}
}
```

**값 객체(Value Object; VO)** 와 **서비스 객체(Service Object)**

- 실무에서는 도메인 클래스를 좀 더 쉬운 방식으로 다루기 위해 정보와 행위를 분리한다.
- 업무 정보는 필드와 getter/setter 로 구성된 VO 클래스로 정의한다.
  - **데이터 타입** 으로서 역할을 한다.
- 업무 행위를 표현한 메서드는 Service 클래스로 정의한다.
- 예) BoardVO/BoardService, MemberVO/MemberService 등

```
class Board {
  // 정보
  int no;
  String title;

  int getNo() {...}
  void setNo(int no) {...}

  String getTitle() {...}
  void setTitle(String title) {...}
}

class BoardService {}
  // 업무 행위
  void add(Board board) {...}
  Board[] list() {...}
}
```

**Domain Object** , **Value Object** , **Model Object** , **Data Transfer Object**

- 실무에서는 보통 *도메인 객체* 를 *값 객체(VO)* , *모델 객체* 라 부른다.
- 예전에는 *DTO* 라 부르기도 했다.
- 개발 회사나 개발자에 따라 이 용어를 엄격히 구분하여 사용하기도 한다.
- 그러나 대부분의 개발자는 같은 것으로 취급한다.
- 예) Board == BoardVO == BoardModel == BoardDTO


## 훈련 목표

- 클래스를 만들고 필드와 메서드를 정의하는 것을 연습한다.
- 역할에 따라 클래스를 분리하는 방법과 이점을 이해한다.
- 캡슐화의 의미를 이해한다.
- 도메인 클래스를 패키지 멤버 클래스로 만드는 이유를 이해한다.
- 고정 크기의 배열을 늘리는 방법을 배운다.

## 훈련 내용

- XxxHandler 클래스에서 데이터 처리 코드를 캡슐화하여 XxxList 클래스로 정의한다.
- XxxList 클래스에 정의된 도메인 클래스를 패키지 멤버 클래스로 만든다.
- add()가 실행될 때 배열의 크기를 검사하여 배열이 꽉차면 배열을 자동으로 늘린다.

## 실습

`app-08-d` 단계의 소스를 가지고 수행한다.

### 1단계 - BoardHandler 에서 데이터 처리 코드를 별도의 클래스로 분리하여 캡슐화한다.

- `BoardHandler`에서 데이터 처리 코드를 분리하여 `BoardList` 클래스로 정의한다.
- `add()`, `toArray()`, `findByNo()`, `indexOf()`, `remove()` 메서드를 정의한다.
- `BoardHandler`는 `BoardList`를 사용하여 입,출력 데이터를 처리한다.

#### 작업 파일

- com.eomcs.pms.handler.BoardList 클래스 추가
- com.eomcs.pms.handler.BoardHandler 클래스 변경


### 2단계 - MemberHandler, ProjectHandler, TaskHandler 에서 데이터 처리 코드를 별도의 클래스로 분리하여 캡슐화한다.

- `BoardHandler`와 마찬가지이다.

#### 작업 파일

- com.eomcs.pms.handler.MemberList 클래스 추가
- com.eomcs.pms.handler.MemberHandler 클래스 변경
- com.eomcs.pms.handler.ProjectList 클래스 추가
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskList 클래스 추가
- com.eomcs.pms.handler.TaskHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardList.java 추가
- src/main/java/com/eomcs/pms/handler/MemberList.java 추가
- src/main/java/com/eomcs/pms/handler/ProjectList.java 추가
- src/main/java/com/eomcs/pms/handler/TaskList.java 추가
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
