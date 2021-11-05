<%@page import="vo.StudentInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%
List<StudentInfoVO> list = (List<StudentInfoVO>)request.getAttribute("list");
int i = 0;
%>
{"result": true,
"data" :{
"contents" : [
<%for(StudentInfoVO item : list) {%>
  {
    "name": "<%=item.getStudent_name()%>",
    "age": "<%=item.getStudent_age()%>",
    "room": "<%=item.getClass_number()%>호",
    "addr": "<%=item.getStudent_addr()%>",
    "tel": "<%=item.getStudent_tel()%>",
    "license": "<%=item.getLicense_name()%>",
    "education": "<%=item.getStudent_education()%>",
    "basic-project": "<%=item.getBasic_project()%>",
    "middle-project": "<%=item.getMiddle_project()%>",
    "last-project": "<%=item.getFinal_project()%>",
    "graduate": "<%=item.getStudent_graduate()%>",
    "idx": "<%=item.getStudent_idx()%>"
  }
<%
if(i < list.size() - 1) out.print(","); i++;
}%>
]}}