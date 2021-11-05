<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/board-modify.css">
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
	<%
	BoardVO boardInfo = (BoardVO) request.getAttribute("boardInfo");
	%>
<div class="body__container">

	<jsp:include page="/WEB-INF/views/main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>

<main>
	<article class="board-modify-wrap">
        <form id="modify-wrap-frm" method="post" 
        	action="<%=request.getContextPath() %>/board?cmd=update">
          <ul class="top">
            <li class="title-wrap">
              <input type="text" class="title" name="board_title" value="<%=boardInfo.getBoard_title() %>" placeholder="제목을 입력하세요.">
              <button type="button" class="modify-btn">수정</button>
            </li>
            <li class="description">
              <div class="radio-wrap">
	              <label><input type="radio" name="board_category" value="2">공지</label>
	              <label><input type="radio" name="board_category" value="1">긴급</label>
	              <label><input type="radio" name="board_category" value="3">일반</label>
              </div>
              <input type="file" class="file">
            </li>
          </ul>
          <div id="editor"></div>
          <input type="hidden" name="board_content" id="board_content" value="<%=boardInfo.getBoard_content() %>">
          <input type="hidden" name="member_idx" id="member_idx" value="<%=boardInfo.getMember_idx() %>">
          <input type="hidden" name="board_idx" id="board_idx" value="<%=boardInfo.getBoard_idx() %>">
        </form>
      </article>
</main>

</div>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script 
	src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js">
</script>

<script>
const Editor = toastui.Editor;
debugger;
const editor = new Editor({
	  el: document.querySelector('#editor'),
	  height: '480px',
	  initialEditType: 'wysiwyg',
	  initialValue : document.querySelector('#board_content').value,
	  previewStyle: 'vertical'
});

$(function(){
	$(".modify-btn").on("click", function(){
		console.log($('#board_content').val());
		console.log(editor.getHTML());
		console.log(editor.getMarkdown());
		if($("input[name='board_category']:checked").length==0){
			alert('카테고리를 체크하세요');
			return false;
		}
		$('#board_content').val(editor.getMarkdown());
		$('#modify-wrap-frm').submit();
	})			
});	



</script>
</body>
</html>