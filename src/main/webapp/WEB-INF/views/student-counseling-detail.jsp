<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>

<link rel="stylesheet" href="../css/student-couseling-detail.css" />
</head>
<body>

<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<section class="list">
			<input type="text" placeholder="학생 검색"/>
			<ul>
			<c:forEach items="${studentList}" var="item">
				<li data-idx="${item.student_idx}">${item.student_name}</li>
			</c:forEach>
			</ul>
		</section>
	
		<section class="detail">
			<div class="top">
				<div class="title">${detail.counseling_title}</div>
				<div class="date">상담 일자 : ${detail.counseling_date.substring(0, 10)}</div>
				<div class="student">
					<span class="name">학생명 : ${detail.student_name }</span>
					<div class="wrap">
						<a href="/student?cmd=counselModify&idx=${detail.counseling_idx}">수정</a>
						<button type="button" data-idx="${detail.counseling_idx}">삭제</button>					
					</div>
				</div>
			</div>
			<div class="description">
				${detail.counseling_content }
			</div>
		</section>
	</main>

</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/student-counseling-detail.js"></script>
</body>
</html>