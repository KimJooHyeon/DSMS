<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/student-check-in.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
	
		<section class="list">
			<input id="search-student" type="text" placeholder="학생 검색"/>
<!-- 		<li class="clicked">- 유영진</li> -->
			<ul>
			<c:forEach items="${studentList}" var="item">
				<li data-idx="${item.getStudent_idx()}">- ${item.getStudent_name() }</li>
			</c:forEach>
			</ul>
		</section>
	
		<section class="detail">
		
			
			<div id="recruitGrid"></div>
			<button class="modify-btn hidden" type="button">수정</button>
		
		</section>
	
	</main>

</div>

<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script>
const Grid = tui.Grid;  //인스턴스 객체 생성
</script>
<script type="text/javascript" src="../js/student-check-in.js"></script>
</body>
</html>