# 01.3 `이클립스 IDE`로 프로젝트 가져오기

## 훈련 목표

- `그레이들`을 이용하여 `이클립스 IDE` 프로젝트로 전환할 수 있다.
- `이클립스` 워크스페이스로 프로젝트를 가져올 수 있다.
- `이클립스`에서 프로젝트를 빌드하고 실행할 수 있다.

## 실습

### 1단계 - 그레이들 빌드 스크립트에 이클립스 플러그인 추가한다

Gradle에서 이클립스 관련 명령을 다룰 수 있도록 플러그인을 추가한다.
다음과 같이 build.gradle 파일에 'eclispe' 플러그인을 추가한다.

```groovy
plugins {
    id 'java'
    id 'application'
    id 'eclipse'   <== 이 코드를 추가한다.
}
```

프로젝트 구조가 다음과 같을 경우에는 .project 파일을 생성할 때 프로젝트 이름이 디렉토리 명인 `app`으로 설정된다.
```
프로젝트/
  settings.gradle
  app/
    src/
    build.gradle
```

프로젝트 이름을 바꾸고 싶다면 다음과 같이 eclipse 설정을 추가한다.
```groovy
// eclipse 프로젝트 이름을 설정하기
eclipse {
    project {
        name = "study-project"
    }
}
```

#### 작업 파일

- build.gradle (변경)

### 2단계 - 이클립스 IDE 용 설정 파일 생성한다

**그래이들** 빌드 도구를 이용하여 이클립스 IDE에서 사용할 설정 파일을 생성한다.
다음과 같이 터미널 창에서 명령을 실행한다.

```console
[~/mini-pms]$ gradle eclipse
```

### 3단계 - 이클립스 IDE로 프로젝트를 불러온다

다음 절차에 따라 이클립스 IDE의 워크스페이스로 프로젝트를 가져온다.

- Eclise IDE 메뉴 > `File > Import...` 클릭
- Import 대화창 > `General 노드 > Existing Projects into Workspace 선택` > Next 클릭
- `java-project` 프로젝트 폴더 선택 > Finish 클릭

### 4단계 - 이클립스 IDE에서 프로젝트를 실행한다

터미널 창에서 자바 애플리케이션을 실행하는 것 보다 더 편리하게 이클립스 IDE에서 실행할 수 있다. 다음 절차에 따라 실행한다.

- `src/main/java/com/eomcs/pms/App.java` 소스 파일을 열기
- Eclipse IDE 메뉴 > `Run > Run` 클릭
- `Console 뷰`에 출력된 결과 확인
