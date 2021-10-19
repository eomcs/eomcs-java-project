# 21-a. 데이터 관리를 DBMS에게 맡기기 : JDBC API 사용

이번 훈련에서는,
- **DBMS** 와 **JDBC API** 를 이용하여 데이터를 저장하고 조회하는 것을 배운다.

**데이터베이스(Database)** 는,
- 상호 연관이 있는 데이터의 모임을 가리킨다.
- 여러 사람이 공유하고 사용할 목적으로 통합 관리되는 정보의 집합이다.(wikipedia.org)
- 논리적으로 연관된 데이터를 검색과 갱신이 쉽도록 고도로 구조화한 것이다.
- 목적(wikipedia.og)
  - 데이터 중복 최소화
  - 데이터 공유
  - 일관성, 무결성, 보안성 유지
  - 데이터 접근 용이성
  - 데이터 저장 공간 절약

**데이터베이스 관리 시스템(Database Management System: DBMS)** 은,
- 데이터베이스의 목적을 달성할 수 있도록 편리하고 효율적인 환경을 제공하는 소프트웨어다.

**JDBC(Java Database Connectivity) API** 는,
- 자바 애플리케이션이 DBMS에 연동할 때 사용할 클래스와 호출 규칙을 정의한 라이브러리다
- DBMS 제작자는 *JDBC API* 의 구현체를 제공한다.

**JDBC 드라이버** 는,
- *JDBC API* 규약에 따라 만든 클래스 라이브러리다.
- 각각의 DBMS 제작자가 배포한다.

**JDBC 드라이버** 의 종류,
- Type 1
  - *JDBC-ODBC 브릿지 드라이버(bridge driver)* 라 부른다.
  - *ODBC API* 를 사용하여 DBMS와 연동한다.
  - *ODBC API* 를 제공하는 엑셀이나 액세스 등의 파일 DB에 접근할 때 용이하다.
  - 따라서 이 드라이버를 사용하는 컴퓨터에 ODBC 드라이버가 설치되어 있어야 한다.
  - 여러 계층을 경유하기 때문에 실행 속도가 느리다.
  - **JRE(Java Runtime Environment)** 에 기본으로 포함되어 있다.
- Type 2
  - *네이티브 드라이버(native driver)* 라 부른다.
  - DBMS 제작자가 제공하는 *데이터베이스 API(C/C++) = Vendor API = Native API* 를 호출하여 DBMS와 연동한다.
  - 따라서 이 드라이버를 사용하는 컴퓨터에 *데이터베이스 API* 가 설치되어 있어야 한다.
  - DBMS 제작자가 제공한다.
- Type 3
  - *네트워크 프로토콜 드라이버(network protocol driver)* 라 부른다.
  - *미들웨어* 를 경유하여 DBMS와 연동한다.
  - 따라서 특정 DBMS에 종속되지 않으며, 하나의 드라이버로 여러 DBMS에 접근할 수 있다.
  - C/C++ 함수를 호출하지 않고 *미들웨어 전용 프로토콜* 로 직접 통신하기 때문에 100% 순수 자바 코드로 구현되었다.
  - *미들웨어* 구입 비용이 추가로 든다.
  - 보통 DBMS 제작자가 아닌 제3의 업체(third-party)에서 제공한다.
- Type 4
  - *씬 드라이버(thin driver)* 라 부른다.
  - DBMS와 연동할 때 *DBMS 전용 프로토콜* 로 직접 통신한다.
  - DBMS와 직접 통신하기 때문에 실행 속도가 빠르다.
  - 내부적으로 C/C++ 함수를 호출하지 않기 때문에 100% 순수 자바 코드로 구현되었다.
  - 단, 특정 DBMS에 종속된다.
  - DBMS 제작자가 제공한다.

## 훈련 목표
- **JDBC API** 를 사용하여 **DBMS** 를 사용하는 방법을 배운다.
- **JDBC API** 와 **JDBC 드라이버** 의 관계를 이해한다.
- *JVM* 이 java.sql.Driver 구현체를 로딩하고 구동시키는 원리를 이해한다.
- 프로젝트에 **JDBC 드라이버** 를 추가하는 방법을 배운다.

## 훈련 내용
- 데이터를 파일에서 읽고 파일로 쓰는 기존 코드를 JDBC API를 사용하는 코드를 변경한다.


## 실습

### 1단계 - 프로젝트에 JDBC 드라이버를 설정한다.

- build.gradle 변경
  - mvnrepository.com 또는 search.maven.org에서 mariadb jdbc driver를 검색한다.
  - 라이브러리 정보를 build.gradle 파일에 설정한다.
  - gradle을 이용하여 eclipse 설정 파일을 갱신한다.
    - `$ gradle eclipse`
    - 다운로드 받지 않은 라이브러리가 있다면 자동으로 서버에서 받을 것이다.
    - 라이브러리 정보가 변경되었다면 해당 라이브러리를 서버에서 받을 것이다.
  - 이클립스 프로젝트를 리프래시 한다.
    - 프로젝트에 mariadb jdbc driver 라이브러리가 추가되었는지 확인한다.

### 2단계 - DBMS에 데이터를 저장할 테이블을 생성한다.

`docs/pms-dbmodel/ddl.sql` 파일의 SQL 실행

### 3단계 - DBMS를 이용하여 회원 데이터를 저장하고 로딩한다.

- com.eomcs.pms.dao.MemberDao 인터페이스 변경
  - 이메일과 암호로 사용자를 찾는 메서드 추가 : findByEmailAndPassword()
- com.eomcs.pms.dao.impl.MariadbMemberDao 클래스를 생성한다.
  - JDBC API를 사용하여 데이터를 처리한다.
- com.eomcs.pms.handler.MemberAddHandler 클래스를 변경한다.
  - 번호를 입력 받지 않는다. 
  - 등록일을 DBMS 서버에 보내지 않는다.
- com.eomcs.pms.handler.AuthLoginHandler 클래스를 변경한다.
  - RequestAgent 대신 MemberDao를 사용한다.
- com.eomcs.pms.ClientApp 클래스를 변경한다.
  - NetMemberDao 대신 MariadbMemberDao를 사용한다.
  - Connection 객체를 준비한다.

### 4단계 - DBMS를 이용하여 프로젝트 데이터를 저장하고 로딩한다.

- com.eomcs.pms.dao.impl.MariadbProjectDao 클래스를 생성한다.
  - JDBC API를 사용하여 데이터를 처리한다.
- com.eomcs.pms.handler.ProjectAddHandler 클래스를 변경한다.
  - 번호를 입력 받지 않는다. 
- com.eomcs.pms.ClientApp 클래스를 변경한다.
  - NetProjectDao 대신 MariadbProjectDao를 사용한다.

### 5단계 - DBMS를 이용하여 게시글 데이터를 저장하고 로딩한다.

- com.eomcs.pms.dao.impl.MariadbBoardDao 클래스를 생성한다.
  - JDBC API를 사용하여 데이터를 처리한다.
- com.eomcs.pms.handler.BoardAddHandler 변경
  - 번호를 입력 받지 않는다. 
  - 등록일을 DBMS 서버에 보내지 않는다.
- com.eomcs.pms.ClientApp 클래스를 변경한다.
  - NetBoardDao 대신 MariadbBoardDao를 사용한다.

### 6단계 - DBMS를 이용하여 작업 데이터를 저장하고 로딩한다.

- com.eomcs.pms.domain.Task 클래스를 변경한다.
  - Project 클래스와 상호 참조하는 관계를 끊는다.
  - Project 객체를 저장하는 대신에 projectNo 필드를 사용한다.
- com.eomcs.pms.dao.TaskDao 인터페이스 생성
  - ProjectDao에서 작업 관련 호출 규칙을 분리하여 별도의 인터페이스로 정의한다.
- com.eomcs.pms.dao.impl.MariadbTaskDao 클래스를 생성한다.
  - JDBC API를 사용하여 데이터를 처리한다.
- com.eomcs.pms.dao.ProjectDao 인터페이스 변경
  - 작업 관련 메서드를 제거한다.
- com.eomcs.pms.dao.impl.MariadbProjectDao 클래스를 변경한다.
  - TaskDao로 분리한 메서드를 제거한다.
- com.eomcs.pms.handler.TaskHandlerHelper 클래스 변경  
  - TaskDao를 사용하도록 변경한다.
- com.eomcs.pms.handler.TaskXxxHandler 변경
  - TaskDao를 사용하여 데이터를 처리한다.
- com.eomcs.pms.ClientApp 클래스를 변경한다.
  - NetTaskDao 대신 MariadbTaskDao를 사용한다.