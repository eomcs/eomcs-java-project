# 01-a. 프로젝트 준비 : `Gradle` 빌드 도구를 이용하여 자바 프로젝트 폴더 구성하기

프로그래밍의 시작은 **프로젝트 폴더** 를 만드는 것으로 시작한다.

`프로그래밍`은 **컴퓨터가 해야 할 일을 명령어로 작성하는 과정** 을 가르키는 말이다.
프로그래밍 중에 생성되는 파일을 두는 폴더가 프로젝트 폴더다.
프로젝트 폴더에는 명령어를 작성한 자바 파일(.java) 뿐만 아니라
명령어 실행에 필요한 설정 정보를 담은 파일(.xml, .properties)이나
그림 파일(.gif, .jpeg)도 둔다.

그런데 이들 파일을 한 폴더에 두면 관리하기가 불편하기 때문에
보통 하위 폴더를 만들어 구분한다.
프로젝트의 하위 폴더를 만들 때는
국가나 회사, 개발자에 상관없이 일관된 방식으로 다루기 위해
**메이븐(Maven) 빌드 도구의 표준 디렉토리 구조** 를 사용한다.

```
메이븐 프로젝트 폴더
└── src
    ├── main  <== 애플리케이션 관련 파일을 두는 폴더
    │   ├── java       <== 예) 패키지 폴더 및 자바 소스 파일(.java)
    │   └── resources  <== 예) 패키지 폴더 및 설정 파일(.xml, .properties 등)
    │   └── webapp     <== 예) 웹 파일을 두는 폴더(HTML, CSS, JavaScript, JPEG, JSP 등)
    └── test  <== 단위 테스트 관련 파일을 두는 폴더
        ├── java       <== 패키지 폴더 및 단위 테스트 자바 소스 파일
        └── resources  <== 단위 테스트 관련 설정 파일(.xml, .properties 등)
```

프로젝트 폴더를 매번 위와 같이 직접 구성하려면 번거롭고 불편하다.
**Maven(메이븐)** 또는 **Gradle(그레이들)** 이라는 빌드 도구를 사용하면
프로젝트 폴더를 쉽게 구성할 수 있다.
**그레이들** 과 같은 프로그램은
프로젝트 폴더 구성에서 컴파일, 테스트, 배포 파일 생성 등
애플리케이션 생성에 필요한 전반적인 작업을 도와준다.
보통 이런 도구를 **프로젝트 빌드 도구** 라 부른다.

**그레이들(Gradle) 빌드 도구** 전에는 **메이븐(Maven) 빌드 도구** 가 있었고
실무에서는 아직까지 메이븐 빌드 도구를 더 많이 사용하고 있다.
차츰 *그레이들* 로 이동하는 추세다.
특히 *안드로이드 앱 개발* 쪽에서는 기본 빌드 도구로 *그레이들* 을 사용한다.

이번 훈련에서는 이 **그레이들** 빌드 도구를 사용하여 프로젝트 폴더를 구성할 것이다.
그렇다고 그레이들 사용법을 자세하게 설명하지는 않는다.
사용할 때 마다 필요한 만큼만 배울 것이다.  

## 훈련 목표 및 내용

- `그레이들` 빌드 도구를 이용하여 자바 프로젝트의 디렉토리를 구성할 수 있다.
- 자바 프로젝트의 디렉토리 구조와 용도를 이해한다.
- `그레이들`로 프로젝트를 빌드하고 실행할 수 있다.

## 훈련 내용

- `그레이들` 빌드 도구로 자바 애플리케이션 프로젝트를 생성하기
- 자바 애플리케이션 프로젝트의 폴더 구조를 확인하기
- 프로젝트를 빌드하고 실행하기

## 실습

### 1단계 - 프로젝트로 사용할 디렉토리 준비한다

사용자 홈 폴더(예: /Users/eomjinyoung)에 자바 프로젝트로 사용할 폴더를 생성한다.

```console
[~]$ mkdir mylist
[~]$ cd mylist
```

### 2단계 - 자바 애플리케이션 프로젝트 폴더로 구성한다.

Gradle을 사용하여 자바 애플리케이션 프로젝트에 필요한 설정 파일과 폴더를 준비한다.

```console
[~/mylist]$ gradle init
Starting a Gradle Daemon (subsequent builds will be faster)

Select type of project to generate:
  1: basic
  2: application  <== 프로젝트 유형으로 '일반 애플리케이션 개발'을 선택한다.
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java  <== 프로그래밍에 사용할 언어로 '자바'를 선택한다.
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Split functionality across multiple subprojects?:
  1: no - only one application project  <== 하위 프로젝트를 만들지 않을 때 선택한다.
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 1

Select build script DSL:
  1: Groovy  <== 빌드 스크립트 파일을 작성할 때 사용할 언어로 'Groovy'를 선택한다.
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Select test framework:
  1: JUnit 4  <== 단위 테스트로 사용할 프레임워크로 'JUnit'을 선택한다.
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit 4) [1..4] 1

Project name (default: mylist): <== 프로젝트 이름을 설정한다. 현재 디렉토리명을 프로젝트 이름으로 사용하고 싶다면 그냥 엔터를 친다.
Source package (default: mylist): com.eomcs.mylist  <== 프로젝트의 기본 자바 패키지를 설정한다.
```

### 3단계 - 프로젝트의 디렉토리 구조 확인한다

Gradle로 생성한 프로젝트 설정 파일과 폴더를 확인한다.

```console
[~/mylist]$ tree   <== tree 명령을 실행하면 현재 폴더의 구조를 보여준다.
.
├── README.md
├── app
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

16 directories, 10 files
```

### 4단계 - 프로젝트를 실행한다

Gradle로 생성한 에제 소스 파일을 컴파일 하고 실행한다.

```console
[~/mylist]$ gradle run

> Task :app:run
Hello World!     <== 실행 결과

BUILD SUCCESSFUL in 4s
2 actionable tasks: 2 executed
```

- `$ gradle run`
  - JDK의 자바 컴파일러(javac)를 사용하여 app/src/main/java 폴더에 있는 모든 자바 소스 파일을 컴파일 한다.
  - JDK의 JVM(java)을 사용하여 settings.gradle 파일에 등록된 자바 메인 클래스를 찾아 실행한다.

### 5단계 - 사용자에게 배포할 파일을 만든다.

Gradle을 사용하여 프로젝트를 빌드하여 사용자에게 배포할 파일을 준비한다.

```console
[~/mylist]$ gradle build
```

#### build 절차

- 소스 파일을 컴파일한다.
- 단위 테스트를 수행한다.
- .jar 아카이브 파일을 만든다.
- 실행과 관련된 파일을 생성한다.
- .tar, .zip 배포 파일을 생성한다.

프로젝트 빌드를 통해 생성된 파일과 폴더는 build 디렉토리 아래에 놓인다.

```console
[~/mylist]$ tree  <== 윈도우에서는 파일 탐색기로 확인하면 된다.
.
├── README.md
├── app
│   ├── README.md
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── com
│   │   │       │       └── eomcs
│   │   │       │           └── mylist
│   │   │       │               └── App.class
│   │   │       └── test
│   │   │           └── com
│   │   │               └── eomcs
│   │   │                   └── mylist
│   │   │                       └── AppTest.class
│   │   ├── distributions
│   │   │   ├── app.tar
│   │   │   └── app.zip
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       ├── main
│   │   │       │       └── test
│   │   │       └── headers
│   │   │           └── java
│   │   │               ├── main
│   │   │               └── test
│   │   ├── libs
│   │   │   └── app.jar
│   │   ├── reports
│   │   │   └── tests
│   │   │       └── test
│   │   │           ├── classes
│   │   │           │   └── com.eomcs.mylist.AppTest.html
│   │   │           ├── css
│   │   │           │   ├── base-style.css
│   │   │           │   └── style.css
│   │   │           ├── index.html
│   │   │           ├── js
│   │   │           │   └── report.js
│   │   │           └── packages
│   │   │               └── com.eomcs.mylist.html
│   │   ├── scripts
│   │   │   ├── app
│   │   │   └── app.bat
│   │   ├── test-results
│   │   │   └── test
│   │   │       ├── TEST-com.eomcs.mylist.AppTest.xml
│   │   │       └── binary
│   │   │           ├── output.bin
│   │   │           ├── output.bin.idx
│   │   │           └── results.bin
│   │   └── tmp
│   │       ├── compileJava
│   │       │   └── previous-compilation-data.bin
│   │       ├── compileTestJava
│   │       │   └── previous-compilation-data.bin
│   │       ├── jar
│   │       │   └── MANIFEST.MF
│   │       └── test
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

55 directories, 30 files
```

### 6단계 - 파일을 배포하고 실행한다

배포 파일을 받은 일반 사용자는 보통 다음의 절차에 따라 애플리케이션을 실행한다.

적당한 폴더에 배포 파일(.tar 또는 .zip)의 압축을 푼다.
배포 파일을 `Downloads` 폴더에 다운로드 받았다고 가정하자.  

```console
[~/Downloads]$ tar -xvf app.tar  <== 압축 파일을 푼다.
[~/Downloads]$ cd app 
[~/Downloads/app]$ tree  <== 압축을 푼 폴더의 구조를 확인한다. tree 가 없으면 따로 설치해야 한다.
.
├── bin
│   ├── app
│   └── app.bat
└── lib
    ├── app.jar
    ├── checker-qual-3.8.0.jar
    ├── error_prone_annotations-2.5.1.jar
    ├── failureaccess-1.0.1.jar
    ├── guava-30.1.1-jre.jar
    ├── j2objc-annotations-1.3.jar
    ├── jsr305-3.0.2.jar
    └── listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar

2 directories, 10 files
```

배포 파일에 들어 있는 스크립트 파일을 실행한다.

Windows OS:

```console
[~/Downloads/app]$ ./bin/app.bat
Hello World!
```

macOS/Linux/Unix OS:

```console
[~/Downloads/app]$ ./bin/app
Hello World!
```
