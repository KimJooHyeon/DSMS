<%@page import="vo.ClassRoomVO"%>
<%@page import="vo.StudentOfClassRoomVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%List<StudentOfClassRoomVO> list = (List<StudentOfClassRoomVO>) request.getAttribute("AllClassStudent"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/class-room-list.css" />
<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
</head>
<body>
<div class="body__container">

	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>
	
	<main>
		<select name="">
			<option value="401">401호</option>
			<option value="402">402호</option>
			<option value="403">403호</option>
			<option value="404">404호</option>
			<option value="405">405호</option>
			<option value="406">406호</option>
			<option value="301">301호</option>
			<option value="302">302호</option>
		</select>
		<div id="recruitGrid"></div>
	</main>

</div>


<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<script type="text/javascript">
const Grid = tui.Grid;  //인스턴스 객체 생성
const recruitData = [
	<%for(StudentOfClassRoomVO scvo : list){ %>
	{
    room: "<%=scvo.getClass_number()%>",
    name: "<%=scvo.getStudent_name()%>",
    teacher: "<%=scvo.getMember_name()%>",
    tel: "<%=scvo.getStudent_name()%>"
  }
<%}%>
	]

const recruitGrid = new Grid({
  el: document.getElementById('recruitGrid'),
  bodyHeight: 680,
  columns: [
    {
      header: '반',
      name: 'room',
      align: 'center'
    },
    {
      header: '이름',
      name: 'name',
      align: 'center'
    },
    {
      header: '담임 교사',
      name: 'teacher',
      align: 'center'
    },
    {
      header: '교사 전화 번호',
      name: 'tel',
      align: 'center'
    }
  ],
  data: recruitData
});

Grid.applyTheme('striped');
</script>
</body>
</html>