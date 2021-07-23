# 06-a. CRUD 구현하기

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

### 0단계 - 여러 개의 게시판을 다루기 위해 작성했던 코드를 제거한다.

- 스태틱 필드와 인스턴스 필드와 차이점을 알아보기 위해
  여러 개의 게시판을 작성했다.
- 이제 확인했으니, 제거한다.

#### 작업 파일

- com.eomcs.pms.App 클래스 변경

### 1단계 - 게시글의 상세 조회 기능을 추가한다.

- `BoardHandler`에 상세 조회 기능을 수행하는 `detail()` 메서드를 추가한다.
- 호출될 때 마다 조회수 필드의 값을 1 증가시킨다.
- `App` 클래스에 `/board/detail` 명령을 추가한다.


```
명령> /board/add
[새 게시글]
번호? 1
제목? 제목1
내용? 내용입니다.
작성자? 홍길동
게시글을 등록하였습니다.

명령> /board/list
[게시글 목록]
1, 제목1, 홍길동, 2020-01-10, 0
2, 제목2, 임꺽정, 2020-01-20, 12
3, 제목3, 유관순, 2020-01-30, 7

명령> /board/detail
[게시글 상세보기]
번호? 1   <--- 사용자가 번호 입력
제목: 제목1
내용: 내용입니다.
작성자: 홍길동
등록일: 2020-01-10
조회수: 1

명령> /board/detail
[게시글 상세보기]
번호? 100
해당 번호의 게시글이 없습니다.
```

#### 작업 파일

- com.eomcs.pms.handler.BoardHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경


### 2단계 - 게시글의 변경 기능을 추가한다.

- `BoardHandler`에 변경 기능을 수행하는 `update()` 메서드를 추가한다.
- `App` 클래스에 `/board/update` 명령을 추가한다.


```
명령> /board/update
[게시글 변경]
번호? 1    <--- 사용자가 번호를 입력
제목(제목1)? 제목변경
내용(내용입니다.)? 내용변경
정말 변경하시겠습니까?(y/N) y
게시글을 변경하였습니다.

명령> /board/update
[게시글 변경]
번호? 1
제목(제목1)? 제목변경
내용(내용입니다.)? 내용변경
정말 변경하시겠습니까?(y/N) n
게시글 변경을 취소하였습니다.

명령> /board/detail
[게시글 상세보기]
번호? 1
제목: 제목변경
내용: 내용변경
작성자: 홍길순
등록일: 2020-01-10
조회수: 2

명령> /board/update
[게시글 변경]
번호? 100
해당 번호의 게시글이 없습니다.
```

#### 작업 파일

- com.eomcs.pms.handler.BoardHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경


### 3단계 - 게시글의 삭제 기능을 추가한다.

- `BoardHandler`에 변경 기능을 수행하는 `delete()` 메서드를 추가한다.
- `App` 클래스에 `/board/delete` 명령을 추가한다.


```
명령> /board/delete
[게시글 삭제]
번호? 1  <--- 사용자가 번호 입력
정말 삭제하시겠습니까?(y/N) y
게시글을 삭제하였습니다.

명령> /board/delete
[게시글 삭제]
번호? 1
정말 삭제하시겠습니까?(y/N) n
게시글 삭제를 취소하였습니다.

명령> /board/delete
[게시글 삭제]
번호? 100
해당 번호의 게시글이 없습니다.
```

#### 작업 파일

- com.eomcs.pms.handler.BoardHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경

### 4단계 - 게시글 CRUD를 참고하여 회원, 프로젝트, 작업에 대해서도 CRUD를 완성한다.

#### 작업 파일

- com.eomcs.pms.handler.MemberHandler 클래스 변경
- com.eomcs.pms.handler.ProjectHandler 클래스 변경
- com.eomcs.pms.handler.TaskHandler 클래스 변경
- com.eomcs.pms.App 클래스 변경


## 실습 결과

- src/main/java/com/eomcs/pms/handler/BoardHandler.java 변경
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 변경
- src/main/java/com/eomcs/pms/App.java 변경
