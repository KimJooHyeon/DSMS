<%@page import="vo.CounselingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<CounselingVO> list = (List<CounselingVO>)request.getAttribute("counselList");
int i = 0;
if(list != null){
%>
[
<%for(CounselingVO item : list) {%>
{
	"counsel-idx": "<%=item.getCounseling_idx()%>",
	"member-idx": "<%=item.getTeacher_idx()%>",
	"student-idx": "<%=item.getStudent_idx()%>",
	"title": "<%=item.getCounseling_title()%>",
	"date": "<%=item.getCounseling_date().substring(0, 10)%>"
}
<%
i++;
if(i < list.size()) out.print(",");%>
<%}%>
]
<%}%>