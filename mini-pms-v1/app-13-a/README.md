# 13-a. 사용자 인증 : 로그인

이번 훈련에서는 **로그인(login)/로그아웃(logout)** 을 구현해 보자.
 
## 훈련 목표

- 


## 훈련 내용

- 

## 실습


### 1단계 - 로그인 메뉴를 추가한다.


```
[메인]
1. 로그인 <--- 메뉴 추가 
2. 게시판
3. 회원
4. 프로젝트
5. 작업
6. 관리1
0. 종료

선택>
```

- com.eomcs.pms.App 변경
  - createMenu() 메서드 변경
  - 백업: App.java.01


### 2단계 - 로그인을 처리할 핸들러를 정의한다.

- com.eomcs.pms.handler.AuthHandler 클래스 추가
  - login() 메서드 정의


### 3단계 - 로그인 핸들러 객체를 준비한다.

- com.eomcs.pms.App 클래스 변경




## 실습 결과

- src/main/java/com/eomcs/menu/App.java 변경
- src/main/java/com/eomcs/pms/handler/AuthHandler.java 추가