# 01.1 프로젝트 준비 - `Gradle` 빌드 도구를 이용한 프로젝트 폴더 구성

## 학습 목표

- `그레이들(Gradle)` 빌드 도구를 이용하여 자바 프로젝트의 디렉토리를 구성할 수 있다.
- **메이븐(Maven) 빌드 도구의 표준 디렉토리 구조**를 설명할 수 있다.
- `그레이들`로 프로젝트를 빌드, 배포, 실행할 수 있다.

## 실습

### 1단계 - 프로젝트로 사용할 디렉토리 준비한다

사용자 홈 폴더(예: /Users/eomjinyoung)에 자바 프로젝트로 사용할 폴더를 생성한다.

```console
~$ mkdir mylist
~$ cd mylist
~/mylist$
```

### 2단계 - 생성한 디렉토리를 자바 애플리케이션 프로젝트 폴더로 전환한다.

`Gradle` 빌드 도구를 이용하여 자바 애플리케이션 프로젝트에 필요한 설정 파일과 폴더를 준비한다.

```console
~/mylist$ gradle init   
Select type of project to generate:
  1: basic
  2: application       <== 프로젝트 유형으로 '애플리케이션 개발'을 선택한다.
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java       <== 프로그래밍 언어로 'Java'를 선택한다.
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Split functionality across multiple subprojects?:
  1: no - only one application project       <== 한 개의 프로젝트를 수행하는 것으로 설정한다.
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 1

Select build script DSL:
  1: Groovy       <== 빌드 스크립트 파일을 작성할 때 사용할 언어로 `Groovy`를 선택한다.
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no]
Select test framework:
  1: JUnit 4       <== 단위 테스트에 사용할 프레임워크로 `Junit 4`를 선택한다.
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit Jupiter) [1..4] 1

Project name (default: mylist):     <== 그냥 엔터치면 현재 디렉토리명을 프로젝트 이름으로 사용한다.
Source package (default: mylist.console): com.eomcs.mylist     <== 프로젝트의 자바 패키지를 설정한다.

> Task :init
Get more help with your project: https://docs.gradle.org/7.4/samples/sample_building_java_applications.html

BUILD SUCCESSFUL in 33s
2 actionable tasks: 2 executed
```

### 3단계 - 프로젝트의 디렉토리 구조 확인한다

Gradle로 생성한 프로젝트 설정 파일과 폴더를 확인한다.

```console
~/mylist$  tree   <== tree 명령을 실행하면 현재 폴더의 구조를 보여준다.
$ tree
.
├── app
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

16 directories, 8 files
```

### 4단계 - 자바 애플리케이션을 실행한다

Gradle을 사용하여 자바 애플리케이션을 실행한다.

```console
~/mylist$ gradle run

> Task :app:run
Hello World      <== 실행 결과

BUILD SUCCESSFUL in 10s
2 actionable tasks: 2 executed
```

콘솔에 애플리케이션 실행 결과만 출력하고 싶다면 -q 또는 --quiet 옵션을 붙인다.

```console
~/mylist$ gradle -q run
Hello World!
```

### 5단계 - 애플리케이션의 배포 파일을 생성한다.

Gradle을 사용하여 일반 사용자에게 배포할 파일을 만든다.

```console
~/mylist$ gradle build
```

#### build 절차

- 소스 파일을 컴파일한다.
- 단위 테스트를 수행한다.
- .jar 아카이브 파일을 만든다.
- 실행과 관련된 파일을 생성한다.
- .tar, .zip 배포 파일을 생성한다.

프로젝트 빌드를 통해 생성된 파일과 폴더는 build 디렉토리 아래에 놓인다.

```console
~/mylist$ tree  <== 윈도우에서는 파일 탐색기로 확인하면 된다.
$ tree
.
├── app
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
│   │   │   ├── app.tar    <== 이 파일을 Unix/Linux 사용자에게 배포한다.
│   │   │   └── app.zip    <== 이 파일을 Windows 사용자에게 배포한다.
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

55 directories, 28 files
```

### 6단계 - 파일을 배포하고 실행한다

배포 파일을 받은 일반 사용자는 보통 다음의 절차에 따라 애플리케이션을 실행한다.

적당한 폴더에 배포 파일(.tar 또는 .zip)의 압축을 푼다.
배포 파일을 `Downloads` 폴더에 다운로드 받았다고 가정하자.  

```console
~/Downloads$ tar -xvf app.tar  <== 압축 파일을 푼다.
x app/
x app/lib/
x app/lib/app.jar
x app/lib/guava-30.1.1-jre.jar
x app/lib/failureaccess-1.0.1.jar
x app/lib/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar
x app/lib/jsr305-3.0.2.jar
x app/lib/checker-qual-3.8.0.jar
x app/lib/error_prone_annotations-2.5.1.jar
x app/lib/j2objc-annotations-1.3.jar
x app/bin/
x app/bin/app
x app/bin/app.bat

~/Downloads$ cd app  <== 압축 푼 폴더로 이동한다.
~/Downloads/app$ tree  <== 압축 푼 폴더의 구조를 확인한다. tree 가 없으면 따로 설치해야 한다.
.
├── bin
│   ├── app   <== Unix/Linux 에서는 이 파일을 실행한다.
│   └── app.bat   <== Windows 에서는 이 파일을 실행한다.
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
~/Downloads/app$ ./bin/app.bat
Hello World!
```

macOS/Linux/Unix OS:
```console
~/Downloads/app$ ./bin/app
Hello World!
```
