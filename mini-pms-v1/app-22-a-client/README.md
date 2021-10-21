# 22-a. DB 프로그래밍을 더 쉽고 간단히 하는 방법 : Mybatis 퍼시스턴스 프레임워크 도입

이번 훈련에서는,
- 실무에서 자주 쓰이는 *퍼시스턴스 프레임워크* 중에 하나인 **마이바티스** 프레임워크의 사용법을 배울 것이다.

**퍼시스턴스 프레임워크(Persistence Framework)** 는,
- 데이터의 저장, 조회, 변경, 삭제를 다루는 클래스 및 설정 파일들의 집합이다.(위키백과)
- JDBC 프로그래밍의 번거로움 없이 간결하게 데이터베이스와 연동할 수 있다.
- 소스 코드에서 SQL 문을 분리하여 관리한다.

**마이바티스(Mybatis)** 는,
- *퍼시스턴스 프레임워크* 중의 하나이다.
- JDBC 프로그래밍을 캡슐화하여 데이터베이스 연동을 쉽게 하도록 도와준다.
- 자바 소스 파일에서 SQL을 분리하여 별도의 파일로 관리하기 때문에
  자바 소스 코드를 간결하게 유지할 수 있다.
- JDBC 프로그래밍 할 때와 마찬가지로 직접 SQL을 다루기 때문에
  레거시(legacy) 시스템에서 사용하는 데이터베이스와 연동할 때 유리하다.
- SQL을 통해 데이터베이스와 연동한다고 해서 보통 **SQL 매퍼(mapper)** 라 부른다.

## 훈련 목표
- **Mybatis SQL 맵퍼** 의 특징과 동작 원리를 이해한다.
- Mybatis 퍼시스턴스 프레임워크를 설정하고 다루는 방법을 배운다.

## 훈련 내용
- *Mybatis 프레임워크* 라이브러리 파일을 프로젝트에 추가한다.
- *Mybatis* 를 설정한다.
- *DAO* 에 *Mybatis* 를 적용한다.

## 실습

### 1단계 - 프로젝트에 MyBatis 라이브러리를 추가한다.

- build.gradle   
  - `search.maven.org` 사이트에서 *mybatis* 라이브러리 정보를 찾는다.
  - 의존 라이브러리 블록에서 `mybatis` 라이브러리를 등록한다.
- gradle을 이용하여 eclipse 설정 파일을 갱신한다.
  - `$ gradle eclipse`
- 이클립스에서 프로젝트를 갱신한다.

### 2단계 - `MyBatis` 설정 파일을 준비한다.

- src/main/resources/com/eomcs/pms/conf/jdbc.properties
  - 마이바티스 홈 : <http://www.mybatis.org>
  - `MyBatis` 설정 파일에서 참고할 DBMS 접속 정보를 등록한다.
- src/main/resources/com/eomcs/pms/conf/mybatis-config.xml
  - `MyBatis` 설정 파일이다.
  - DBMS 서버의 접속 정보를 갖고 있는 jdbc.properties 파일의 경로를 등록한다.
  - DBMS 서버 정보를 설정한다.
  - DB 커넥션 풀을 설정한다.


### 3단계: Mybatis를 적용한 MybatisMemberDao 만들어 기존 DAO를 대체한다.

- com/eomcs/pms/conf/mybatis-config.xml 변경
  - MemberMapper.xml 파일의 경로를 등록한다.
- com/eomcs/pms/mapper/MemberMapper.xml 추가
  - MariadbMemberDao 에 있던 SQL문을 이 파일로 옮긴다.
- com.eomcs.pms.dao.impl.MybatisMemberDao 클래스 생성.
  - 의존 객체 SqlSession을 생성자를 통해 주입 받는다.
  - SQL을 뜯어내어 MemberMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- com.eomcs.pms.ClientApp 클래스 변경
  - SqlSession 객체를 준비한다.
  - MybatisMemberDao 객체에 주입한다.

### 4단계: Mybatis를 적용한 MybatisBoardDao 만들어 기존 DAO를 대체한다.

- com/eomcs/pms/conf/mybatis-config.xml 변경
  - BoardMapper.xml 파일의 경로를 등록한다.
- com/eomcs/pms/mapper/BoardMapper.xml 추가
  - MariadbBoardDao 에 있던 SQL문을 이 파일로 옮긴다.
- com.eomcs.pms.dao.impl.MybatisBoardDao 클래스 생성.
  - 의존 객체 SqlSession을 생성자를 통해 주입 받는다.
  - SQL을 뜯어내어 BoardMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- com.eomcs.pms.ClientApp 클래스 변경
  - SqlSession 객체를 준비한다.
  - MybatisBoardDao 객체에 주입한다.

### 5단계: Mybatis를 적용한 MybatisProjectDao 만들어 기존 DAO를 대체한다.

- com/eomcs/pms/conf/mybatis-config.xml 변경
  - ProjectMapper.xml 파일의 경로를 등록한다.
- com/eomcs/pms/mapper/ProjectMapper.xml 추가
  - MariadbProjectDao 에 있던 SQL문을 이 파일로 옮긴다.
- com.eomcs.pms.dao.impl.MybatisProjectDao 클래스 생성.
  - 의존 객체 SqlSession을 생성자를 통해 주입 받는다.
  - SQL을 뜯어내어 ProjectMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- com.eomcs.pms.ClientApp 클래스 변경
  - SqlSession 객체를 준비한다.
  - MybatisProjectDao 객체에 주입한다.

### 6단계: Mybatis를 적용한 MybatisTaskDao 만들어 기존 DAO를 대체한다.

- com/eomcs/pms/conf/mybatis-config.xml 변경
  - TaskMapper.xml 파일의 경로를 등록한다.
- com/eomcs/pms/mapper/TaskMapper.xml 추가
  - MariadbTaskDao 에 있던 SQL문을 이 파일로 옮긴다.
- com.eomcs.pms.dao.impl.MybatisTaskDao 클래스 생성.
  - 의존 객체 SqlSession을 생성자를 통해 주입 받는다.
  - SQL을 뜯어내어 TaskMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- com.eomcs.pms.ClientApp 클래스 변경
  - SqlSession 객체를 준비한다.
  - MybatisTaskDao 객체에 주입한다.

