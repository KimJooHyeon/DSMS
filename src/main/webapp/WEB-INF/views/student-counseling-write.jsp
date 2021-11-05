<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/student-counseling-write.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
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
				<c:if test="${item.student_idx eq student.student_idx}">
					<li class="clicked" data-idx="${item.student_idx}">${item.student_name}</li>
				</c:if>
				<c:if test="${item.student_idx ne student.student_idx }">
					<li data-idx="${item.student_idx}">${item.student_name}</li>
				</c:if>
			</c:forEach>
			</ul>
		</section>
	
		<section class="detail">
		
			<form class="top" action="/" method="post">
				<input type="text" class="title" placeholder="상담 일지 제목"/>
				<input id="idx" type="hidden" value="${student.student_idx}" />
				<label>학생명 : <input class="student-name" type="text" readonly value="${student.student_name}"/></label>
			</form>
			<div id="editor"></div>
			<button class="write-btn" type="button">작성</button>
		</section>
	
	</main>


</div>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
const Editor = toastui.Editor;

const editor = new Editor({
	  el: document.querySelector('#editor'),
	  height: '480px',
	  initialEditType: 'wysiwyg',
	  previewStyle: 'vertical'
	});
</script>
<script type="text/javascript" src="../js/student-counseling-write.js"></script>
</body>
</html>