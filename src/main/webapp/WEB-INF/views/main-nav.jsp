<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String job = request.getParameter("job") + "";

if(session.getAttribute("user") == null) {%>
<script>
alert('로그인 후 이용해 주세요')
location.href = 'index.jsp'
</script>	
<%}%>


<nav>
   <ul>
     <li class="logo"><img src="../storage/img/Logo.png" alt="logo"></li>
     <li class="home-nav"><a href="/home?cmd=index">홈</a></li>
     <li class="board-nav"><a href="/board?cmd=selectList.do">게시판</a></li>
     <li class="manage-nav">
	  <c:if test="${param.job eq '1' }">
	       <button type="button" class="student-open">학생관리</button>
	       <ul class="student-nav-wrap nav-hidden">
	         <li class="student-info-nav"><a href="/student?cmd=info&roomNumber=301">학생 정보</a></li>
	         <li class="student-attend-nav"><a href="/student?cmd=checkIn">학생 출결</a></li>
	         <li class="student-cons-nav"><a href="/student?cmd=cons">상담 일지</a></li>
	       </ul>
	   </c:if>
       <c:if test="${param.job eq '2' }">
	       <button type="button" class="admin-open">행정관리</button>
	       <ul class="admin-nav-wrap nav-hidden">
	         <li class="applicant-nav"><a href="/applicant?cmd=start">면접 지원자</a></li>
	         <li class="company-nav"><a href="/company?cmd=notice">공고 회사</a></li>
	         <li class="attendance-nav"><a href="/student?cmd=attend">출결 조회</a></li>
	         <li class="room-nav"><a href="/student?cmd=classRoomSet">반 배정</a></li>
<!-- 	         <li class="room-list-nav"><a href="class-room-list.jsp">반별 현황</a></li> -->
	       </ul>
       </c:if>
       <c:if test="${param.job eq '0' }">
       	   <button type="button" class="student-open">학생관리</button>
	       <ul class="student-nav-wrap nav-hidden">
	         <li class="student-info-nav"><a href="/student?cmd=index">학생 정보</a></li>
	         <li class="student-attend-nav"><a href="/student?cmd=checkIn">학생 출결</a></li>
	         <li class="student-cons-nav"><a href="/student?cmd=cons">상담 일지</a></li>
	       </ul>
	       <button type="button" class="admin-open">행정관리</button>
	       <ul class="admin-nav-wrap nav-hidden">
	         <li class="applicant-nav"><a href="/applicant?cmd=start">면접 지원자</a></li>
	         <li class="company-nav"><a href="/company?cmd=notice">공고 회사</a></li>
	         <li class="attendance-nav"><a href="/student?cmd=attend">출결 조회</a></li>
	         <li class="room-nav"><a href="/student?cmd=classRoomSet">반 배정</a></li>
<!-- 	         <li class="room-list-nav"><a href="/student?cmd=classRoomInfo">반별 현황</a></li> -->
	       </ul>
       </c:if>
     </li>
     <li class="schedule-nav"><a href="/schedule?cmd=index">일정 관리</a></li>
     <li class="employ-nav"><a href="/company?cmd=map">취업 분포도</a></li>
     <li class="chat-nav"><a href="/chat?cmd=chatting">채팅</a></li>
     <li class="my-nav"><a href="/member?cmd=myPage.do">마이페이지</a></li>
     <c:if test="${param.job eq '0'}">
     <li class="manage-nav"><a href="/member?cmd=memberManage">직원 관리</a></li>
     </c:if>
   </ul>
   <a class="logout" href="/member?cmd=logout">로그아웃</a>
 </nav>