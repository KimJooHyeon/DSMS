<%@page import="vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<StudentVO> list = (List<StudentVO>)request.getAttribute("list");
int i = 0;
if(list != null) {
%>
[
<%for(StudentVO item : list) {%>

{
	"name" : "<%=item.getStudent_name()%>",
	"idx" : "<%=item.getStudent_idx()%>"
}

<%
i++;
if(i < list.size()) out.print(",");%>
<%}%>
]
<%}%>