<%@page import="vo.NoticeCompanyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<NoticeCompanyVO> list = (List<NoticeCompanyVO>)request.getAttribute("NoticeCompanyList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/company.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<section>
			<div class="btn-wrap">
				<button type="button" class="insert">추가</button>			
				<button type="button" class="delete">삭제</button>
				<button type="button" class="save">저장</button>			
			</div>
			<div class="grid-wrap">
				<div id="recruitGrid"></div>
			</div>
		</section>
	</main>

</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1a7bc73fb550d50d510296af57950207"></script>
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/company.js"></script>

<script type="text/javascript">
const Grid = tui.Grid;  //인스턴스 객체 생성
let recruitData = [
<%for (NoticeCompanyVO vo : list){%>  
	{
    title: "<%=vo.getCompany_name() %>",
    addr: "<%=vo.getCompany_addr() %>",
    salary: "<%=vo.getCompany_salary() %>",
    section: "<%=vo.getCompany_section() %>",
    intake: "<%=vo.getRecuruit_intake() %>",
    idx: "<%=vo.getCompany_idx() %>"
  },
<%}%>
]

let	 recruitGrid = new Grid({
  el: document.getElementById('recruitGrid'),
  rowHeaders: ['checkbox'],
  columns: [
    {
      header: '회사명',
      name: 'title',
      align: 'center',
      editor: 'text'
    },
    {
      header: '주소',
      name: 'addr',
      align: 'center',
      editor: 'text'
    },
    {
      header: '제시 연봉',
      name: 'salary',
      align: 'center',
      editor: 'text'
    },
    {
      header: '모집 분야',
      name: 'section',
      align: 'center',
      editor: 'text'
    },
    {
      header: '채용인원',
      name: 'intake',
      align: 'center',
      editor: 'text'
    },
    {
    	header: 'idx',
	    name: 'idx',
	    hidden : true
    }
  ],
  data: recruitData
});

$('.insert').on('click', function(){
	const obj = {
			title: "",
			addr: "",
			salary: "",
			section: "",
			intake: ""
	}
	recruitData.unshift(obj)
	$('#recruitGrid').html("")
	recruitGrid = new Grid({
		  el: document.getElementById('recruitGrid'),
		  scrollX: true,
		  rowHeaders: ['checkbox'],
		 columns: [
				    {
				      header: '회사명',
				      name: 'title',
				      align: 'center',
				      editor: 'text'
				    },
				    {
				      header: '주소',
				      name: 'addr',
				      align: 'center',
				      editor: 'text'
				    },
				    {
				      header: '제시 연봉',
				      name: 'salary',
				      align: 'center',
				      editor: 'text'
				    },
				    {
				      header: '모집 분야',
				      name: 'section',
				      align: 'center',
				      editor: 'text'
				    },
				    {
				      header: '채용인원',
				      name: 'intake',
				      align: 'center',
				      editor: 'text'
				    },
				    {
				    	header: 'idx',
					    name: 'idx',
					    hidden : true
				    }
				    
				  ],
		  data: recruitData
		});
})

$('.delete').on('click', function() {
	const selectedValues   = recruitGrid.getValue(recruitGrid.getCheckedRowKeys(), "idx");
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
	
	const _data = JSON.stringify(deleteRows)
	console.log(_data)
	
	$.ajax({
		url: '/company?cmd=companyDelete',
		data: {
			json: _data
		},
		success: function(res) {
			alert('삭제 완료')
			location.reload()
			
		},
		error: function(e) {
			console.log(e)
		}
	})
})


$('.save').on('click', function() {
	const { rowKey, columnName } = recruitGrid.getFocusedCell();
	if (rowKey && columnName) {
		recruitGrid.finishEditing(rowKey, columnName);
	}
	const updatedRows = recruitGrid.getCheckedRows();
 
	for(const ele of updatedRows) {
    	delete ele.rowKey;
    	delete ele.rowSpanMap;
    	delete ele.sortKey;
    	delete ele.uniqueKey;
    	delete ele._attributes;
    	delete ele._disabledPriority;
    	delete ele._relationListItemMap;
	}

	
	const _data = JSON.stringify(updatedRows)
	console.log(_data)
	
	$.ajax({
		url: '/company?cmd=companySave',
		data: {
			json: _data
		},
		success: function(res) {
			alert('저장완료')
			location.reload()
		},
		error: function(e) {
			console.log(e)
		}
	})
})










Grid.applyTheme('striped');



</script>
</body>
</html>