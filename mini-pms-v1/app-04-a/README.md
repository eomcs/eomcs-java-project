# 04-a. 클래스 사용법 : 메서드 분류

**클래스**는 여러 개의 메서드를 한 단위로 묶을 때 사용한다.
서로 관련된 일을 하는 메서드들을 한 곳에 묶어 놓으면
관리가 쉽기 때문이다.

이번 훈련에서는 클래스를 사용하여 회원 데이터를 다루는 메서드와
프로젝트 데이터를 다루는 메서드, 작업 데이터를 다루는 메서드를
각각의 클래스로 분리할 것이다.
메서드를 분류하는 용도로 클래스 문법을 사용해 보자.

## 훈련 목표

- 클래스를 이용하여 메서드를 한 단위로 묶는 방법을 배운다.
- 리팩토링 기법 중에서 '클래스 추출(Extract Class)'을 연습한다.

## 훈련 내용

- 프롬프트 관련 메서드를 별도의 클래스로 분리한다.
- 회원 관련 메서드를 별도의 클래스로 분리한다.
- 프로젝트 관련 메서드를 별도의 클래스로 분리한다.
- 작업 관련 메서드를 별도의 클래스로 분리한다.

## 실습

### 1단계 - 사용자의 입력을 받는 프롬프트 메서드를 별도의 클래스로 분리한다

- `App` 클래스에서 사용자의 입력을 받는 프롬프트 관련 필드와 메서드를 추출하여 `Prompt` 라는 별도의 클래스로 옮긴다.
- `App` 클래스는 사용자 입력이 필요할 때 `Prompt` 클래스로 옮겨진 메서드를 사용하여 처리한다.

#### 작업 파일

- com.eomcs.pms.Prompt 클래스 생성
    - 프롬프트 메서드의 이름을 적절하게 변경한다.
- com.eomcs.pms.App 변경
    - 백업: App.java.01

### 2단계 - 회원 데이터 처리와 관련된 메서드를 별도의 클래스로 분리한다

- `App` 클래스에서 회원 관리와 관련된 필드와 메서드를 추출하여 `MemberHandler` 라는 별도의 클래스로 옮긴다.
- `App` 클래스는 회원 입력과 목록 조회를 처리할 때 `MemberHandler` 클래스를 사용하여 처리한다.

#### 작업 파일

- com.eomcs.pms.MemberHandler 클래스 생성
  - 메서드 이름을 적절하게 변경한다. 예) addMember() --> add()
- com.eomcs.pms.App 변경
  - 백업: App.java.02

### 3단계 - 프로젝트 데이터 처리와 관련된 메서드를 별도의 클래스로 분리한다

- `App` 클래스에서 프로젝트 관리와 관련된 필드와 메서드를 추출하여 `ProjectHandler` 라는 별도의 클래스로 옮긴다.
- `App` 클래스는 프로젝트 입력과 목록 조회를 처리할 때 `ProjectHandler` 클래스를 사용하여 처리한다.

#### 작업 파일

- com.eomcs.pms.ProjectHandler 클래스 생성
  - 필드와 메서드를 적절한 이름으로 변경한다.
- com.eomcs.pms.App 변경
  - 백업: App.java.03

### 4단계 - 작업 데이터 처리와 관련된 메서드를 별도의 클래스로 분리한다

- `App` 클래스에서 작업 관리와 관련된 필드와 메서드를 추출하여 `TaskHandler` 라는 별도의 클래스로 옮긴다.
- `App` 클래스는 작업 입력과 목록 조회를 처리할 때 `TaskHandler` 클래스를 사용하여 처리한다.
-
#### 작업 파일

- com.eomcs.pms.TaskHandler 클래스 생성
  - 필드와 메서드를 적절한 이름으로 변경한다.
- com.eomcs.pms.App 변경
  - 백업: App.java.04

### 5단계 - 자원 해제는 그 자원을 소유한 클래스에게 맡긴다.

- `Prompt` 클래스에 `close()` 메서드를 추가한다.
  - 이 메서드에서 키보드 스캐너를 닫는다.
- `App` 클래스는 `Prompt`의 `close()`를 호출하여 해당 클래스가 사용한 자원을 해제시킨다.
-
#### 작업 파일

- com.eomcs.pms.Prompt 클래스 변경
  - close() 메서드 정의를 추가한다.
- com.eomcs.pms.App 변경

## 실습 결과

- src/main/java/com/eomcs/pms/App.java 변경
- src/main/java/com/eomcs/pms/Prompt.java 추가
- src/main/java/com/eomcs/pms/MemberHandler.java 추가
- src/main/java/com/eomcs/pms/ProjectHandler.java 추가
- src/main/java/com/eomcs/pms/TaskHandler.java 추가
