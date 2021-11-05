package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.ChatDAOImpl;
import dao.IChatDAO;
import util.JSONUtil;
import vo.ChatDetailVO;
import vo.ChatListVO;
import vo.ChatRoomVO;
import vo.MemberVO;

public class ChatServiceImpl implements IChatService {
	private IChatDAO dao;
	private static ChatServiceImpl service;

	private ChatServiceImpl() {
		dao = ChatDAOImpl.getInstance();

	}

	public static ChatServiceImpl getInstacne() {
		if (service == null) {
			service = new ChatServiceImpl();
		}
		return service;
	}

	@Override
	public List<ChatListVO> getAllChatRooms(int member_idx, MemberVO user) {
		List<Integer> roomList = dao.getRoomList(member_idx);
		List<ChatListVO> result = new ArrayList<>();

		for (Integer item : roomList) {
			ChatListVO vo = new ChatListVO();
			List<String> u = dao.getRoomUser(item);
			String allUser = String.join(",", u);
			String lastContent = dao.getLastContent(item);

			vo.setChat_detail_content(lastContent);
			vo.setMember_name(allUser);
			vo.setChat_room_idx(item);

			result.add(vo);
		}
		return result;
	}
	
	@Override
	public List<ChatListVO> searchChatRoom(String member_name, MemberVO user) {
		List<Integer> chatRoomList = dao.searchChatRoom(member_name, user);
		List<ChatListVO> result = new ArrayList<>();
		
		for (Integer item : chatRoomList) {
			ChatListVO vo = new ChatListVO();
			List<String> u = dao.getRoomUser(item);
			String allUser = String.join(",", u);
			String lastContent = dao.getLastContent(item);
			
			vo.setChat_detail_content(lastContent);
			vo.setMember_name(allUser);
			vo.setChat_room_idx(item);
			
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<ChatDetailVO> getOneChatRoom(ChatDetailVO cdvo) {
		List<ChatDetailVO> oneChatRoom = dao.getOneChatRoom(cdvo);
		List<String> roomUser = dao.getRoomUser(cdvo.getChat_room_idx());
		String user = String.join(",", roomUser);
		
		for (ChatDetailVO vo : oneChatRoom) {
			vo.setAll_member_name(user);
		}
		return oneChatRoom;
	}

	@Override
	public boolean delChatRoom(ChatRoomVO crvo, ChatDetailVO cdvo) {
		return dao.delChatRoom(crvo, cdvo);
	}

	@Override
	public int createChatRoom(String json, MemberVO user) {
		List<Map<String, String>> map = JSONUtil.getJsonMap(json);
		List<ChatRoomVO> list = new ArrayList<>();
		int seq = dao.getSeq(); // 생성될 방 번호
		
		for(Map<String, String> item : map) {
			ChatRoomVO vo = new ChatRoomVO();
			int idx = Integer.parseInt(item.get("value"));
			vo.setMember_idx(idx);
			vo.setchat_room_idx(seq);
			list.add(vo);
		}
		
		ChatRoomVO vo = new ChatRoomVO();
		vo.setchat_room_idx(seq);
		vo.setMember_idx(user.getMember_idx());
		list.add(vo);
		
		for(ChatRoomVO item : list) {
			boolean result = dao.createChatRoom(item);
			if(!result) {
				return -1; // 방 생성 실패시 -1 리턴
			}
		}
		return seq;
	}

	@Override
	public boolean sendChat(ChatDetailVO cdvo) {
		boolean flag = false;

		if (dao.sendChat(cdvo)) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public String getLastestChat(int chat_room_idx) {
		return dao.getLastContent(chat_room_idx);
	}

	@Override
	public String getOneRoomUsers(int chat_room_idx) {
		List<String> user = dao.getRoomUser(chat_room_idx);
		String allUser = String.join(",", user);
		return allUser;
	}
}
