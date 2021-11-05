<%@page import="vo.PageVO"%>
<%@page import="vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board-list.css">
</head>
<%
	List<BoardVO> Emer = (List<BoardVO>) request.getAttribute("BoardList1"); //긴급
	List<BoardVO> Bulletin = (List<BoardVO>) request.getAttribute("BoardList2"); //공지 
	List<BoardVO> Free = (List<BoardVO>) request.getAttribute("BoardList3"); //자유
	String path = (String)request.getAttribute("path");
// 	//페이징을 위한 변수 선언
// 	int totalRecord = 0; // 전체 글의 갯수
// 	int numPerPage = 15; // 한 페이지당 보여질 글의 갯수
// 	int pagePerBlock = 3; // 한 블럭당 묶여질 페이지 수
// 	int totalPage = 0; // 전체 페이지 수
// 	int totalBlock = 0; //전체 블럭 수
// 	int nowPage = 0; //현재 보여질 페이지
// 	int nowBlock = 0; //현재 보여질 블럭
// 	int beginPerPage = 0; //각 페이지의 시작 글 번호

%>
<body>
<div class="body__container">
	<jsp:include page="/WEB-INF/views/main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>	
	<main>
		<article class="board-list-wrap">

        <div class="board-head">
          <form id="search-board-frm" action="<%=request.getContextPath() %>/board" method="get">
            <input name="searchDa" type="text" id="searchData" >
            <input name="cmd" type="hidden" value="searchData" >
            <button type="button" id = "searchBtn">검색</button>
          </form>
        </div>
  
        <div class="board-body">
          <div class="board-page-wrap">
            
            <ul>
            	<li>Page</li>
            	<%  PageVO pageVO = (PageVO)request.getAttribute("pageVO"); 
            		int pagination = pageVO.getPaginationSize();
            		int pageD = pageVO.getPage();
            		
            	if(pageD != 1){
            	 %>
            	<li><a href="<%=request.getContextPath() %>/board?cmd=<%=path %>&page=<%=pageD-1%>">«</a></li>
            	<%
            	}
            	for(int i = 1; i <= pagination; i++){ 
            		if(pageD == i){
            	%>
            		
            		<li class="page-click"><a href="<%=request.getContextPath() %>/board?cmd=<%=path %>&page=<%=i%>&searchDa=<%=request.getParameter("searchDa") %>"><%=i%></a></li>
              	<%
            		}else{
            	%>		
            		<li><a href="<%=request.getContextPath() %>/board?cmd=<%=path %>&page=<%=i%>&searchDa=<%=request.getParameter("searchDa") %>"><span class= "pageIdx"><%=i%></span></a></li>
            	<% 		
            		}
              	}
            	%>
            	<% 
            	if(pageD != pagination){
            	%>
            	<li><a href="<%=request.getContextPath() %>/board?cmd=<%=path %>&page=<%=pageD+1%>&searchDa=<%=request.getParameter("searchDa") %>">»</a></li>
            	<%
            	}
            	%>
            </ul>
            <div class="go-to-write">
	            <a href="<%=request.getContextPath() %>/board?cmd=write">글 작성</a>            
            </div>
          </div>


          <div class="board-list">
            <ul class="list-header">
              <li>구분</li>
              <li>제목</li>
              <li>작성자</li>
              <li>작성일</li>
              <li>조회수</li>
            </ul>

            <ul class="list-body">
            
            <%
            for(BoardVO bvo : Bulletin){
            %>
              <li class="notice-item">
                <span class="division"><%=bvo.getBoard_category()%></span>
                <div class="title">
	                <a href="<%=request.getContextPath() %>/board?cmd=detail&idx=<%=bvo.getBoard_idx() %>"><%=bvo.getBoard_title() %></a>                
                </div>
                <span class="author"><%=bvo.getMember_name() %></span>
                <span class="date"><%=bvo.getBoard_date() %></span>
                <span class="hit-count"><%=bvo.getBoard_hits() %></span>
              </li>             
             <%
            		}
             %>
            
            <%
            for(BoardVO bvo : Emer){
            %>
              <li class="press-item">
                <span class="division"><%=bvo.getBoard_category()%></span>
                <div class="title">
	                <a href="<%=request.getContextPath() %>/board?cmd=detail&idx=<%=bvo.getBoard_idx() %>"><%=bvo.getBoard_title() %></a>           
                </div>
                <span class="author"><%=bvo.getMember_name() %></span>
                <span class="date"><%=bvo.getBoard_date() %></span>
                <span class="hit-count"><%=bvo.getBoard_hits() %></span>
              </li>             
             <%
             		} 
             %>
             
            <%
            for(BoardVO bvo : Free){
            %>
              <li class="normal-item">
                <span class="division"><%=bvo.getBoard_category()%></span>
                <div class="title">
	                <a href="<%=request.getContextPath() %>/board?cmd=detail&idx=<%=bvo.getBoard_idx() %>"><%=bvo.getBoard_title() %></a>         
                </div>
                <span class="author"><%=bvo.getMember_name()%></span>
                <span class="date"><%=bvo.getBoard_date() %></span>
                <span class="hit-count"><%=bvo.getBoard_hits() %></span>
              </li>             
             <%
             		} 
             %>
             
<!--              <li class="press-item"> 긴급 -->
<!--              <li class="notice-item"> 공지 -->
<!--              <li class="normal-item"> 자유 -->
         </ul>
          </div>
        </div>
      </article>
	</main>
</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script>

	$(function(){
		$("#searchBtn").on("click", function(){
			$('#search-board-frm').submit();
		})			
	});		
	
	



</script>
</body>
</html>