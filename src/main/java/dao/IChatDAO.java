package dao;

import java.util.List;

import vo.ChatDetailVO;
import vo.ChatListVO;
import vo.ChatRoomVO;
import vo.MemberVO;

public interface IChatDAO {
	
//	List<ChatListVO> getAllChatRooms(int member_idx);
	
	List<ChatDetailVO> getOneChatRoom(ChatDetailVO cdvo);
	
	boolean delChatRoom(ChatRoomVO crvo, ChatDetailVO cdvo);
	
	boolean createChatRoom(ChatRoomVO crvo);
	
	boolean sendChat(ChatDetailVO cdvo);
	
	List<String> getMemberName(int room);
	
	List<Integer> getRoomList(int member_idx);
	
	List<String> getRoomUser(int chat_room_idx);
	
	String getLastContent(int chat_room_idx);
	
	List<Integer> searchChatRoom(String member_name, MemberVO user);
	
	int getSeq();
}
