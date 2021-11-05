<%@page import="vo.FileVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="vo.CommentVO"%>
<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BoardVO boardInfo = (BoardVO) request.getAttribute("boardInfo");
List<Map<String, Object>> commentList = (List<Map<String, Object>>) request.getAttribute("commentList");
FileVO fileVo = new FileVO();
if ((FileVO) request.getAttribute("file") != null) {
	fileVo = (FileVO) request.getAttribute("file");
} ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/board-detail.css">
</head>

<body>
	<div class="body__container">
		<jsp:include page="/WEB-INF/views/main-nav.jsp" flush="false">
			<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
		</jsp:include>

		<main>

			<article class="board-detail-wrap">
				<div class="inner">

					<ul class="top">
						<li class="title-wrap">
							<div class="title"><%=boardInfo.getBoard_title()%></div>
							<div class="title-btn-wrap">
								<button class="modify-btn"
									onclick="location.href='<%=request.getContextPath()%>/board?cmd=modify&idx=<%=boardInfo.getBoard_idx()%>'">수정</button>

								<button class="delete-btn"
									onclick="location.href='<%=request.getContextPath()%>/board?cmd=delete&idx=<%=boardInfo.getBoard_idx()%>'">삭제</button>
								<input type="hidden" name="board_idx" id="board_idx"
									value="<%=boardInfo.getBoard_idx()%>"> <input
									type="hidden" id="login_mem_job"
									value="${user.getMember_job()}"> <input type="hidden"
									id="login_mem_idx" value="${user.getMember_idx()}">
							</div>
						</li>
						<li class="description"><span class="author"><%=boardInfo.getMember_name()%></span>
							<span class="date"><%=boardInfo.getBoard_date()%></span> <span
							class="hit-count">조회수 <span class="count"><%=boardInfo.getBoard_hits()%></span></span>
							<%
							if ((FileVO) request.getAttribute("file") != null) {
							%> <span
							class="file"><a
								href="<%=request.getContextPath()%>/board?cmd=file_download&idx=<%=boardInfo.getBoard_idx()%>"><%=fileVo.getFile_name()%><%=fileVo.getFile_type()%></a></span>
							<%
							}
							%></li>
					</ul>

					<div class="content">
						<%=boardInfo.getBoard_content()%>
					</div>

					<div class="comment">
						<form action="" id="insertComment-frm">
							<textarea id="commentContent"></textarea>
							<button type="button" class="writeComment-btn" onclick="">입력</button>
						</form>
						<br>
						<ul class="comment-list">
							<%
							for (int i = 0; i < commentList.size(); i++) {
							%>
							<li class="comment-item">
								<div class="info">
									<span class="author"><%=commentList.get(i).get("MEMBER_NAME")%>
										<span class="date"><%=commentList.get(i).get("COMMENT_DATE")%></span></span>
									<div class="info-btns">
										<button type="button" class="modify-btn" onclick="">수정</button>
										<button type="button" id="deleteComment" class="delete-btn">삭제</button>
										<input type="hidden" id="comment_idx"
											value="<%=commentList.get(i).get("COMMENT_IDX")%>">
									</div>
								</div>


								<div class="content">
									<sapn id="comment_content_btn"> <%=commentList.get(i).get("COMMENT_CONTENT")%>
									</sapn>

									<div id="comment_div" style="display: none;">
										<textarea id="updateContent"><%=commentList.get(i).get("COMMENT_CONTENT")%></textarea>
										<div class="modify-btn-wrap">
											<button type="button" class="comment_update_btn" onclick="">수정</button>
											<button type="button" class="comment_cancel_btn">취소</button>										
										</div>
									</div>
								</div>
							</li>
							<%
							}
							%>
						</ul>
					</div>
			</article>
		</main>

	</div>

	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/global.js"></script>
	<script type="text/javascript" src="../js/board-detail.js"></script>
	<script>
		const loginMemJob = document.querySelector('#login_mem_job').value;
		const loginMemIdx = parseInt(document.querySelector('#login_mem_idx').value);
		const boardMemIdx = parseInt(<%=boardInfo.getMember_idx()%>);

		//관리자가 아니면서 본인이 작성한 게시물이 아닌 경우
		if (loginMemJob !== '0' && boardMemIdx !== loginMemIdx) {
			document.querySelector('.modify-btn').style.visibility = 'hidden';
			document.querySelector('.delete-btn').style.visibility = 'hidden';
			
			//관리자면서 본인이 작성한 게시물이 아닌 경우
		} else if (loginMemJob == '0' && boardMemIdx != loginMemIdx) {
			document.querySelector('.modify-btn').style.visibility = 'hidden';
		} else {
			document.querySelector('.modify-btn').style.visibility = 'visible';
			document.querySelector('.delete-btn').style.visibility = 'visible';
		}

		$(".writeComment-btn").on("click", function() { //댓글 작성 눌렀을때
			const param = {
				comment_content : $("#commentContent").val(),
				board_idx : $("#board_idx").val(),
				member_idx : $("#login_mem_idx").val()
			};

			$.ajax({
				type : "POST",
				url : '/board?cmd=commentWrite',
				dataType : 'json',
				data : param,
				success : function(res) {
					if(res.board_idx !== ''){
						let code = '<li class="comment-item">'
						code += '<div class="info">'
						code += '<span class="author">' + res.member_name
						code += '<span class="date">' + res.comment_date.substring(0, 11) + '</span></span>'
						code += '<div class="info-btns">'
						code += '<button type="button" class="modify-btn" onclick="">수정</button>'
						code += '<button type="button" id="deleteComment" class="delete-btn">삭제</button>'
						code += '<input type="hidden" id="comment_idx" value="' + res.member_idx + '">'
						code += '</div>'
						code += '</div>'
						code += '<div class="content">'
						code += '<sapn id="comment_content_btn">' + res.comment_content
						code += '</span>'
						code += '<div id="comment_div" style="display: none;">'
						code += '<textarea id="updateContent">' + res.comment_content + '</textarea>'
						code += '<button type="button" id="comment_update_btn" onclick="">입력</button>'
						code += '<button type="button" id="comment_cancel_btn">취소</button>'
						code += '</div></div></div>'
						code += '</li>'
						$('.comment-list').append(code)
						$('#commentContent').val("")
					}
				},
				error : function(e) {
					console.log(e)
				},

			})
			
		})
		

		$("#deleteComment").on("click", function(e) {
			const param2 = {
				board_idx : $("#board_idx").val(),
				comment_idx : $("#comment_idx").val()
			};
			console.log(param2)
			
			$.ajax({
				type : "GET",
				url : "/board?cmd=deleteComment",
				data : param2,
				success : function(res) {
					alert("댓글 삭제완료!");
					location.reload();

				},
				error : function(e) {
					console.log(e)
				},
			})
		})

		$(".modify-btn").on("click", function() { //수정눌렀을때 
			$("#comment_content_btn").hide();
			$("#comment_div").show();
			$(".modify-btn").hide();
			$("#deleteComment").hide();
		})

 		$('.comment_update_btn').on("click", function(e) { //댓글 수정을 눌렀을때
			$("#comment_content_btn").show();
			$("#comment_div").hide();
			const param3 = {
				comment_content : $("#updateContent").val(),
				board_idx : $("#board_idx").val(),
				comment_idx : $("#comment_idx").val()
			};

			$.ajax({
				type : "POST",
				url : '/board?cmd=modifyCommnet',
				//dataType : 'json' 
				data : param3,
				success : function(res) {
					const text = param3.comment_content
					$("#comment_content_btn").html(text)
					$("#comment_content_btn").show();
					$("#comment_div").hide();
					$(".modify-btn").show();
					$("#deleteComment").show();
				},
				error : function(e) {
					console.log(e)
				},

			})

		});

		$(".comment_cancel_btn").on("click", function() { //댓글 취소를 눌렀을때
			$("#comment_content_btn").show();
			$("#comment_div").hide();
			$(".modify-btn").show();
			$("#deleteComment").show();
		});
	</script>

</body>
</html>