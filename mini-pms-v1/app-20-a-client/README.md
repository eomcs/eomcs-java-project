# 20-a. 데이터 처리 코드를 캡슐화하기 : DAO 클래스 도입

이번 훈련에서는,
- **DAO** 의 역할을 이해하고 데이터 처리 코드를 분리하는 연습을 한다.

**DAO(Data Access Object)**
- DBMS 또는 File 을 이용하여 데이터를 저장, 조회, 변경, 삭제하는 일을 하는 객체이다.
- 데이터 처리 로직을 DAO 객체로 분리해두면 객체를 재사용하거나 교체하기가 쉬워진다.

**High Cohesion(높은 응집력)**
- 하나의 모듈(메서드, 클래스 등)이 하나의 기능(역할)을 수행하게 하는 것을 의미한다.
- 목적이 분명하도록 작성한 메서드나 클래스는 신뢰도가 높고, 재사용과 코드를 이해하기 쉽다.
- 반대로 한 개의 메서드나 클래스가 여러 기능이나 역할을 수행한다면
  유지보수나 재사용이 어렵고 코드를 이해하기도 어렵다.

## 훈련 목표
- 소프트웨어 설계의 원칙 중 하나인 "High Cohesion"의 개념을 이해한다.
- 기존 코드에서 특정 역할을 하는 코드를 분리해 내는 것을 연습한다.
- DAO 의 역할과 DAO를 도입했을 때의 이점을 이해한다.
- DAO와 DBMS 테이블의 관계를 이해한다.

## 훈련 내용
- 커맨드 클래스에서 데이터 처리 코드를 분리하여 별도의 클래스(DAO)로 정의한다.

## 실습

### 1단계 - 게시글 데이터 처리 객체의 사용법을 인터페이스로 정의한다.

- com.eomcs.pms.dao.BoardDao 인터페이스를 정의한다.

### 2단계 - BoardDao 규칙에 따라 게시글을 컬렉션으로 관리하는 구현체를 만든다.

- com.eomcs.pms.dao.impl.ListBoardDao 클래스를 정의한다.

### 3단계 - BoardXxxHandler는 BoardDao 규칙에 따라 만든 객체를 사용한다.

- com.eomcs.pms.handler.BoardXxxHandler 클래스 변경
- com.eomcs.pms.ClientApp 클래스 변경
  - BoardDao 객체 생성 및 핸들러에 적용

### 4단계 - BoardDao 규칙에 따라 데이터 처리 서버와 통신하는 구현체를 만들어 핸들러에 적용한다.

- com.eomcs.pms.dao.impl.NetBoardDao 클래스를 정의한다.
- com.eomcs.pms.ClientApp 클래스 변경
  - BoardDao 객체 생성 및 핸들러에 적용

### 5단계 - 회원/프로젝트/작업 관리에 DAO 적용한다.

- com.eomcs.pms.dao.MemberDao 인터페이스 정의
- com.eomcs.pms.dao.ListProjectDao 클래스 정의
- com.eomcs.pms.dao.NetProjectDao 클래스 정의
- com.eomcs.pms.dao.ProjectDao 인터페이스 정의
- com.eomcs.pms.dao.NetProjectDao 클래스 정의
- com.eomcs.pms.handler.MemberXxx 클래스 변경
- com.eomcs.pms.handler.ProjectXxx 클래스 변경
- com.eomcs.pms.handler.TaskXxx 클래스 변경