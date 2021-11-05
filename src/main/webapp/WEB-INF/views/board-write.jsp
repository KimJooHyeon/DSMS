<%@page import="java.util.List"%>
<%@page import="vo.FileVO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
	BoardVO boardInfo = (BoardVO) request.getAttribute("boardInfo");
	%>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/board-write.css">
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
	
	<div class="body__container">
		<jsp:include page="/WEB-INF/views/main-nav.jsp" flush="false">
	  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
	  	</jsp:include>
		<main>
			<article class="board-write-wrap">
				<form id="write-wrap-frm" method="post"
					action="<%=request.getContextPath() %>/board?cmd=write" enctype="multipart/form-data">
					<ul class="top">
						<li class="title-wrap">
							<input type="text" class="title" name="board_title" placeholder="제목을 입력하세요.">
							<button type="button" class="write-btn">작성</button>
						</li>
						<li class="description">
							<div class="radio-wrap">
								<label>
									<input type="radio" name="board_category" value="2">공지</label>
								<label>
									<input type="radio" name="board_category" value="1">긴급</label>
								<label><input type="radio" name="board_category" value="3">자유</label>
							</div> 
							<input type="file" name="profile" class="file">
						</li>
					</ul>
					<div id="editor"></div>
					<input type="hidden" name="board_content" id="board_content"> 
					<!-- value값 session에서 넣기 -->
					<input type="hidden" name="member_idx" id="member_idx" value="${user.getMember_idx()}">
				</form>
			</article>
		</main>
	</div>

	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/global.js"></script>
	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
	<script>
		const Editor = toastui.Editor;

		const editor = new Editor({
			el : document.querySelector('#editor'),
			height : '480px',
			initialEditType : 'wysiwyg',
			previewStyle : 'vertical'
		});
		
		$(function() {
			$(".write-btn").on("click", function() { //작성을 눌렀을때
				if($("input[name='board_category']:checked").length==0){
					alert('카테고리를 체크하세요');
					return false;
				}
				
				$('#board_content').val(editor.getMarkdown());
				$("#write-wrap-frm").submit();
			})
		});
		
		
		$(function(){
			
		    $("#toggle_id").bind("click", function(){

		        if($("#toggle_id").val()==0){
		            $("#toggle_id").val(1); 
		        }

		        else{
		            $("#toggle_id").val(0);
		        }
		        
		        
		        var isHide=$("#toggle_id").val();

		        if(isHide==1){
		            // Button 숨기기
		            $("#admin_id").hide();
		        }

		        else{
		            // Button 보이기
		            $("#admin_id").show();
		        }

		    });

		});

	</script>
</body>
</html>