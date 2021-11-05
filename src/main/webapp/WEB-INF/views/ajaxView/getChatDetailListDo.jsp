<%@page import="org.json.simple.JSONObject"%>
<%@page import="vo.ChatDetailVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ChatDetailVO> chatDetailList = (List<ChatDetailVO>) request.getAttribute("chatDetail");
	int size = chatDetailList.size();
	int i = 0;
%>
[
<%for (ChatDetailVO cdvo : chatDetailList) {%>
{
	"chatDetailIdx" : "<%=cdvo.getChat_detail_idx() %>",
	"chatRoomIdx" : "<%=cdvo.getChat_room_idx() %>",
	"memberIdx" : "<%=cdvo.getMember_idx() %>",
	"chatDetailContent" : "<%=cdvo.getChat_detail_content() %>",
	"chatDetailDate" : "<%=cdvo.getChat_detail_date() %>",
	"memberName" : "<%=cdvo.getMember_name() %>",
	"allMemberName" : "<%=cdvo.getAll_member_name() %>"
}
<%if (i < size - 1) {out.print(","); i++;} %>
<%} %>
]
