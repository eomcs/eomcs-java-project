# 01.2 프로젝트 준비 - 버전 관리 시스템 `Git` 적용

## 학습 목표

- 프로젝트 폴더를 로컬 Git 저장소로 만들 수 있다.
- 로컬 Git 저장소를 `github.com` 서버의 원격 저장소와 연결할 수 있다.

## 실습

### 1단계 - 프로젝트 폴더를 깃 저장소(git repository)로 만든다

git 클라이언트 프로그램을 이용하여 프로젝트 폴더를 Git 저장소로 설정한다.

```console
~/mylist$ git init
Initialized empty Git repository in /Users/eomjinyoung/mylist/.git/

~/mylist$ tree -ad   <== 옵션 a(숨겨진 파일 및 디렉토리 출력)와 d(디렉토리만 출력)를 추가한다.
.
├── .git       <=== 이 폴더가 로컬 깃 저장소다.
│   ├── hooks
│   ├── info
│   ├── objects
│   │   ├── info
│   │   └── pack
│   └── refs
│       ├── heads
│       └── tags
├── .gradle
│   ├── 7.4
│   │   ├── checksums
│   │   ├── dependencies-accessors
│   │   ├── executionHistory
│   │   ├── fileChanges
│   │   ├── fileHashes
│   │   └── vcsMetadata
│   ├── buildOutputCleanup
│   └── vcs-1
├── app
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── com
│   │   │       │       └── eomcs
...

74 directories
```

### 2단계 - 버전 관리 대상에서 제외할 항목을 지정한다

로컬 깃 저장소에 보관하지 않을 파일이나 디렉토리는 `.gitignore` 파일에 등록한다.
`.gitignore`에 기록된 파일이나 디렉토리는 깃 버전 관리 대상에서 제외된다.

- 제외 항목 선정하기
  - **gitignore.io** 사이트에 접속한다.
  - 제외 목록을 생성한다.
    - java,linux,macos,gradle,windows,eclipse,visualstudiocode,node 으로 검색.
- `.gitignore` 파일 변경
  - `~/mylist/.gitignore` 파일에 제외 목록을 복사한다.

### 3단계 - 버전 관리 대상(파일 및 폴더)을 깃 스테이지에 등록한다

현재 프로젝트에서 버전 관리 대상으로 등록할 대상(파일과 디렉토리)을 지정한다.
단, .gitignore 파일에 적어 놓은 파일과 디렉토리는 제외한다.

```console
~/mylist$ git add .    <== 현재 폴더 및 하위 폴더의 모든 디렉토리 및 파일 추가
```

### 4단계 - 로컬 깃 저장소에 백업한다

Git 스테이지에 등록된 파일 및 디렉토리를 로컬 Git 저장소에 보관한다

```console
~/mylist$ git commit -m "프로젝트준비"
[master (root-commit) c65cb99] 프로젝트준비
 10 files changed, 753 insertions(+)
 create mode 100644 .gitattributes
 create mode 100644 .gitignore
 create mode 100644 app/build.gradle
 create mode 100644 app/src/main/java/com/eomcs/mylist/App.java
 create mode 100644 app/src/test/java/com/eomcs/mylist/AppTest.java
 create mode 100644 gradle/wrapper/gradle-wrapper.jar
 create mode 100644 gradle/wrapper/gradle-wrapper.properties
 create mode 100755 gradlew
 create mode 100644 gradlew.bat
 create mode 100644 settings.gradle
```

### 5단계 - **github.com** 에 원격 깃 저장소를 만든다

github.com 사이트에 로그인하여 mini-pms를 보관할 원격 저장소를 만든다.

- github.com 에 원격 저장소 생성
  - github.com 사이트에 로그인 한다.
  - 저장소(repository)를 생성한다.
  - 저장소의 URL을 복사해둔다.

### 6단계 - 로컬 깃 저장소와 원격 깃 저장소(github.com)를 연결한다.

로컬 깃 저장소를 업로드할 원격 깃 저장소를 지정한다.
`git remote add [원격저장소를 가리키는 이름] [원격저장소 URL]` 명령을 사용하여
로컬 저장소를 원격 저장소와 연결한다.
원격 저장소 이름은 보통 `origin`으로 한다.

```console
~/mylist$ git remote add origin https://github.com/eomjinyoung/mylist.git   <== 5단계에서 만든 원격 깃 저장소의 URL을 등록

~/mylist$ git branch   <== 현재 로컬 저장소에 존재하는 소스 브랜치 목록
* master

~/mylist$ git branch -M main   <== 현재 작업 브랜치의 이름을 "main" 으로 변경

~/mylist$ git branch   <== 브랜치 이름 변경 확인
* main

~/mylist$ git remote   <== 로컬 깃 저장소와 연결된 원격 깃 저장소의 이름 확인
origin

~/mylist$ git remote -v   <=== 로컬 깃 저장소와 연결된 원격 깃 저장소의 URL 확인
origin	https://github.com/eomjinyoung/mylist.git (fetch)
origin	https://github.com/eomjinyoung/mylist.git (push)
```

### 7단계 - 로컬 깃 저장소의 내용을 원격 깃 저장소에 업로드 한다.

로컬 깃 저장소의 내용을 github.com에 생성한 원격 깃 저장소로 업로드 한다.
`git push --set-upstream [원격저장소이름] [로컬브랜치]:[원격브랜치]`
또는 `git push -u [원격저장소이름] [로컬브랜치]:[원격브랜치]`명령을 실행한다.
`[원격저장소이름]`은 6단계에서 등록한 이름(`origin`) 이다.

**브랜치** 는 특정 버전의 내용물을 가리키는 이름이다.
`[로컬브랜치]`는 로컬 깃 저장소의 현재 버전을 가리키는 브랜치 이름이다.
프로젝트 내용물을 로컬 깃 저장소에 백업하기 위해서 `git commit` 명령을 처음 실행할 때
`master`라는 이름의 로컬 브랜치가 자동 생성된다.
위에서 `$ git branch -M main` 명령을 시행하여 브랜치 이름을 바꿨다면,
`main` 이름을 사용하라.

먼저 원격 깃 저장소의 파일 및 커밋 기록을 로컬 깃 저장소와 합친다.
```console
~/mylist$ git pull origin main --allow-unrelated-histories
From https://github.com/eomjinyoung/mylist
 * branch            main       -> FETCH_HEAD
Merge made by the 'recursive' strategy.
 README.md | 2 ++
 1 file changed, 2 insertions(+)
 create mode 100644 README.md
```

그런 후 로컬 깃 저장소의 변경 내용을 원격 깃 저장소로 업로드 한다.
```console
~/mylist$ git push -u origin main:main
Counting objects: 28, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (17/17), done.
Writing objects: 100% (28/28), 62.45 KiB | 6.94 MiB/s, done.
Total 28 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), done.
To https://github.com/eomjinyoung/mylist.git
   70ac502..911467c  main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.

```

### 8단계 - 백업되지 않은 폴더를 깃 저장소에 백업되게 한다.

깃 저장소는 빈 폴더를 백업하지 않는다. 원격 깃 저장소에 업로드된 것을 확인해 보면, `mylist/app/src/main/resources` 와 `mylist/app/src/test/resources` 폴더가 존재하지 않는 것을 알 수 있다. 이들 폴더를 깃 저장소에 백업하려면 임의의 파일을 저장하여 폴더가 비어있지 않게 해야 한다.

- `mylist/app/src/main/resources` 폴더에 `README.md` 파일을 저장한다.
- `mylist/app/src/test/resources` 폴더에 `README.md` 파일을 저장한다.

로컬 깃 저장소에 커밋하고 원격 깃 저장소에 푸쉬한다.
```console
~/mylist$ git add .

~/mylist$ git commit -m "resource 폴더 백업"
[main b8215f4] resource 폴더 백업
 2 files changed, 2 insertions(+)
 create mode 100644 app/src/main/resources/README.md
 create mode 100644 app/src/test/resources/README.md

~/mylist$ git push
Counting objects: 10, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (6/6), done.
Writing objects: 100% (10/10), 725 bytes | 181.00 KiB/s, done.
Total 10 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/eomjinyoung/mylist.git
   911467c..b8215f4  main -> main
```

`github.com` 사이트에서 해당 폴더가 백업되었는지 확인한다.
