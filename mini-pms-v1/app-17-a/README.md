# 17-a. 메뉴 리팩토링: 상세보기에서 변경, 삭제 기능 수행하기

이번 훈련에서는 객체 간의 종속성을 줄이는 방법을 알아본다.


## 훈련 목표

-

## 훈련 내용

- 


## 실습


### 1단계 - 객체 간의 종속성 이해하기

- 게시글 상세 보기에서 게시글 변경, 삭제를 수행하려면,
  - BoardDetailHandler 는 BoardUpdateHandler와 BoardDeleteHandler를 알아야 한다.
  - 즉 인스턴스 필드에 사용할 객체를 담고 있어야 한다.
  - BoardDetailHandler는 BoardUpdateHandler와 BoardDeleteHandler 없이 사용할 수 없다.
  - 이것이 '종속된다'는 의미다.

- com.eomcs.pms.handler.BoardDetailHandler 변경
  - 생성자에서 BoardUpdateHandler와 BoardDeleteHandler 객체를 파라미터로 받아 보관 한다.
  - 상세보기에서 게시글 변경, 삭제를 수행할 수 있도록 변경한다.
- com.eomcs.pms.App 클래스 변경
  - BoardDetailHandler의 변경에 맞춰 의존 객체를 파라미터로 넘긴다.
  - 게시글 변경, 삭제 메뉴를 제거한다.

