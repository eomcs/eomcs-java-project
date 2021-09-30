# 19-f. 데이터 관리 서버 만들기 : 파일 및 데이터 처리 기능을 서버로 이전


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
- 통신 프로토콜을 이해한다.
- `extract method` 리팩토링 기법을 연습한다.

## 훈련 내용
- 응답 프로토콜을 변경하고 그에 맞게 구현한다.
- 응답을 수신하는 코드를 별도의 메서드로 분리한다.

## 실습

### 요청 프로토콜 변경

```
요청 데이터 규칙: 
명령(UTF-8 문자열) CRLF
JSON 데이터(UTF-8 문자열) CRLF  <== 명령어에 따라 선택 사항

예) 게시글 목록 요청
/board/list CRLF
CRLF

예) 게시글 상세 요청
/board/detail CRLF
{"no": 1} CRLF

예) 게시글 등록 요청
/board/add CRLF
{
    "title": "제목", 
    "content": "내용", 
    "writer": {
        "no": 1, "name": "홍길동"
    }
} CRLF

예) 게시글 변경 요청
board/update CRLF
{
    "no": 1
    "title": "제목", 
    "content": "내용"
} CRLF

예) 게시글 삭제 요청
/board/delete CRLF
{"no": 1} CRLF
```

### 응답 프로토콜

```
응답 데이터 규칙: 
처리상태(success | fail) CRLF
JSON 데이터(UTF-8 문자열) CRLF  <== 데이터가 없으면 빈 문자열을 응답한다.


예) /board/list 요청에 대한 응답
success CRLF
게시글 목록에 대한 JSON 데이터 CRLF


예) board/detail 요청에 대한 응답
success CRLF
{
    "no": 1
    "title": "제목", 
    "content": "내용", 
    "writer": {
        "no": 1, "name": "홍길동"
    },
    "registeredDate": "2021-01-01",
    "viewCount": 11,
    "like": 5
} CRLF

예) board/add 요청에 대한 응답
success CRLF
CRLF

예) board/update 요청에 대한 응답
success CRLF
CRLF

예) board/delete 요청에 대한 응답
success CRLF
CRLF
```



### 1단계 - 메뉴 처리 코드를 가져온다.

- com.eomcs.menu 패키지 및 클래스를 가져온다.
    - com.eomcs.pms.AuthLoginHandler 클래스에 종속되는 문제가 있다.
    - 해결책!
        - getMenuList() 변경: 
            - MenuFilter 도입. 
            - File 클래스에서 FileFilter 역할을 하는 객체처럼 출력할 메뉴를 선별하는 기능을 별도의 객체로 분리한다.
- com.eomcs.menu.Menu 클래스 변경
    - accessScope 필드의 겟터 추가
- com.eomcs.menu.MenuFilter 인터페이스 정의
    - java.io.FileFilter 인터페이스 참조.
- com.eomcs.menu.MenuGroup 클래스 변경
    - MenuFilter 객체를 저장할 필드와 셋터/겟터 메서드를 추가한다.

### 2단계 - Command 디자인 패턴 관련 코드를 가져온다.

- com.eomcs.pms.handler.Command 인터페이스를 가져온다.
- com.eomcs.pms.handler.CommandRequest 클래스를 가져온다.
- com.eomcs.pms.handler.RequestDispatcher 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경
    - Command 맵 필드를 가져온다.
    - ClientApp 생성자 준비

### 3단계 - Observer 디자인 패턴 관련 코드를 가져온다.

- com.eomcs.context 패키지 및 클래스, 인터페이스 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경
    - 옵저버 관련 필드와 메서드 가져온다.
    - notifyOnXxx() 메서드를 가져온다.

### 4단계 - 메뉴 생성 관련 코드를 가져온다.

- com.eomcs.pms.ClientApp 클래스 변경
    - 메뉴 생성과 관련된 코드를 가져온다.

### 5단계 - service() 실행 관련 코드를 가져온다.

- com.eomcs.pms.ClientApp 클래스 변경
    - service() 메서드 가져온다.
    - main() 메서드 변경

### 6단계 - AppInitListener 옵저버 코드를 가져온다.

- com.eomcs.pms.listener.AppInitListener 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경
    - AppInitListener 옵저버를 등록한다.

### 7단계 - RequestAgent 필드를 인스턴스 필드로 전환한다.

- com.eomcs.pms.ClientApp 클래스 변경
    - RequestAgent 스태틱 필드를 인스턴스 필드로 전환.
    - ClientApp() 생성자 변경 : RequestAgent 인스턴스 생성.

### 8단계 - 회원 등록 커맨드를 C/S 구조에 맞게 변경(마이그레이션)한다.

- com.eomcs.pms.handler.MemberAddHandler 클래스를 가져온다.
    - 이제부터 회원 데이터는 서버에서 처리할 것이다.
    - 회원 목록을 다루는 의존 객체 List를 제거한다.
    - 대신에 서버와 통신하여 데이터를 처리하는 의존 객체를 주입한다.
- com.eomcs.pms.ClientApp 클래스 변경
    - MemberAddHandler 객체를 생성하여 커맨드맵에 등록한다.

### 9단계 - 회원 목록 조회 커맨드를 C/S 구조에 맞게 변경(마이그레이션)한다.

- com.eomcs.request.RequestAgent 클래스 변경
    - getObjects() 메서드를 추가한다.
- com.eomcs.pms.handler.MemberListHandler 클래스를 가져온다.
    - 회원 목록을 다루는 의존 객체를 List 대신 RequestAgent를 사용한다.
    - RequestAgent는 서버와 통신하여 데이터를 처리하는 객체이다.
- com.eomcs.pms.ClientApp 클래스 변경
    - MemberListHandler 객체를 생성하여 커맨드맵에 등록한다.

### 10단계 - 회원 상세 조회 커맨드를 C/S 구조에 맞게 변경(마이그레이션)한다.

- com.eomcs.pms.handler.MemberDetailHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 11단계 - 회원 변경 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.MemberUpdateHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 12단계 - 회원 삭제 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.MemberDeleteHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 13단계 - 게시판 관리 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.MemberXxxHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 14단계 - 로그인/로그아웃/사용자정보 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.AuthLoginHandler 클래스를 가져온다.
- com.eomcs.pms.handler.AuthLogoutHandler 클래스를 가져온다.
- com.eomcs.pms.handler.AuthUserInfoHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 15단계 - 프로젝트 관리 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.MemberPrompt 클래스를 가져온다.
- com.eomcs.pms.handler.ProjectXxxHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경

### 16단계 - 작업 관리 커맨드를 C/S 구조에 맞게 마이그레이션 한다.

- com.eomcs.pms.handler.ProjectPrompt 클래스를 가져온다.
- com.eomcs.pms.handler.AbstractTaskHandler 클래스를 가져온다.
    - TaskHandlerHelper 클래스로 변경한다.
- com.eomcs.pms.handler.TaskXxxHandler 클래스를 가져온다.
- com.eomcs.pms.ClientApp 클래스 변경