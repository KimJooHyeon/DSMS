<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int roomNumber = (int)request.getAttribute("roomNumber");
if(roomNumber != -1) {
String name = request.getAttribute("userName") + "";
System.out.println(name);
%>
{
	"roomNumber" : "<%=roomNumber%>",
	"name" : "<%=name%>"
}
<%} else {%>
{
	"roomNumber" : "",
	"name" : ""
}
<%}%>