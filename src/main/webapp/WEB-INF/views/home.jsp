<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>대덕 인재 개발원</title>

<link rel="stylesheet"
	href="../util/fullCalendar/vendor/css/fullcalendar.min.css" />
<link rel="stylesheet"
	href="../util/fullCalendar/vendor/css/bootstrap.min.css">
<link rel="stylesheet"
	href='../util/fullCalendar/vendor/css/select2.min.css' />
<link rel="stylesheet"
	href='../util/fullCalendar/vendor/css/bootstrap-datetimepicker.min.css' />

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans:400,500,600">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="../util/fullCalendar/css/main.css">
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/home.css">
</head>
<body>
	<div class="body__container">

		<jsp:include page="main-nav.jsp" flush="false">
			<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
		</jsp:include>

		<main>
			<section class="home-section">

				<article class="latest-posts-wrap">
					<div class="inner">
						<h2>최신글</h2>
						<ul class="posts-list">
<!-- 							<li class="list-item"> -->
<!-- 								<a class="item-link" href="/">  -->
<!-- 									<span class="item-div">공지</span> -->
<!-- 									<span class="item-title">디자인하기가 정말로 매우싫어요 언제까지 퍼블리싱을 해야되는 걸까요?...</span> -->
<!-- 									<span class="item-author">원장님</span> -->
<!-- 									<span class="item-date">2021.01.01 11:11</span> -->
<!-- 								</a> -->
<!-- 							</li> -->
								<c:forEach items="${post}" var="item">
								<li class="list-item">
									<a class="item-link" href="/board?cmd=detail&idx=${item.board_idx}">
										<c:choose>
											<c:when test="${item.board_category eq '공지'}">										
												<span class="item-div notice">${item.board_category}</span>										
											</c:when>
											<c:when test="${item.board_category eq '긴급'}">										
												<span class="item-div press">${item.board_category}</span>										
											</c:when>
											<c:otherwise>
												<span class="item-div normal">${item.board_idx}</span>										
											</c:otherwise>
										</c:choose>
										
										<span class="item-title">${item.board_title}</span>
										<span class="item-author">${item.member_name}</span>
										<span class="item-date">${item.board_date.substring(0, 11)}</span>
									</a>
								</li>
								</c:forEach>
						</ul>
					</div>
				</article>

				<article class="area-rank-wrap">
					<div class="inner">
						<h2>취업 지역 랭크</h2>
						<div class="chart">
							<ul class="bar">
								<c:forEach items="${rank}" var="entry">
									<li class="bar-item" style="height: ${entry.per}%;">${entry.cnt}</li>
								</c:forEach>
							</ul>
							<ul class="name">
								<c:forEach items="${rank}" var="entry">
									<li class="area-name">${entry.fiveRankCity}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</article>

				<article class="now-time-wrap">
					<div id="clock" class="light">
						<div class="display">
							<div class="weekdays"></div>
							<div class="ampm"></div>
							<div class="digits"></div>
						</div>
					</div>
				</article>

				<article class="this-week-schedule-wrap">
					<div class="inner">
						<h2>이번 주 일정</h2>
						<div class="schedule">
							<div id="calendar"></div>
						</div>
					</div>
				</article>
			</section>
		</main>

	</div>



	<script src="../util/fullCalendar/vendor/js/jquery.min.js"></script>
	<script src="../util/fullCalendar/vendor/js/bootstrap.min.js"></script>
	<script src="../util/fullCalendar/vendor/js/moment.min.js"></script>
	<script src="../util/fullCalendar/vendor/js/fullcalendar.min.js"></script>
	<script src="../util/fullCalendar/vendor/js/ko.js"></script>
	<script src="../util/fullCalendar/vendor/js/select2.min.js"></script>
	<script
		src="../util/fullCalendar/vendor/js/bootstrap-datetimepicker.min.js"></script>
<!-- 	<script src="../util/fullCalendar/js/main.js"></script> -->
	<script src="../js/global.js"></script>
	<script src="../js/home.js"></script>
	<script src="../util/fullCalendar/js/addEvent.js"></script>
	<script src="../util/fullCalendar/js/editEvent.js"></script>
	<script src="../util/fullCalendar/js/etcSetting.js"></script>
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>