<%@page import="vo.StudentInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<StudentInfoVO> list = (List<StudentInfoVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/student-info.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />

</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>

	<main>
	
		<section>
			<div class="room-number-wrap">
				<select class="room">
					<option value="301호">301호</option>
					<option value="302호">302호</option>					
					<option value="401호">401호</option>
					<option value="402호">402호</option>
					<option value="403호">403호</option>
					<option value="404호">404호</option>
					<option value="405호">405호</option>
					<option value="406호">406호</option>
				</select>
			</div>
			<div id="recruitGrid"></div>
			<button class="modify-btn" type="button">수정하기</button>
		</section>

	</main>
</div>

<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script>
const Grid = tui.Grid;  //인스턴스 객체 생성

let recruitGrid = new Grid({
  el: document.getElementById('recruitGrid'),
  scrollX: true,
  columns: [
    {
      header: '이름',
      name: 'name',
      align: 'center'
    },
    {
      header: '나이',
      name: 'age',
      align: 'center'
    },
    {
      header: '반',
      name: 'room',
      align: 'center'
    },
    {
      header: '주소',
      name: 'addr',
      editor: 'text'
    },
    {
      header: '전화번호',
      name: 'tel',
      editor: 'text',
      align: 'center'
    },
    {
      header: '자격증',
      name: 'license',
      editor: 'text'
    },
    {
      header: '학력',
      name: 'education',
      editor: 'text',
      align: 'center'
    },
    {
      header: '초급 프로젝트',
      name: 'basic-project',
      editor: 'text',
      align: 'center'
    },
    {
      header: '중급 프로젝트',
      name: 'middle-project',
      editor: 'text',
      align: 'center'
    },
    {
      header: '최종 프로젝트',
      name: 'last-project',
      editor: 'text',
      align: 'center'
    },
    {
      header: '수료구분',
      name: 'graduate',
      editor: 'text',
      align: 'center'
    },
    {
    	header: 'idx',
    	name: 'idx',
    	hidden: true
    }
  ],
  data: {
	  contentType : 'application/json',
	  api: {
		  readData: {
			  url: '/student?cmd=info&roomNumber=' + $('.room').val().substring(0, 3),
			  method: 'get'
		  }
	  }
  }
});

//recruitGrid.resetData(newData); 
Grid.applyTheme('striped'); 
</script>
<script type="text/javascript" src="../js/student-info.js"></script>
</body>
</html>