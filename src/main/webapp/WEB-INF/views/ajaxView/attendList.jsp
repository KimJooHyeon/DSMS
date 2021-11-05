<%@page import="java.util.List"%>
<%@page import="vo.AttendanceVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<AttendanceVO> list = (List<AttendanceVO>)request.getAttribute("attendList");
int i = 0;
if(list != null) {%>
[
<%for(AttendanceVO item : list) {%>
{
	"year": "<%=item.getAttend_date()%>",
	"idx": "<%=item.getStudent_idx()%>",
	"date": "<%=item.getAttend_date()%>",
	"in": "<%=item.getAttend_in_time()%>",
	"out": "<%=item.getAttend_out_time()%>",
	"division": "<%=item.getAttend_division()%>"
}
<%i++;
if(i < list.size()) out.print(",");%>
<%}%>
]
<%}%>