# 01.3 `이클립스 IDE`로 프로젝트 가져오기

## 학습 목표

- `그레이들`을 이용하여 `이클립스 IDE` 프로젝트로 전환할 수 있다.
- `이클립스 IDE` 워크스페이스로 프로젝트를 가져올 수 있다.
- `이클립스 IDE`에서 프로젝트를 빌드하고 실행할 수 있다.

## 실습

### 1단계 - 그레이들 빌드 스크립트에 이클립스 플러그인 추가한다

Gradle에서 이클립스 관련 명령을 다룰 수 있도록 플러그인을 추가한다.
다음과 같이 build.gradle 파일에 'eclispe' 플러그인을 추가한다.

- ~/mylist/app/build.gradle 변경

```groovy
plugins {
    id 'application'
    id 'eclipse'   <== 이 코드를 추가한다.
}
```

### 2단계 - 이클립스 IDE 용 설정 파일 생성한다

**그래이들** 빌드 도구를 이용하여 이클립스 IDE에서 사용할 설정 파일을 생성한다.
다음과 같이 터미널 창에서 명령을 실행한다.

```console
~/mylist$ gradle eclipse
```

생성된 파일 및 디렉토리를 확인해 보자.
```console
~/mylist$ tree -a   <== -a 옵션 추가하면 숨김 파일이나 폴더까지 보여준다.
.
├── .gitattributes
├── .gitignore
├── .gradle
│   ...
├── README.md
├── app
│   ├── .classpath      <=== 생성된 eclipse 설정 파일
│   ├── .project        <=== 생성된 eclipse 설정 파일
│   ├── .settings       <=== 생성된 eclipse 설정 디렉토리
│   │   └── org.eclipse.jdt.core.prefs
│   ├── README.md
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── eomcs
│       │   │           └── mylist
│       │   │               └── App.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── com
│           │       └── eomcs
│           │           └── mylist
│           │               └── AppTest.java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle

```

### 3단계 - 이클립스 IDE로 프로젝트를 불러온다

다음 절차에 따라 이클립스 IDE의 워크스페이스로 프로젝트를 가져온다.

- Eclise IDE 메뉴 > `File > Import...` 클릭
- Import 대화창 > `General 노드 > Existing Projects into Workspace 선택` > Next 클릭
- `~/mylist/app` 프로젝트 폴더 선택 > Finish 클릭

### 4단계 - 프로젝트 이름을 변경한다.

이클립스 IDE의 `Project Explorer` 창을 보면 프로젝트 이름이 `app`으로 출력된다.
프로젝트 설정 파일(.project)에 프로젝트 이름이 `app`으로 설정되어 있기 때문이다.
`gradle eclipse`를 실행하여 .project 파일을 생성할 때,
메인 프로젝트의 디렉토리(예: `app`) 이름이 프로젝트 이름으로 설정된다.
프로젝트 이름을 바꾸고 싶다면 다음과 같이 빌드 스클립트 파일에 eclipse 설정을 추가한다.

- ~/mylist/app/build.gradle 변경

```groovy
// eclipse 프로젝트 이름을 설정하기
eclipse {
    project {
        name = "mylist"
    }
}
```

- `gradle eclipse` 명령을 다시 실행하여 설정 파일을 갱신한다.
- 이클립스 IDE에서 기존에 가져온 프로젝트를 삭제하고 프로젝트를 다시 가져온다.


### 4단계 - 이클립스 IDE에서 프로젝트를 실행한다

다음 절차에 따라 이클립스 IDE에서 애플리케이션을 실행해 보자.

- `app/src/main/java/com/eomcs/mylist/App.java` 소스 파일을 열기
- Eclipse IDE 메뉴 > `Run > Run` 클릭
- `Console` 뷰에 출력된 결과 확인
