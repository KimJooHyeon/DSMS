<%@page import="vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/student-counseling-list.css" />
</head>
<body>

<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<section class="list">
			<input id="search-student" type="text" placeholder="학생 검색"/>
			<ul>
			<c:forEach items="${studentList}" var="item">
				<li data-idx="${item.student_idx}">${item.student_name}</li>
			</c:forEach>
			</ul>
		</section>
		
		<section class="detail">
			<div class="inner">
				<ul class="top">
					<li>제목</li>
					<li>작성일</li>
				</ul>
				
				<ul class="items">
<!-- 					<li> -->
<!-- 						<a href="./student-counseling-detail.jsp"> -->
<!-- 							<span>1차 상담</span> -->
<!-- 							<span>2021.01.01</span>						 -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<a href="/"> -->
<!-- 							<span>2차 상담</span> -->
<!-- 							<span>2021.01.02</span>						 -->
<!-- 						</a> -->
<!-- 					</li> -->


				</ul>
				<a class="write-btn" href="#">상담 일지 작성</a>
			</div>
		</section>
	</main>

</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/student-counseling-list.js"></script>
</body>
</html>