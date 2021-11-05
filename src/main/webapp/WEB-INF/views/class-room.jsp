<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/class-room.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<div class="btn-wrap">
			<button class="mod" type="button">배정</button>
			<button class="del" type="button">삭제</button>
		</div>
		<div class="grid-wrap">
		<div id="recruitGrid"></div>
		</div>
	</main>

</div>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script type="text/javascript">
const Grid = tui.Grid;  //인스턴스 객체 생성
const recruitData = [
  <c:forEach items="${list}" var="item">
  {
    room: "",
    name: "${item.applicant_name}",
    score: "${item.applicant_score}",
    teacher: "",
    idx: "${item.applicant_idx}",
    tel: "${item.applicant_tel}",
    age: "${item.applicant_age}"
  },
  </c:forEach>
]

const recruitGrid = new Grid({
  el: document.getElementById('recruitGrid'),
  rowHeaders: ['checkbox'],
  columns: [
    {
      header: '반',
      name: 'room',
      align: 'center',
      editor: {
    	  type: 'select',
    	  options: {
    		  listItems: [
    			  {
    				  text: '없음',
    				  value: '없음'
    			  },
    			  {
    				  text: '401호',
    				  value: '401호'
    			  },
    			  {
    				  text: '402호',
    				  value: '402호'
    			  },
    			  {
    				  text: '403호',
    				  value: '403호'
    			  },
    			  {
    				  text: '404호',
    				  value: '404호'
    			  },
    			  {
    				  text: '405호',
    				  value: '405호'
    			  },
    			  {
    				  text: '406호',
    				  value: '406호'
    			  },
    			  {
    				  text: '301호',
    				  value: '301호'
    			  },
    			  {
    				  text: '302호',
    				  value: '302호'
    			  }
    		  ]
    	  }
      }
    },
    {
      header: '이름',
      name: 'name',
      align: 'center'
    },
    {
        header: '면접 점수',
        name: 'score',
        align: 'center'
      },
    {
      header: '담임 교사',
      name: 'teacher',
      align: 'center',
      editor: {
    	  type: 'select',
    	  options: {
    		  listItems: [
    			  {
    				  text: '없음',
    				  value: '없음'
    			  },
    			  <c:forEach items="${allMember}" var="item">
    			  {
    				  text: "${item.member_name}",
    				  value: "${item.member_idx}"
    			  },
    			  </c:forEach>
    		  ]
    	  }
      }
    },
    {
    	header: "idx",
    	name: "idx",
    	hidden: true
    },
    {
    	header: "tel",
    	name: "tel",
    	hidden: true
    },
    {
    	header: "age",
    	name: "age",
    	hidden: true
    }
  ],
  data: recruitData
});

Grid.applyTheme('striped');

$('.mod').on('click', function() {
	const { rowKey, columnName } = recruitGrid.getFocusedCell();
	if (rowKey && columnName) {
		recruitGrid.finishEditing(rowKey, columnName);
	}
	const { updatedRows } = recruitGrid.getModifiedRows();
  
	for(const str of updatedRows) {
		delete str.rowKey
		delete str._attributes
	}
	
	const _data = JSON.stringify(updatedRows)
	console.log(_data)
	
	$.ajax({
		url: '/student?cmd=classRoomSetModify',
		data: {
			json: _data
		},
		success: function(res) {
			if(res.trim() === 'true') {
				alert('수정 완료')
				location.reload()
			} else {
				alert('수정 실패')
			}
		},
		error: function(e) {
			console.log(e)
		}
	})
})

$('.del').on('click', function() {
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
		url: '/student?cmd=classRoomSetDelete',
		data: {
			json: _data
		},
		success: function(res) {
			if(res.trim() === 'true') {
				alert('삭제 완료')
				location.reload()
			} else {
				alert('삭제 실패')
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