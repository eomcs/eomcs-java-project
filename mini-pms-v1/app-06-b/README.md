# 06-b. CRUD 구현하기 : 리팩토링

이번 훈련에서는 게시글, 회원, 프로젝트, 작업 정보 각각에 대해 CRUD를 완성해보자.

**CRUD** 는 데이터의 생성(Create), 조회(Read/Retrieve), 변경(Update), 삭제(Delete)을 가리키는 용어이다.

## 훈련 목표

- 관리 시스템에서 데이터 처리의 기본 기능인 CRUD를 연습한다.
- 이를 통해 배열을 다루는 방법과 조건문, 반복문, 메서드 등 자바 기본 문법을 다루는 방법을 연습한다.

## 훈련 내용

- 게시글의 상세 조회, 변경, 삭제 기능을 추가한다.
- 회원 정보의 상세 조회, 변경, 삭제 기능을 추가한다.
- 프로젝트 정보의 상세 조회, 변경, 삭제 기능을 추가한다.
- 작업 정보의 상세 조회, 변경, 삭제 기능을 추가한다.

## 실습

### 1단계 - BoardHandler 클래스에서 중복되는 코드를 메서드로 추출한다.

- 게시글 번호와 일치하는 Board 인스턴스를 찾는 코드를 메서드로 분리한다.
  - `findByNo(int)`: detail(), update() 메서드에 적용
  - `indexOf(int)`: delete() 메서드에 적용

#### 작업 파일

- com.eomcs.pms.handler.BoardHandler 클래스 변경

### 2단계 - MemberHandler 클래스에서 중복되는 코드를 메서드로 추출한다.

- 회원 번호와 일치하는 Member 인스턴스를 찾는 코드를 메서드로 분리한다.
  - `findByNo(int)`: detail(), update() 메서드에 적용
  - `indexOf(int)`: delete() 메서드에 적용

#### 작업 파일

- com.eomcs.pms.handler.MemberHandler 클래스 변경

### 3단계 - ProjectHandler 클래스에서 중복되는 코드를 메서드로 추출한다.

- 프로젝트 번호와 일치하는 Project 인스턴스를 찾는 코드를 메서드로 분리한다.
  - `findByNo(int)`: detail(), update() 메서드에 적용
  - `indexOf(int)`: delete() 메서드에 적용
- 프로젝트 관리자를 입력하는 코드를 메서드로 분리한다.
  - `promptOwner(MemberHandler,String)`: add(), upate() 메서드에 적용
- 프로젝트 팀원을 입력하는 코드를 메서드로 분리한다.
  - `promptMembers(MemberHandler,String)`: add(), upate() 메서드에 적용

#### 작업 파일

- com.eomcs.pms.handler.ProjectHandler 클래스 변경

### 4단계 - TaskHandler 클래스에서 중복되는 코드를 메서드로 추출한다.

- 작업 번호와 일치하는 Task 인스턴스를 찾는 코드를 메서드로 분리한다.
  - `findByNo(int)`: detail(), update() 메서드에 적용
  - `indexOf(int)`: delete() 메서드에 적용
- 작업 상태 번호의 라벨을 설정하는 코드를 메서드로 추출한다.
  - `getStatusLabel(int)` : detail(), list()에 적용
- 작업 담당자를 입력하는 코드를 메서드로 분리한다.
  - `promptOwner(MemberHandler,String)`: add(), upate() 메서드에 적용
- 작업 상태 정보를 입력 받는 코드를 메서드로 분리한다.
  - `promptStatus(int)` : add(), update() 메서드에 적용

#### 작업 파일

- com.eomcs.pms.handler.TaskHandler 클래스 변경

## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
