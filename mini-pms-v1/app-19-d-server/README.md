# 19-d. 데이터 관리 서버 만들기 : 프로토콜 정의 및 적용

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

### 요청 프로토콜

```
요청 데이터 규칙: 
명령(UTF-8 문자열) CRLF
JSON 데이터(UTF-8 문자열) CRLF  <== 명령어에 따라 선택 사항

예) 게시글 목록 요청
/board/list CRLF

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
JSON 데이터(UTF-8 문자열) CRLF  <== 처리 결과에 따라 선택 사항


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

예) board/update 요청에 대한 응답
success CRLF

예) board/delete 요청에 대한 응답
success CRLF
```

## 실습

### 1단계 - 프로토콜에 맞춰 게시글 데이터의 저장 요청을 처리한다.

- com.eomcs.pms.domain.Member 클래스 가져오기
- com.eomcs.pms.domain.Board 클래스 가져오기
- `com.eomcs.pms.ServerApp` 변경
    - 프로토콜에 맞춰 JSON 형식으로 받은 데이터를 Board 객체로 변환하여 저장한다.
    - 저장 성공 시 success 응답을 보낸다.
    - 저장 실패 시 fail 응답을 보낸다.


## 실습 결과
- src/main/java/com/eomcs/pms/ServerApp.java 변경
