<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Fake DC - Main</title>

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

  <link href="/resources/css/base.css" rel="stylesheet" />
  <link href="/resources/css/login.css" rel="stylesheet" />

  <script src="js/common.js"></script>
</head>

<body>
  <!-- 1. 레이아웃 -->
  <!-- 2. 개별 요소들의 스타일 -->

  <!-- 탑 요소 시작 -->
  <header>
    <div class="header-left">
      <c:choose>
        <c:when test="${ member != null }">
          <span>${ member.MEM_NAME } 님 환영합니다!</span>
        </c:when>
        <c:otherwise>
          <span>FakeDC에 오신 것을 환영합니다.</span>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="header-right">
      <ul class="member-command">
        <c:choose>
          <c:when test="${ member != null }">
            <li><a href="/member/logout">로그아웃</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="/member/login">로그인</a></li>
            <li><a href="/member/register">회원가입</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </header>
  <!-- 탑 요소 끝 -->

  <!-- 메뉴 시작 -->
  <nav>
    <h2><a href="/">FakeDC</a></h2>
    <ul class="galleries">
      <li><a href="/board/pop">해외음악 갤러리</a></li>
      <li><a href="/board/major-football">해외축구 갤러리</a></li>
      <li><a href="/board/assassin-creed">어쌔신크리드 갤러리</a></li>
      <li><a href="/board/kpop-girlfriend">걸그룹 여자친구 갤러리</a></li>
      <li><a href="/board/wagal-dish">와갤요리 갤러리</a></li>
    </ul>
  </nav>
  <!-- 메뉴 끝 -->

  <main class="container">
   
    <form class="form-login" method="post">

      <div class="form-control">
        <input type="text" name="MEM_ID" placeholder="아이디" value="${ vo.MEM_ID }" />
      </div>
      <div class="form-control">
        <input type="password" name="MEM_PASSWORD" placeholder="비밀번호" value="${ vo.MEM_PASSWORD }" />
      </div>

      <div class="form-button-group">
        <button type="submit" class="confirm">로그인</button>
        <button type="button" class="register">회원가입</button>
      </div>


    </form>
  </main>

  <!-- 푸터 영역 시작 -->
  <footer>
    <div class="footer_top">
      <ul class="footer_nav">
        <li><a href="/#">소개</a></li>
        <li><a href="/#">고객센터</a></li>
        <li><a href="/#">FAQ</a></li>
        <li><a href="/#">이용약관</a></li>
        <li><a href="/#">개인정보처리방침</a></li>
        <li><a href="/#">청소년 보호정책</a></li>
        <li><a href="/#">제휴/광고 문의</a></li>
        <li><a href="/#">메인 바로가기</a></li>
      </ul>

      <ul class="sns">

        <li><a href="/#"><i class="fa fa-facebook-square" aria-hidden="true"></i></a></li>
        <li><a href="/#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
        <li><a href="/#"><i class="fa fa-twitter-square" aria-hidden="true"></i></a></li>
      </ul>
    </div>

    <div class="footer_bottom">
      <div class="footer_logo">
        <h1>FakeDC</h1>
      </div>
      <div class="footer_info">
        <p>대표이사 : 최성혁 | 사업자정보확인 | 개인정보관리책임자 최성혁 | 사업자등록번호 106-81-12310 </p>
        <p>서울 서초구 과천대로 111-11 마포구 상암산로 11 FakeDC | 호스팅제공자: 가비아 | 고객센터 1670-1111(평일 09시 ~ 18시) | 대표메일
          likeaglow@gmail.com</p>
        <p>Copyright (C) FakeDC All rights reserved.</p>
      </div>
    </div>
  </footer>
  <!-- 푸터 영역 끝 -->
</body>

</html>
