<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/student-counseling-modify.css" />
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
				<li data-idx="${item.student_idx}">${item.student_name}</li>
			</c:forEach>
			</ul>
		</section>
	
		<section class="detail">
			<form class="top" action="" method="post">
				<input type="text" class="title" value="${detail.counseling_title}"/>
				<label>학생명 : <input class="student-name" type="text" readonly value="${detail.student_name}"/></label>
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
editor.setHTML(`${detail.counseling_content}`);

$('.write-btn').on('click', function() {
	const _data = {
		idx: `${detail.counseling_idx}`,
		title: $('.title').val(),
		name: $('.student-name').val(),
		contents: editor.getHTML()
	}
	$.ajax({
		url: '/student?cmd=counselModifyDo',
		data: _data,
		success: function(res) {
			if(res.trim() === 'true') {
				alert('수정 완료')
				location.replace('/student?cmd=cons')
			} else {
				alert('다시 작성해 주세요')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})
</script>
</body>
</html>