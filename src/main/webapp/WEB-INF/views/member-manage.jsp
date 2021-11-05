<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/member-manage.css">
</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
	</jsp:include>

	<main>
		<section>
	 		  <form id="frm" action=""></form>
			  <div class="table-wrap">
			    <button type="button">수정</button>
			    <table border="1">
			      <thead>
			        <tr>
			          <th><input id="allCheck" type="checkbox"></th>
			          <th>이름</th>
			          <th>전화번호</th>
			          <th>이메일</th>
			          <th>직무구분</th>
			          <th>가입 상태</th>
			        </tr>
			      </thead>
			      <tbody>
<!-- 			        <tr> -->
<!-- 			          <td><input form="frm" value="idx" type="checkbox" name="idx"></td> -->
<!-- 			          <td>안대근</td> -->
<!-- 			          <td>010-4063-4264</td> -->
<!-- 			          <td>adg0807@naver.com</td> -->
<!-- 			          <td>교사</td> -->
<!-- 			          <td> -->
<!-- 			            <select form="frm" name="state"> -->
<!-- 			              <option value="N">N</option> -->
<!-- 			              <option value="Y" selected>Y</option> -->
<!-- 			            </select> -->
<!-- 			          </td> -->
<!-- 			        </tr> -->
			        
			        <c:forEach items="${list}" var="item">
			        <tr>
			          <td><input form="frm" value="${item.member_idx}" type="checkbox" name="idx"></td>
			          <td>${item.member_name}</td>
			          <td>${item.member_tel}</td>
			          <td>${item.member_email}</td>
			          <c:choose>
						<c:when test="${item.member_job eq '1'}">
				          <td>교사</td>
						</c:when>			          
						<c:when test="${item.member_job eq '2'}">
				          <td>행정</td>
						</c:when>			          
			          </c:choose>
			          <td>
			            <select form="frm" name="state">
			              <c:choose>
			              	 <c:when test="${item.member_state eq 'N'}">
					              <option value="N" selected>N</option>
					              <option value="Y">Y</option>			              	 
			              	 </c:when>
			              	 <c:when test="${item.member_state eq 'Y'}">
					              <option value="N">N</option>
					              <option value="Y" selected>Y</option>			              	 
			              	 </c:when>
			              </c:choose>
			            </select>
			          </td>
			        </tr>
			        </c:forEach>
			        
			      </tbody>
			    </table>
			  </div>
		</section>
	</main>
</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/member-manage.js"></script>
</body>
</html>