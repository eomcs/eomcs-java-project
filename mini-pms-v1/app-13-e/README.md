# 13-e. 사용자 인증 : 로그인 정보 활용

이번 훈련에서는 **로그인(login)/로그아웃(logout)** 을 구현해 보자.
 
## 훈련 목표

- 


## 훈련 내용

- 

## 실습


### 1단계 - 게시글을 관리할 때 로그인 정보를 사용한다.

- com.eomcs.pms.handler.Board 클래스 변경
  - writer 필드 변경: 회원 정보를 저장할 수 있도록 타입을 Member로 변경한다. 
- com.eomcs.pms.handler.BoardHandler 클래스 변경
  - add() 메서드 변경 : 작성자에 로그인 회원 정보를 저장한다.
  - list() 메서드 변경 : 작성자 이름 출력
  - detail() 메서드 변경 : 작성자 이름 출력
  - update() 메서드 변경 : 작성자 여부 검사
  - delete() 메서드 변경 : 작성자 여부 검사

### 2단계 - 프로젝트를 관리할 때 로그인 정보를 사용한다.

- com.eomcs.pms.handler.Project 클래스 변경
  - owner 필드 변경: 회원 정보를 저장할 수 있도록 타입을 Member로 변경한다. 
  - members 필드 변경: 여러 명의 회원 정보를 저장할 수 있도록 타입을 List<Member>로 변경한다. 
- com.eomcs.pms.handler.MemberHandler 클래스 변경
  - promptMembers() 메서드 변경 :  메서드의 리턴 타입을 List<Member> 로 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
  - add() 메서드 변경 : 작성자에 로그인 회원 정보를 저장한다.
  - list() 메서드 변경 : 작성자 이름 출력
  - detail() 메서드 변경 : 작성자 이름 출력
  - update() 메서드 변경 : 작성자 여부 검사
  - delete() 메서드 변경 : 작성자 여부 검사


### 3단계 - 작업을 관리할 때 로그인 정보를 사용한다.

- com.eomcs.pms.handler.Project 클래스 변경
  - tasks 필드 추가: 여러 개의 작업 정보를 저장할 수 있도록 List<Task> 필드를 추가한다.
- com.eomcs.pms.handler.Task 클래스 변경   
  - project 필드 추가: 작업이 소속된 프로젝트 정보를 저장할 수 있도록 Project 필드를 추가한다.
  - owner 필드 변경 : 회원 정보를 담을 수 있도록 Member 타입으로 변경한다.
- com.eomcs.pms.handler.MemberHandler 클래스 변경
  - promptMember() 변경 : 리턴 타입을 Member 로 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
  - promptProject() 추가 : 프로젝트 목록에서 프로젝트를 선택하는 기능을 수행한다.
- com.eomcs.pms.handler.TaskHandler 클래스 변경
  - taskList, memberHandler 필드 제거
  - add() 메서드 변경 : 작성자에 로그인 회원 정보를 저장한다.
  - list() 메서드 변경 : 작성자 이름 출력
  - detail() 메서드 변경 : 작성자 이름 출력
  - update() 메서드 변경 : 작성자 여부 검사
  - delete() 메서드 변경 : 작성자 여부 검사
  - findByNo() 메서드 변경 : 프로젝트 목록
  - 인스턴스 멤버를 사용하지 않는 메서드를 스태틱 메서드로 전환.
- com.eomcs.pms.App 클래스 변경
  - List<Task> 변수 삭제
  - TaskHandler의 인스턴스 생성 부분 변경

## 실습 결과

- src/main/java/com/eomcs/pms/App.java 변경
- src/main/java/com/eomcs/pms/domain/Board.java 변경
- src/main/java/com/eomcs/pms/domain/Project.java 변경
- src/main/java/com/eomcs/pms/domain/Member.java 변경
- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경