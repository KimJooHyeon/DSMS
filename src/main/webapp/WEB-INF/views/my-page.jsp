<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/my-page.css">

</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
	
		<section class="my-section">
	      <div class="inner">
	        <form id="my-frm" action="" method="POST">
	          <div class="id-wrap">
	            <div class="label">아이디</div>
	            <div class="user-id">${userInfo.getMember_id()}</div>
	          </div>
	          <div class="pass-wrap">
	            <label for="user-pw">비밀번호</label>
	            <div class="pw-icon-wrap">
	              <input id="user-pw" type="password" name="user-pw">
	              <img src="../storage/img/ckIcon.png" alt="check-icon">
	            </div>
	            <div class="pass-text">비밀번호를 입력해 주세요.</div>
	          </div>
	          <div class="pass-ck-wrap">
	            <label for="user-pw-ck">비밀번호 확인</label>
	            <div class="pw-ck-icon-wrap">
	              <input id="user-pw-ck" type="password">
	              <img src="../storage/img/ckIcon.png" alt="check-icon">
	            </div>
	            <div class="pass-ck-text">비밀번호를 입력해 주세요.</div>
	          </div>
	          <div class="name-wrap">
	            <div class="label">이름</div>
	            <input type="hidden" value="username" name="user-name">
	            <div class="user-name">${userInfo.getMember_name()}</div>
	          </div>
	          <div class="phone-wrap">
	            <label for="user-phone">전화번호</label>
	            <div class="phone-icon-wrap">
	              <input id="user-phone" type="tel" value="${userInfo.getMember_tel()}" disabled name="user-tel">
	              <img src="../storage/img/ckIcon.png" alt="check-icon">
	            </div>
	            <div class="phone-text"></div>
	          </div>
	          <div class="license-wrap">
	            <label for="user-license">인증번호</label>
	            <div class="license-icon-wrap">
	              <input id="user-license" type="text" disabled>
	              <div class="license-btn-wrap">
	                <img src="../storage/img/ckIcon.png" alt="check-icon" class="hidden">
	                <button type="button" class="send-license">인증번호 전송</button>
	                <button type="button" class="ck-license hidden">인증번호 확인</button>
	              </div>
	            </div>
	            <div class="license-text">문자 발송 했다!!</div>
	          </div>
	          <div class="job-wrap">
	            <div class="label">직무</div>
	            <div class="user-job">${userInfo.getMember_job() }</div>
	          </div>
	          <div class="email-wrap">
	            <label for="user-email">이메일</label>
	            <div class="email-icon-wrap">
	              <input id="user-email" type="email" value="${userInfo.getMember_email()}" disabled name="user-email">
	              <img src="../storage/img/ckIcon.png" alt="check-icon">
	            </div>
	            <div class="email-text">이메일 입력해라!</div>
	          </div>
	        </form>
	        <button class="my-frm-btn" form="my-frm" type="button">정보수정</button>
	      </div>
	    </section>
	
	</main>

</div>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/my-page.js"></script>
</body>
</html>