# 04-c. 클래스 사용법 : 패키지로 클래스 분류

**패키지(package)** 는 여러 개의 클래스를 관리하기 좋게 분류할 때 사용하는 문법이다.
패키지는 실제 폴더를 가리키며, 각 클래스 파일을 *역할에 따라 패키지에 나누어 배치* 한다.

## 훈련 목표

- 패키지를 이용하여 역할에 따라 클래스를 분류하는 방법을 배운다.
- 패키지와 폴더의 관계를 이해한다.
- 멤버의 접근을 제어하는 default와 public의 사용법을 이해한다.

## 훈련 내용

- Prompt 클래스를 다른 패키지로 분류한다.
- Handler 클래스들을 다른 패키지로 분류한다.
- 사용자 정의 데이터 타입에 해당하는 클래스를 다른 패키지로 분류한다.
- 메서드의 접근 범위를 public 으로 확장한다.

## 실습

### 1단계 - `Prompt` 클래스를 별도의 패키지로 분류한다

- 여러 프로젝트에서 공통으로 사용할 클래스라면 별도의 패키지로 분류하는 것이 클래스 관리에 좋다.
- 프롬프트를 다루는 클래스는 여러 프로젝트에서 사용할 것이기 때문에 별도의 패키지로 분류한다.

#### 작업 파일

- 유틸리티 패키지 생성
  - `com.eomcs.util` 패키지 생성
- com.eomcs.util.Prompt 클래스 변경
  - Prompt 클래스를 `util` 패키지로 이동한다.
  - 다른 패키지의 클래스가 메서드를 사용할 수 있도록 메서드의 사용 범위를 public 으로 확장한다.
- com.eomcs.pms.App 클래스 및 XxxHandler 클래스 변경
  - Prompt 클래스의 소속을 밝히는 import 문을 추가한다.

### 2단계 - 사용자 정의 데이터 타입에 해당하는 클래스를 별도의 패키지로 분류한다.

### 작업 파일

- `com.eomcs.pms.domain` 패키지 생성
  - 이 패키지로 Member, Project, Task 클래스를 옮긴다.
  - 클래스에 정의되어 있는 변수를 다른 패키지에서 접근할 수 있도록 public 으로 공개한다.

### 3단계 - 핸들러 클래스들을 별도의 패키지로 분류한다

#### 작업 파일

- `com.eomcs.pms.handler` 패키지 생성
  - 이 패키지로 MemberHandler, ProjectHandler, TaskHandler 클래스를 옮긴다.
  - 다른 패키지에서 메서드를 호출할 수 있도록 사용 범위를 public 으로 확장한다.
- com.eomcs.pms.App 클래스 변경
    - 핸들러 클래스에 대해 import 문 변경

## 실습 결과

- com.eomcs.util 패키지 추가
- src/main/java/com/eomcs/util/Prompt.java 패키지 변경
- com.eomcs.pms.handler 패키지 추가
- src/main/java/com/eomcs/pms/handler/MemberHandler.java 패키지 변경
- src/main/java/com/eomcs/pms/handler/ProjectHandler.java 패키지 변경
- src/main/java/com/eomcs/pms/handler/TaskHandler.java 패키지 변경
- src/main/java/com/eomcs/pms/App.java 변경
