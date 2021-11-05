<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>대덕 인재 개발원</title>
  
  <link rel="stylesheet" href="../css/index.css">
  <link rel="stylesheet" href="../util/key/pinpad.css"/>
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<div class="body__container">

  <jsp:include page="/WEB-INF/views/index-nav.jsp" flush="false"/>

  <main>

    <section class="login">
      <h2>어서오세요.<br>대덕 인재 개발원 입니다.</h2>
      <form id="login-frm" action="" method="POST">
        <label for="login-id">아이디</label>
        <input id="login-id" type="text" name="userId" autocomplete="off">
        <label for="login-pw">비밀번호</label>
        <input id="login-pw" type="password" name="userPw" autocomplete="off">
        <div class="login-text"></div>
        <div class="robot">
        	<form action="/validation.do" method="POST">
		      <div class="g-recaptcha" data-sitekey="6Lf50ogcAAAAAHAVpGMtGgxpO1-rQjcKw5QHLyId"></div>
		    </form>
        </div>
        <button class="login-btn" type="button">로그인</button>
      </form>
      <div class="login-link-wrap">
        <button class="search-info-link login-page-btn">내정보 찾기</button>
        <button class="sign-up-link login-page-btn">회원가입</button>
      </div>
    </section>

    <section class="sign-up main-view-hidden">
      <form id="sign-up-frm" action="" method="post">
        <div class="id-wrap">
          <label for="sign-up-id">아이디</label>
          <div class="id-icon-wrap">
            <input id="sign-up-id" type="text" name="sign-up-id" autocomplete="off">
            <img src="../storage/img/ckIcon.png" alt="icon">
          </div>
          <span class="sign-up-id-text">아이디는 6~20자 영어와 숫자를 조합해야 합니다.</span>
        </div>

        <div class="pw-wrap">
          <label for="sign-up-pw">비밀번호</label>
          <div class="pw-icon-wrap">
            <input id="sign-up-pw" type="password" name="sign-up-pw" autocomplete="off">
            <img src="../storage/img/ckIcon.png" alt="icon">
          </div>
          <span class="sign-up-pw-text">비밀번호는 8~20자 영어, 숫자, 특수문자를 조합해야 합니다.</span>
        </div>

        <div class="pw-ck-wrap">
          <label for="sign-up-pw-ck">비밀번호 확인</label>
          <div class="pw-ck-icon-wrap">
            <input id="sign-up-pw-ck" type="password" name="sign-up-pw-ck" autocomplete="off">
            <img src="../storage/img/ckIcon.png" alt="icon">
          </div>
          <span class="sign-up-pw-ck-text">비밀번호가 일치하지 않습니다.</span>
        </div>

        <div class="name-wrap">
          <label for="sign-up-name">이름</label>
          <input id="sign-up-name" type="text" name="sign-up-name" autocomplete="off">
          <span class="sign-up-name-text">이름을 입력해 주세요.</span>
        </div>

        <div class="reg-no-wrap">
          <label for="sign-up-reg-no">주민번호</label>
          <input id="sign-up-reg-no" type="password" name="sign-up-reg-no" autocomplete="off" disabled readonly>
          <span class="sign-up-reg-text">주민번호13자리를 입력해 주세요.</span>
        </div>

        <div class="phone-wrap">
          <label for="sign-up-phone">전화번호</label>
          <div class="phone-icon-wrap">
            <input id="sign-up-phone" type="tel" name="sign-up-phone" autocomplete="off">
            <img src="../storage/img/ckIcon.png" alt="icon">
          </div>
          <span class="sign-up-phone-text">전화번호 형식에 맞게 입력해 주세요.</span>
        </div>

        <div class="license-wrap">
          <label for="sign-up-license">인증번호</label>
          <div class="license-icon-wrap">
            <input id="sign-up-license" type="text" name="sign-up-license" autocomplete="off">
            <button type="button" class="send-license">인증번호 전송</button>
            <button type="button" class="ck-license hidden">인증번호 확인</button>
            <img class="hidden" src="../storage/img/ckIconOk.png" alt="icon">
          </div>
          <span class="sign-up-license-text">문자 전송 완료</span>
        </div>

        <div class="job-wrap">
          <div class="job-title">직무</div>
          <label>
            <input id="sign-up-job-teacher" type="radio" name="sign-up-job" value="teach" checked>교사
          </label>
          <label>
            <input id="sign-up-job-admin" type="radio" name="sign-up-job" value="admin">행정
          </label>
        </div>

        <div class="email-wrap">
          <label for="sign-up-email">이메일</label>
          <div class="email-icon-wrap">
            <input id="sign-up-email" type="email" name="sign-up-email" autocomplete="off">
            <img src="../storage/img/ckIcon.png" alt="icon">
          </div>
          <span class="sign-up-eamil-text">이메일 형식에 맞게 입력해 주세요.</span>
        </div>
      </form>
      <button class="sign-up-btn" type="button">회원가입</button>
    </section>

    <section class="search-info main-view-hidden">
      <form class="search-id-frm" action="/" method="POST">
        <div class="search-id-name-wrap">
          <label for="search-id-name">이름</label>
          <input id="search-id-name" type="text" autocomplete="off">
        </div>
        <div class="search-id-regno-wrap">
          <label for="search-id-regno">주민번호</label>
          <input id="search-id-regno" type="password" autocomplete="off" readonly>
        </div>
        <div class="print-id"></div>
        <button class="search-id-btn" type="button">아이디 찾기</button>
      </form>

      <form class="reset-pw-frm" action="/" method="POST">
        <div class="reset-pw-id-wrap">
          <label for="reset-pw-id">아이디</label>
          <input id="reset-pw-id" type="text" autocomplete="off">
        </div>
        <div class="reset-pw-name-wrap">
          <label for="reset-pw-name">이름</label>
          <input id="reset-pw-name" type="text" autocomplete="off">
        </div>
        <div class="reset-pw-regno-wrap">
          <label for="reset-pw-regno">주민번호</label>
          <input id="reset-pw-regno" type="password" autocomplete="off" readonly>
        </div>
        <div class="print-info"></div>
        <button class="reset-pw-btn" type="button">임시비밀번호 발급</button>
      </form>
    </section>

  </main>

  <div class="login-side-bar">
    <div class="side-bar-btn-group">
      <button class="login-link login-page-btn side-bar-btn-hidden">로그인</button>
      <button class="search-info-link login-page-btn side-bar-btn-hidden">내정보 찾기</button>
      <button class="sign-up-link login-page-btn side-bar-btn-hidden">회원가입</button>
    </div>
  </div>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
let regNoCheck = false
</script>
<script type="text/javascript" src="../util/key/pinpad.js"></script>
<script src="../js/index.js"></script>
<script type="text/javascript">
new pinpad({
	ref : {
		el : '#sign-up-reg-no'
	},
	skin : 'white',
	mode : 'x4',
  setRandomSpaceCount: 4,
  passcode: true,
  format: '*************'
})
new pinpad({
	ref : {
		el : '#search-id-regno'
	},
	skin : 'white',
	mode : 'x4',
  setRandomSpaceCount: 4,
  passcode: true,
  format: '*************'
})
new pinpad({
	ref : {
		el : '#reset-pw-regno'
	},
	skin : 'white',
	mode : 'x4',
  setRandomSpaceCount: 4,
  passcode: true,
  format: '*************'
})
</script>
</body>
</html>