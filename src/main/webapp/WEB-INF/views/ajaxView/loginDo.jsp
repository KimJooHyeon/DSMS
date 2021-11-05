<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

MemberVO result = (MemberVO)request.getAttribute("result");
String state = request.getAttribute("state") + "";
boolean flag = false;
if(result != null) {
	flag = true;
}
%>
{
	"flag": "<%=flag%>",
	"state" : "<%=state%>"
}