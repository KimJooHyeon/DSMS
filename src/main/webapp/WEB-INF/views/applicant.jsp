<%@page import="vo.MemberVO"%>
<%@page import="vo.ApplicantVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<ApplicantVO> list = (List<ApplicantVO>) request.getAttribute("applicantList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/applicant.css" />
<link rel="stylesheet"
	href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
</head>
<body>
	<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>

		<main>
			<div class="btn-wrap">
				<button type="button" id="insert">추가</button>
				<button type="button" id="delete">삭제</button>
        		<button type="button" id="update">저장</button>
			</div>
			<div class="grid-wrap">
				<div id="recruitGrid"></div>
			</div>
		</main>

	</div>

	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/global.js"></script>
	<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
	<script>



const Grid = tui.Grid;  //인스턴스 객체 생성
let recruitGrid;
let recruitData = [
<%for (ApplicantVO vo : list) {%>
	{
	name: "<%=vo.getApplicant_name()%>",
    age: "<%=vo.getApplicant_age()%>",
    tel: "<%=vo.getApplicant_tel()%>",
    score: "<%=vo.getApplicant_score()%>",
    pass: "<%=vo.getApplicant_pass()%>"
  	},
<%}%>
]


recruitGrid = new Grid({
  el: document.getElementById('recruitGrid'),
  scrollX: true,
  rowHeaders: ['checkbox'],
  columns: [

    {
      header: '학생명',
      name: 'name',
      editor: 'text',
      align: 'center'
    },
    {
      header: '나이',
      name: 'age',
      editor: 'text',
      align: 'center'
    },
    {
      header: '전화번호',
      name: 'tel',
      editor: 'text',
      align: 'center'
    },
    {
      header: '면접 점수',
      name: 'score',
      editor: 'text',
      align: 'center'
    },
    {
      header: '합격 여부',
      name: 'pass',
      editor: 'text',
      align: 'center'
    }
  ],
  data: recruitData
});
//----------------------------------------------------------------
let isEnd = false;
    
    $(function(){
        $(window).scroll(function(){
            let $window = $(this);
            let scrollTop = $window.scrollTop();
            let windowHeight = $window.height();
            let documentHeight = $(document).height();
			//console.log("documentHeight:" + documentHeight + " | scrollTop:" + scrollTop + " | windowHeight: " + windowHeight );
            if( scrollTop + windowHeight + 30 > documentHeight ){
                fetchList();
            }
        })
        fetchList();
    })
    
    let fetchList = function(){
        if(isEnd == true){
            return;
        }

        let startNo = $("#recruitGrid").last().data("no") || 0;
        $.ajax({
            url:"/applicant" +startNo,
            type: "GET",
//             data: {
//             	page : startNo
//             }
            dataType: "json",
            success: function(result){

                let length = result.data.length;
                if( length < 5 ){
                    isEnd = true;
                }
                $.each(result.data, function(index, vo){
                    renderList(false, vo);
                })
            }
        });
    }
    
    let renderList = function(mode, vo){
        // 
        let html = "<li data-no='"+ vo.no +"'>" +
            "<strong>"+ vo.name +"</strong>" +
            "<p>"+ vo.content.replace(/\n/gi, "<br>") +"</p>" +
            "<strong></strong>" +
            "<a href='#' data-no='"+ vo.no +"'>삭제</a>" +
            "</li>"
        
        if( mode ){
            $("#recruitGrid").prepend(html);
            console.log(html);
        }
        else{
            $("#recruitGrid").append(html);
        }
    }

    $("#insert").click(function(){
    	$('#recruitGrid').html("")
    	recruitData = [
    		{
       			name: "",
       		    age: "",
       		    tel: "",
       		    score: "",
       		    pass: ""
        	},
    		<%for (ApplicantVO vo : list) {%>
    			{
    			name: "<%=vo.getApplicant_name()%>",
    		    age: "<%=vo.getApplicant_age()%>",
    		    tel: "<%=vo.getApplicant_tel()%>",
    		    score: "<%=vo.getApplicant_score()%>",
    		    pass: "<%=vo.getApplicant_pass()%>"
    		  	},
    		<%}%>
    	]
    	
    	recruitGrid = new Grid({
    		  el: document.getElementById('recruitGrid'),
    		  scrollX: true,
    		  rowHeaders: ['checkbox'],
    		  columns: [
    		    {
    		      header: '학생명',
    		      name: 'name',
    		      editor: 'text',
    		      align: 'center'
    		    },
    		    {
    		      header: '나이',
    		      name: 'age',
    		      editor: 'text',
    		      align: 'center'
    		    },
    		    {
    		      header: '전화번호',
    		      name: 'tel',
    		      editor: 'text',
    		      align: 'center'
    		    },
    		    {
    		      header: '면접 점수',
    		      name: 'score',
    		      editor: 'text',
    		      align: 'center'
    		    },
    		    {
    		      header: '합격 여부',
    		      name: 'pass',
    		      editor: 'text',
    		      align: 'center'
    		    }
    		  ],
    		  data: recruitData
    		});
    })
    $("#update").click(function() {
    	const { rowKey, columnName } = recruitGrid.getFocusedCell();
    	if (rowKey && columnName) {
    		recruitGrid.finishEditing(rowKey, columnName);
    	}
    	const { updatedRows } = recruitGrid.getModifiedRows();
      
    	for(const str of updatedRows) {
    		delete str.rowKey
    		delete str._attributes
    	console.log(JSON.stringify(updatedRows))
    	
    	}

    $.ajax({
      url:'/applicant?cmd=add',
      method: 'POST',
      data: {
    	  addJson :JSON.stringify(updatedRows),
      },
      success: function(res){
		console.log(res)
		
		if(res.trim() === 'true') {
			alert('성공')
			location.reload()
		} else {
			alert('실패')
		}
      },
      error: function(e) {
    	  console.log(e)
      }

  	  })
    })
    $('#delete').click(function(){
        const selectedValues   = recruitGrid.getValue(recruitGrid.getCheckedRowKeys(), "name");
    	const deleteRows = recruitGrid.getCheckedRows();
    	
    	for(const ele of deleteRows) {
	    	delete ele.rowKey;
	    	delete ele.rowSpanMap;
	    	delete ele.sortKey;
	    	delete ele.uniqueKey;
	    	delete ele._attributes;
	    	delete ele._disabledPriority;
	    	delete ele._relationListItemMap;
    	}
    	
    	

    	$.ajax({
            url:'/applicant?cmd=delete',
            method: 'POST',
            data: {
          	  deleteJson : JSON.stringify(deleteRows),
            	
            },
            success: function(res){
      		console.log(res)
      		if(res.trim() === 'true') {
      			alert('성공')
      			location.reload()
      		} else {
      			alert('실패')
      		}
            },
            error: function(e) {
          	  console.log(e)
          	  	console.log(deleteRows)
            }

        	  })
          })
    	
    	
    	
  
    
    
    
    
    



//----------------------------------------------------------------

Grid.applyTheme('striped');





// $('.selectbox').on('click', function() {
// 	$.ajax({
// 		type : "get",
// 		url : "/ajaxView/applicantDo.jsp",
// 		data: {roomNum : $(this).val()},
// 		success: function(res) {
// 			recruitData = res
// 		}
// 	})
// })
</script>
</body>
</html>