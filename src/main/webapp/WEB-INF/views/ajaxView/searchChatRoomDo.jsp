<%@page import="vo.ChatListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<ChatListVO> searchChatList = (List<ChatListVO>) request.getAttribute("searchChat");
int size = searchChatList.size();
int i = 0;
%>

[

<%for (ChatListVO clvo : searchChatList) {%>
{
	"chatRoomIdx" : "<%=clvo.getChat_room_idx() %>",
	"chatDetailContent" : "<%=clvo.getChat_detail_content() %>",
	"chatDetailDate" : "<%=clvo.getChat_detail_date() %>",
	"memberName" : "<%=clvo.getMember_name() %>"
}
<%if (i < size - 1) {out.print(","); i++;} %>
<%} %>
]