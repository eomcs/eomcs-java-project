# 19-a. 데이터 관리 서버 만들기 : 클라이언트/서버 프로젝트 준비

이번 훈련에서는,
- **네트워크 API** 를 이용하여 데스크톱 애플리케이션을 클라이언트/서버 구조로 변경한다.

데스크톱(desktop) 애플리케이션은,
- 다른 애플리케이션과 연동하지 않고 단독적으로 실행한다.
- 보통 PC나 노트북에 설치해서 사용한다.
- 예) MS-Word, Adobe Photoshop, 메모장 등

클라이언트(Client)/서버(Server) 애플리케이션은,
- 줄여서 C/S 애플리케이션이라 부른다.
- 클라이언트는 서버에게 서비스나 자원을 요청하는 일을 한다.
- 서버는 클라이언트에게 자원이나 서비스를 제공하는 일을 한다.


## 훈련 목표
- Gradle 빌드 도구를 이용하여 클라이언트 애플리케이션 프로젝트를 만드는 것을 연습한다.
- Gradle 빌드 도구를 이용하여 프로젝트를 *Eclipse IDE* 용으로 바꾸는 것을 연습한다.

## 훈련 내용
- 클라이언트 프로젝트를 수행할 작업 폴더를 만들고 Gradle 빌드 도구로 초기화시킨다.
- Gradle에서 제공하는 *Eclipse 플러그인* 을 사용하여 *Eclipse IDE* 용 설정 파일을 생성한다.
- 프로젝트를 *Eclipse IDE* 로 가져와서 프로그래밍 작업을 준비한다.

## 실습

### 1단계 - 클라이언트 프로젝트 폴더를 생성한다.

사용자 홈 폴더(예: /Users/eomjinyoung)에 클라이언트 프로젝트로 사용할 폴더를 생성한다.
- 예) 'mini-pms-client' 디렉토리를 생성한다.

```console
[~]$ mkdir mini-pms-client
[~]$ cd mini-pms-client
[~/mini-pms-client]$
```


### 2단계 - 클라이언트 프로젝트 폴더를 Maven 기본 자바 디렉토리 구조로 초기화한다.

- `[~/mini-pms-client]$ gradle init` 실행


### 3단계 - 이클립스 IDE로 임포트 한다.

- `build.gradle` 변경
  - `eclipse` gradle 플러그인을 추가한다.
  - `javaCompile` 을 설정한다.
- gradle을 실행하여 이클립스 설정 파일을 생성한다.
  - `[~/mini-pms-client]$ gradle eclipse` 실행
- 이클립스 프로젝트 이름을 `app` 에서 `mini-pms-client` 로 변경한다.
  - build.gradle 파일에 이클립스의 프로젝트 이름을 변경하는 코드를 추가한다.
```
eclipse {
    project {
        name = "mini-pms-client"
    }
}
```
- 이클립스에서 프로젝트 폴더를 임포트한다.


### 4단계 - 애플리케이션 메인 클래스를 변경한다.

- `App.java` 변경
  - 기존의 `App.java` 의 클래스 이름을 `ClientApp.java` 로 변경한다.
- `src/test/java/com/eomcs/pms/AppTest.java` 삭제
  - JUnit 관련해서 자동 생성된 클래스 파일은 일단 삭제한다.
- `ClientApp.java` 를 실행하여 결과를 확인한다.    


## 실습 결과
- src/main/java/com/eomcs/pms/ClientApp.java 이름 변경
