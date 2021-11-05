package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ChatServiceImpl;
import service.IChatService;
import service.IMemberService;
import service.MemberServiceImpl;
import vo.ChatDetailVO;
import vo.ChatListVO;
import vo.MemberVO;

@WebServlet("/chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IChatService chatService = ChatServiceImpl.getInstacne();
		IMemberService memberService = MemberServiceImpl.getService();
		RequestDispatcher rd = null;
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		
		MemberVO mvo = (MemberVO) request.getSession().getAttribute("user");
		int member_idx = mvo.getMember_idx();
		
		if (cmd.equals("chatting")) {
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			List<ChatListVO> chatRoomList = chatService.getAllChatRooms(member_idx, user);
			List<MemberVO> memberList = memberService.getAllMember(user.getMember_idx());
//			for(ChatListVO vo : chatRoomList) {
//				System.out.println(vo);
//			}
			request.setAttribute("chatRoom", chatRoomList);
			request.setAttribute("memberList", memberList);
			rd = request.getRequestDispatcher("WEB-INF/views/chat.jsp");
			
		} else if(cmd.equals("chattingDetail")) {
			ChatDetailVO cdvo = new ChatDetailVO();
			int roomInfo = Integer.parseInt(request.getParameter("room"));
			cdvo.setChat_room_idx(roomInfo);
			cdvo.setMember_idx(member_idx);
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO) session.getAttribute("user");
			
//			System.out.println("컨트롤러 방 번호 : " + roomInfo);
			
			List<ChatDetailVO> chatDetailList = chatService.getOneChatRoom(cdvo);
			List<ChatListVO> chatUserList = chatService.getAllChatRooms(member_idx, user);
			
//			for(ChatDetailVO item : chatDetailList) {
//				System.out.println("컨트롤러에서 받은 리턴 값" + item);
//			}
			
//			for(ChatListVO item : chatUserList) {
//				System.out.println("컨트롤러에서 받은 리턴 값 " + item);
//			}
			
			request.setAttribute("chatDetail", chatDetailList);
			request.setAttribute("chatUser", chatUserList);
			
			rd = request.getRequestDispatcher("WEB-INF/views/ajaxView/getChatDetailListDo.jsp");
		} else if (cmd.equals("searchChatRoom")) {
			String searchWord = request.getParameter("searchWord");
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO) session.getAttribute("user");
			
			List<ChatListVO> searchChatList = chatService.searchChatRoom(searchWord, user);
			request.setAttribute("searchChat", searchChatList);
			rd = request.getRequestDispatcher("WEB-INF/views/ajaxView/searchChatRoomDo.jsp");
		} else if (cmd.equals("getLastestChat")) {
			int roomNum = Integer.parseInt(request.getParameter("roomNum"));
			String latestChat = chatService.getLastestChat(roomNum);
			
			request.setAttribute("lastestChat", latestChat);
			rd = request.getRequestDispatcher("WEB-INF/views/ajaxView/getLastestChatDo.jsp");
		} else if(cmd.equals("createRoom")) {
			String json = request.getParameter("json");
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			int chatRoomIdx = chatService.createChatRoom(json, user);
			request.setAttribute("roomNumber", chatRoomIdx);
			
			if(chatRoomIdx != -1) {
				// 룸 정보 가져오기
				String userName = chatService.getOneRoomUsers(chatRoomIdx);
				request.setAttribute("userName", userName);
			}
			
			rd = request.getRequestDispatcher("WEB-INF/views/ajaxView/createChatDo.jsp");
		} else if(cmd.equals("saveMsg.Do")) {
			String room = request.getParameter("room_idx");
			int roomIdx = 0;
			if(room != null) {
				roomIdx = Integer.parseInt(room);				
			}
			int memIdx = Integer.parseInt(request.getParameter("mem_idx"));
			String msg = request.getParameter("msg");
			
			ChatDetailVO cdvo = new ChatDetailVO();
			cdvo.setMember_idx(memIdx);
			cdvo.setChat_room_idx(roomIdx);
			cdvo.setChat_detail_content(msg);
			
			boolean result = chatService.sendChat(cdvo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("WEB-INF/views/ajaxView/saveMsgDo.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
