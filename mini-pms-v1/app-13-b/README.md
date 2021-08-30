# 13-b. 사용자 인증 : 내 정보 보기 

이번 훈련에서는 **로그인(login)/로그아웃(logout)** 을 구현해 보자.
 
## 훈련 목표

- 


## 훈련 내용

- 

## 실습


### 1단계 - 내정보 메뉴를 추가한다.


```
[메인]
1. 로그인 
2. 내정보 <--- 메뉴 추가 
3. 게시판
4. 회원
5. 프로젝트
6. 작업
7. 관리1
0. 종료

선택>
```

- com.eomcs.pms.App 변경
  - createMenu() 메서드 변경


### 2단계 - 내정보를 출력할 핸들러를 정의한다.

- com.eomcs.pms.handler.AuthHandler 클래스 변경
  - loginUser 필드 추가 : 로그인 정보를 담을 필드
  - login() 변경: 로그인 한 후 멤버 객체를 loginUser 필드에 저장
  - displayLoginUser() 메서드 추가


## 실습 결과

- src/main/java/com/eomcs/menu/App.java 변경
- src/main/java/com/eomcs/pms/handler/AuthHandler.java 변경