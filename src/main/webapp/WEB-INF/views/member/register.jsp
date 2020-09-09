<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Fake DC - Register</title>

  <link rel="stylesheet" href="/https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

  <link href="/resources/css/base.css" rel="stylesheet" />
  <link href="/resources/css/register.css" rel="stylesheet" />

  <script src="js/common.js"></script>
</head>

<body>
  <!-- 1. 레이아웃 -->
  <!-- 2. 개별 요소들의 스타일 -->

  <!-- 탑 요소 시작 -->
  <header>
    <div class="header-left">
      <span>FakeDC에 오신 것을 환영합니다.</span>
      <!-- <span>최성혁 님 환영합니다!</span> -->
    </div>
    <div class="header-right">
      <ul class="member-command">
        <li><a href="/#">로그인</a></li>
        <li><a href="/#">회원가입</a></li>
        <!-- <li><a href="/#">로그아웃</a></li> -->
      </ul>
    </div>
  </header>
  <!-- 탑 요소 끝 -->

  <!-- 메뉴 시작 -->
  <nav>
    <h2>FakeDC</h2>
    <ul class="galleries">
      <li><a href="/#">해외음악 갤러리</a></li>
      <li><a href="/#">해외축구 갤러리</a></li>
      <li><a href="/#">어쌔신크리드 갤러리</a></li>
      <li><a href="/#">걸그룹 여자친구 갤러리</a></li>
      <li><a href="/#">와갤요리 갤러리</a></li>
    </ul>
  </nav>
  <!-- 메뉴 끝 -->

  <main class="container">
    <form class="form-register" method="POST">

      <div class="form-control">
        <label for="id">아이디</label>
        <input type="text" name="MEM_ID" id="id">
      </div>

      <div class="form-control">
        <label for="password">비밀번호</label>
        <input type="password" name="MEM_PASSWORD" id="password">
      </div>

      <div class="form-control">
        <label for="password_confirm">비밀번호 확인</label>
        <input type="password" id="password_confirm">
      </div>

      <div class="form-control">
        <label for="phone">이름</label>
        <input type="text" name="PHONE_NUM" id="phone">
      </div>

      <div class="form-control">
        <label for="email">이메일</label>
        <input type="email" name="EMAIL" id="email">
      </div>

      <div class="form-control">
        <label for="phone">휴대폰 번호</label>
        <input type="text" name="PHONE_NUM" id="phone">
      </div>

      <div class="form-control">
        <label for="birthday">생년월일</label>
        <input type="date" name="BIRTHDAY" id="birthday">
      </div>

      <div class="form-control">
        <label>정보 제공 동의</label>
        <div class="form-radio-group">
          <label for="rdo-offer-agree">동의</label>
          <input type="radio" id="rdo-offer-agree" name="INFO_OFFER" value="Y" ${ (vo.INFO_OFFER=="Y" ? "checked" : "" )
            } />

          <label for="rdo-offer-disagree">동의하지 않음</label>
          <input type="radio" id="rdo-offer-disagree" name="INFO_OFFER" value="N" ${ (vo.INFO_OFFER=="N" ? "checked"
            : "" ) } />

        </div>
      </div>

      <div class="form-button-group">
        <button type="submit" class="submit">제출</button>
        <button type="button" class="reset">다시 쓰기</button>
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