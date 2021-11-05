<%@page import="vo.WorkCompanyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대덕 인재 개발원</title>
<link rel="stylesheet" href="../css/map.css" />
</head>
<body>
<div class="body__container">
<%
List<String> mapInfo = (List<String>) request.getAttribute("map");
// System.out.println(mapInfo);
%>
	<jsp:include page="main-nav.jsp" flush="false">
  		<jsp:param name="job" value="${user.getMember_job()}"></jsp:param>
  	</jsp:include>

	<main>
		<div id="map" style="width: 90%; height: 80%;"></div>
	</main>
</div>

<script type="text/javascript">
const j = {
		"position": [
		]
}

<%for(String str : mapInfo){%>
	j.position.push(JSON.parse(`<%=str%>`))
<%}%>



</script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1a7bc73fb550d50d510296af57950207&libraries=clusterer"></script>
<script type="text/javascript" src="../js/map.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
</body>
</html>