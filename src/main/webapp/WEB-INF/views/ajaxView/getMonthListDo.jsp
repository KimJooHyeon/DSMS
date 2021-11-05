<%@page import="vo.Schedule"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  List<Schedule> list = (List<Schedule>)request.getAttribute("list");
  int size = list.size();
  int i = 0;
  %>
  [
  <%for(Schedule item : list) {%>
  {
    "_id" : "<%=item.getSchedule_idx()%>",
    "title" : "<%=item.getSchedule_title()%>",
    "description" : "<%=item.getSchedule_content()%>",
    "start" : "<%=item.getStart_date().replace(" ", "T").substring(0, 16)%>",
    "end" : "<%=item.getEnd_date().replace(" ", "T").substring(0, 16)%>",
    "type" : "<%=item.getSchedule_type()%>",
    "username" : "<%=item.getMember_name()%>",
    "backgroundColor" : "<%=item.getBackground_color()%>",
    "textColor" : "<%=item.getText_color()%>"
  }
  <%if(i < size - 1) {out.print(","); i++;}%>
  <%}%>
  ]