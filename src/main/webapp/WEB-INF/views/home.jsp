<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Fake DC - Main</title>

<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
  integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
  crossorigin="anonymous">

<link href="/resources/css/base.css" rel="stylesheet" />

<link rel="stylesheet"
  href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet"
  href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<script src="/resources/js/common.js"></script>



<!-- <script src="js/common.js"></script> -->
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
    <h2>
      <a href="/">FakeDC</a>
    </h2>
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

    <!-- 메인 슬라이드 영역 시작 -->
    <div class="main-slider">
      <!-- 갤러리를 만들 때 메인배너용 갤러리 이미지를 추가하게 하고 이 영역에 이 갤러리 내에 6시간 이내 작성된 게시물 중에서 조회수가 가장 많은순으로 3건을 표시 -->

      <!-- Swiper 메인 이미지-->
      <div class="swiper-container">
        <div class="swiper-wrapper">
          <div class="swiper-slide"></div>
          <div class="swiper-slide"></div>
          <div class="swiper-slide"></div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
      </div>

    </div>
    <!-- 메인 슬라이드 영역 끝 -->
    
<style>
.swiper-slide {
  height: 550px;
  background-size: cover;
}

.swiper-slide:nth-child(1) {
  background-image: url('/resources/img/img01.jpg');
}

.swiper-slide:nth-child(2) {
  background-image: url('/resources/img/img02.jpg');
  background-position-y: -33px;
}

.swiper-slide:nth-child(3) {
  background-image: url('/resources/img/img03.jpg');
}

.swiper-slide img {
  width: 100%;
}
</style>

    <!-- best pick 시작 -->
    <div class="best-picks">
      <!-- 이 영역은 존재하는 모든 갤러리중에서 1일동안 등록된 글중에서 추천이 가장 많은 6개만 표시할 것 -->
    </div>
    <!-- best pick 끝 -->

    <!-- swipe 배너 시작 -->
    <div class="swiper-container banner-container">
      <div class="swiper-wrapper">
        <div class="swiper-slide"></div>
        <div class="swiper-slide"></div>
        <div class="swiper-slide"></div>

      </div>
      <!-- Add Pagination -->
      <div class="swiper-pagination"></div>
      <!-- Add Arrows -->
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
    </div>
    <!-- swipe 배너 끝 -->

<!-- swipe 배너 -->
<style>
.banner-container .swiper-slide {
  height: 200px;
}
</style>
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

        <li><a href="/#"><i class="fa fa-facebook-square"
            aria-hidden="true"></i></a></li>
        <li><a href="/#"><i class="fa fa-instagram"
            aria-hidden="true"></i></a></li>
        <li><a href="/#"><i class="fa fa-twitter-square"
            aria-hidden="true"></i></a></li>
      </ul>
    </div>

    <div class="footer_bottom">
      <div class="footer_logo">
        <h1>FakeDC</h1>
      </div>
      <div class="footer_info">
        <p>대표이사 : 최성혁 | 사업자정보확인 | 개인정보관리책임자 최성혁 | 사업자등록번호
          106-81-12310</p>
        <p>서울 서초구 과천대로 111-11 마포구 상암산로 11 FakeDC | 호스팅제공자: 가비아 |
          고객센터 1670-1111(평일 09시 ~ 18시) | 대표메일 likeaglow@gmail.com</p>
        <p>Copyright (C) FakeDC All rights reserved.</p>
      </div>
    </div>
  </footer>
  <!-- 푸터 영역 끝 -->
</body>

<!-- swipe 메인 이미지 -->
<script>
  var swiper = new Swiper('.swiper-container', {
    pagination : {
      el : '.swiper-pagination',
      dynamicBullets : true,
    },
  });
</script>

<!-- swipe 배너 -->
<script>
  var swiper = new Swiper('.swiper-banner-container', {
    slidesPerView : 1,
    spaceBetween : 30,
    loop : true,
    pagination : {
      el : '.swiper-banner-pagination',
      clickable : true,
    },
    navigation : {
      nextEl : '.swiper-banner-button-next',
      prevEl : '.swiper-banner-button-prev',
    },
  });
</script>
</html>
