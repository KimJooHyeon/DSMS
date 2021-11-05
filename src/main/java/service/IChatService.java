package service;

import java.util.List;

import vo.ChatDetailVO;
import vo.ChatListVO;
import vo.ChatRoomVO;
import vo.MemberVO;

public interface IChatService {
	/**
	 * 
	 * @return 회원의 채팅방 리스트 반환
	 */
	List<ChatListVO> getAllChatRooms(int member_idx, MemberVO user);
	
	/**
	 * 
	 * @param cdvo
	 * @return 회원의 채팅방 중 선택한 채팅방의 내용 반환
	 */
	List<ChatDetailVO> getOneChatRoom(ChatDetailVO cdvo);
	
	/**
	 * 
	 * @param crvo
	 * @param cdvo
	 * @return 채팅방 삭제 후 성공 시 true
	 */
	boolean delChatRoom(ChatRoomVO crvo, ChatDetailVO cdvo);
	
	/**
	 * 
	 * @param crvo
	 * @return 채팅방 생성 성공 시 채팅방 번호 반환
	 */
	int createChatRoom(String json, MemberVO user);
	
	/**
	 * 
	 * @param cdvo
	 * @return 메시지 전송 후 성공 시 true
	 */
	boolean sendChat(ChatDetailVO cdvo);
	
	/**
	 * 
	 * @param member_name
	 * @return 검색한 단어가 포함된 채팅방 리스트 반환
	 */
	List<ChatListVO> searchChatRoom(String member_name, MemberVO user);
	
	/**
	 * 
	 * @param chat_room_idx
	 * @return 선택한 채팅방의 마지막 메시지 반환
	 */
	String getLastestChat(int chat_room_idx);
	
	String getOneRoomUsers(int chat_room_idx);
}
