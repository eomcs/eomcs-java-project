# 22-b. DB 프로그래밍을 더 쉽고 간단히 하는 방법 : Mybatis에서 DAO 구현체를 자동으로 생성하기

이번 훈련에서는,
- **마이바티스** 프레임워크에서 DAO 구현체를 자동으로 생성하는 방법을 연습한다.


## 훈련 목표
- 

## 훈련 내용
- 

## 실습

### 방법1 - Mapper XML 파일을 기준으로 설정하기 

- src/main/resources/com/eomcs/pms/mapper/XxxMapper.xml 파일 변경
  - <mapper> 루트 태그의 namespace 속성의 값을 인터페이스 이름(FQName)과 일치시킨다.
- com.eomcs.pms.ClientApp 클래스 변경
  - DAO 객체를 만들 때 SqlSession.getMapper()를 호출한다.

### 방법2 - DAO 인터페이스를 기준으로 설정하기

- src/main/resources/com/eomcs/pms/conf/mybatis-config.xml 파일 변경
  - <mappers> 태그에 매퍼 XML 파일을 등록하는 대신에 DAO 인터페이스의 패키지를 등록한다.
- 기존의 매퍼 파일을 DAO 인터페이스의 패키지 폴더로 옮긴다.
  - 매퍼 파일의 이름을 인터페이스 이름과 일치시킨다.  

### 추가 작업1 - 트랜잭션을 다루는 일을 핸들러가 맡는다.

DAO를 직접 구현하는 기존 방식에서는 insert/update/delete 한 후 DAO에서 commit()을 호출했다. DAO 구현체를 자동으로 생성하는 지금의 방식에서는 commit()/rollback()의 호출을 핸들러가 담당해야 한다.

- com.eomcs.pms.handler.XxxHandler 클래스 변경
  - SqlSession 객체를 주입 받는다.
  - insert/update/delete 을 실행한 경우 commit() 또는 rollback()을 호출한다.

### 추가 작업2 - DAO의 메서드 한 개에서 여러 개의 작업을 실행할 경우 각 SQL 작업을 별도의 메서드로 분리한다.

- com.eomcs.pms.dao.ProjectDao 인터페이스 변경
  - insertMember(), deleteMember() 메서드 추가
- com.eomcs.pms.handler.ProjectAddHandler 클래스 변경
  - 프로젝트를 추가할 때 멤버를 추가하도록 insertMember() 를 호출한다.
  - try ~ catch ~를 사용하여 commit()/rollback()을 제어한다.
- com.eomcs.pms.handler.ProjectUpdateHandler 클래스 변경
  - 프로젝트를 변경할 때 멤버를 삭제, 추가하도록 deleteMember()와 insertMember() 를 호출한다.
  - try ~ catch ~를 사용하여 commit()/rollback()을 제어한다.
- com.eomcs.pms.handler.ProjectDeleteHandler 클래스 변경
  - 프로젝트를 삭제할 때 멤버를 먼저 삭제하도록 deleteMember() 를 호출한다.
  - try ~ catch ~를 사용하여 commit()/rollback()을 제어
