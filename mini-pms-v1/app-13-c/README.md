# 13-c. 사용자 인증 : 로그아웃

이번 훈련에서는 **로그인(login)/로그아웃(logout)** 을 구현해 보자.
 
## 훈련 목표

- 


## 훈련 내용

- 

## 실습


### 1단계 - '로그아웃' 메뉴를 추가한다.


```
[메인]
1. 로그인 
2. 내정보
3. 로그아웃 <--- 메뉴 추가 
4. 게시판
5. 회원
6. 프로젝트
7. 작업
8. 관리1
0. 종료

선택>
```

- com.eomcs.pms.App 변경
  - createMenu() 메서드 변경


### 2단계 - 로그아웃을 수행할 핸들러를 정의한다.

- com.eomcs.pms.handler.AuthHandler 클래스 변경
  - logout() 메서드 추가: loginUser 필드를 null로 초기화 한다.


## 실습 결과

- src/main/java/com/eomcs/menu/App.java 변경
- src/main/java/com/eomcs/pms/handler/AuthHandler.java 변경