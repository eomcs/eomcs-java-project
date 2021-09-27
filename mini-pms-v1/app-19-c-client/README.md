# 19-c. 데이터 관리 서버 만들기 : 사용자가 입력한 명령처리

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
- 콘솔에서 입력 받는 것을 연습한다.
- 입력 받은 값을 서버에 전송하는 것을 연습한다.

## 훈련 내용
- 콘솔 입력 스트림(System.in)을 Scanner 객체에 연결하여 사용자가 입력한 값을 얻는다.
- 소켓의 출력 스트림을 통해 서버에 전송한다.


## 실습

### 1단계 - 사용자가 입력한 명령을 서버에 전송한다.

- com.eomcs.util.Prompt 가져오기
  - 이전 프로젝트(28-b)에서 가져온다.
- com.eomcs.pms.ClientApp 변경
  - Prompt 를 사용하여 사용자 입력을 처리한다.
  - 백업: ClientApp.java.01


### 2단계 - 사용자가 quit 명령을 입력할 때까지 반복한다.

- com.eomcs.pms.ClientApp 변경


## 실습 결과
- src/main/java/com/eomcs/util/Prompt.java 추가
- src/main/java/com/eomcs/pms/ClientApp.java 변경
