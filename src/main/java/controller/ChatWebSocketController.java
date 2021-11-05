package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import service.ChatServiceImpl;
import service.IChatService;
import service.IMemberService;
import service.MemberServiceImpl;
import vo.ChatDetailVO;
import vo.ChatMessageVO;
import vo.ChatMultiVO;

@ServerEndpoint("/webSocketChatting")
public class ChatWebSocketController {
	//유저 집합 리스트
	//static List<DditChatVO> sessionUsers = Collections.synchronizedList(new ArrayList<DditChatVO>());
	private static Map<String, List<ChatMultiVO>> sessionUsersMap = 
			Collections.synchronizedMap(new HashMap<String, List<ChatMultiVO>>());
	
	/**
	 * 웹 소켓이 접속되면 전체방의 유저리스트에 세션을 넣는다.
	 * @param userSession 웹 소켓 세션
	 */
	@OnOpen
	public void handleOpen(Session userSession){
		if(!sessionUsersMap.containsKey("9999")) {
			sessionUsersMap.put("9999", new ArrayList<ChatMultiVO>() );
		}
		ChatMultiVO chatVo = new ChatMultiVO(null, userSession);
		sessionUsersMap.get("9999").add(chatVo);	
		//sessionUsers.add(chatVo);
//		System.out.println("사용자" + (Integer.parseInt(userSession.getId()) + 1) + " 접속\n");
	}
	
	
	/**
	 * 웹 소켓으로부터 메시지가 오면 호출한다.
	 * @param message 메시지
	 * @param userSession
	 * @throws IOException
	 */
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException{
		
		String username = (String)userSession.getUserProperties().get("username");
		if (username == null) {
			username = "9999";
		}
		String room = (String) userSession.getUserProperties().get("room");
//		System.out.println("방이름 = " + room);
		
		// JSON구조의 문자열로 온 메시지를 객체형으로 변환한다.
		Gson gson = new Gson();
		ChatMessageVO chatMessageVo = gson.fromJson(message, ChatMessageVO.class);
//		System.out.println("내용 " + chatMessageVo);
//		if(chatMessageVo.getMessage()==null || "null".equals(chatMessageVo.getMessage())) {
//			System.out.println("999999999999999999");
//			chatMessageVo.setMessage("");
//		}
		
		// 세션 프로퍼티에 username이 없으면 username을 선언하고 해당 세션으로 메시지를 보낸다.(json 형식이다.)
		// 최초 메시지는 username 설정  
		// 처음에는 무조건 '전체'라는 채팅방에 추가된다.
		if(username == null || "connect".equals(chatMessageVo.getCommand()) ){
			for(ChatMultiVO chatVo : sessionUsersMap.get("9999")){
				if(userSession.equals(chatVo.getSession())){
					chatVo.setMember_name(chatMessageVo.getMessage() );
					userSession.getUserProperties().put("username", chatMessageVo.getMessage() );
					//userSession.getUserProperties().put("room", "전체");
					userSession.getUserProperties().put("room", chatMessageVo.getRoom() );
					
//					userSession.getBasicRemote().sendText(buildJsonData("System", chatMessageVo.getMessage() + "님 연결 성공!!", userSession));
					
					Iterator<ChatMultiVO> iterator = sessionUsersMap.get("9999").iterator();
					while(iterator.hasNext()){
						ChatMultiVO chVo = iterator.next();
						if(!chVo.getSession().equals(chatVo.getSession())){
							chVo.getSession().getBasicRemote().sendText(buildJsonData("System", chatMessageVo.getMessage() + "님이 입장했습니다.", userSession));
						}
					}
				
					// 채팅 방 목록과 해당 방의 멤버 목록을 갱신하는 메서드 호출
//					roomUpdateAll(userSession);
					return;
				}
			}
		}
		
/*
		if("create".equals(chatMessageVo.getCommand()) ){
			String newRoom = chatMessageVo.getRoom();
			if(sessionUsersMap.containsKey(newRoom)) {
				userSession.getBasicRemote().sendText( buildJsonData("System", newRoom + " 채팅방은 이미 있습니다.", null));
			}else {
				// 새로 생성된 채팅방에 저장할 List객체 생성
				List<ChatMultiVO> roomMemList = new ArrayList<ChatMultiVO>();
				ChatMultiVO newCharVo = null;
				
				// 현재 채팅방에서 현재 Session을 갖는 DditChatVO객체를 찾는다.
				for(ChatMultiVO oldChatVo : sessionUsersMap.get(room)){
					if(userSession.equals(oldChatVo.getSession())){
						newCharVo = oldChatVo;
						break;
					}
				}
				sessionUsersMap.get(room).remove(newCharVo);
*/
		
				// 채팅방에 멤버가 하나도 없으면 채팅방을 삭제한다.('전체' 체팅방은 멤버가 하나도 없어도 삭제되지 않는다.)
//				if(!"전체".equals(room) && sessionUsersMap.get(room).size()==0) {
//					sessionUsersMap.remove(room);
//				}
				
/*				
				// 이전 채팅방에서 퇴장하는 메시지 전송
				if(sessionUsersMap.containsKey(room)) {
					Iterator<ChatMultiVO> iterator = sessionUsersMap.get(room).iterator();
					while(iterator.hasNext()){
						ChatMultiVO chVo = iterator.next();
						if(!chVo.getSession().equals(userSession)){
							chVo.getSession().getBasicRemote().sendText(buildJsonData("System", newCharVo.getMember_name() + "님이 퇴장했습니다.", userSession));
						}
					}
				}
*/			

/*		
				// 찾은 DditChatVO객체를 새로 생성된 채팅방 List에 추가한다.
				roomMemList.add(newCharVo);
				
				// 새로운 채팅방에 List 추가
				sessionUsersMap.put(newRoom, roomMemList );
				// 세션의 room속성을 새로운 채팅방으로 변경
				userSession.getUserProperties().put("room", chatMessageVo.getRoom() );
				
				userSession.getBasicRemote().sendText(buildJsonData("System", newRoom + " 채팅방 생성 성공!!", userSession));
				
				// 채팅 방 목록과 해당 방의 멤버 목록을 갱신하는 메서드 호출
//				roomUpdateAll(userSession);
				
				// 새로운 채팅방에서 입장하는 메시지 전송
				Iterator<ChatMultiVO> iterator = sessionUsersMap.get(newRoom).iterator();
				while(iterator.hasNext()){
					ChatMultiVO chVo = iterator.next();
					if(!chVo.getSession().equals(userSession)){
						chVo.getSession().getBasicRemote().sendText(buildJsonData("System", newCharVo.getMember_name() + "님이 입장했습니다.", userSession));
					}
				}
			}
		}
*/		
		if("change".equals(chatMessageVo.getCommand()) ){
			String newRoom = chatMessageVo.getRoom();
			if(room.equals(newRoom)) {
//				userSession.getBasicRemote().sendText( buildJsonData("System", newRoom + " 채팅방은 현재 입장해있는 채팅방입니다.", null));
				return;
			}
/*			
			ChatMultiVO newCharVo = null;
			
			// 현재 채팅방에서 현재 Session을 갖는 DditChatVO객체를 찾는다.
			for(ChatMultiVO oldChatVo : sessionUsersMap.get(room)){
				if(userSession.equals(oldChatVo.getSession())){
					newCharVo = oldChatVo;
					break;
				}
			}
			// 이전 채팅방의 멤버 목록에서 삭제한다.
			//sessionUsersMap.get(room).remove(newCharVo);
			
//			// 채팅방에 멤버가 하나도 없으면 채팅방을 삭제한다.
//			if(!"전체".equals(room) && sessionUsersMap.get(room).size()==0) {
//				sessionUsersMap.remove(room);
//			}
			
			// 찾은 DditChatVO객체를 이동할 채팅방 List에 추가한다.
			sessionUsersMap.get(newRoom).add(newCharVo);
			
			// 이전 채팅방에서 퇴장하는 메시지 전송
			if(sessionUsersMap.containsKey(room)) {
				Iterator<ChatMultiVO> iterator = sessionUsersMap.get(room).iterator();
				while(iterator.hasNext()){
					ChatMultiVO chVo = iterator.next();
					if(!chVo.getSession().equals(userSession)){
						chVo.getSession().getBasicRemote().sendText(buildJsonData("System", newCharVo.getMember_name() + "님이 퇴장했습니다.", userSession));
					}
				}
			}
		
			// 세션의 room속성을 새로운 채팅방으로 변경
			userSession.getUserProperties().put("room", newRoom );
//			userSession.getBasicRemote().sendText(buildJsonData("System", newRoom + " 채팅방으로 이동 성공!!", userSession));
			
			// 채팅 방 목록과 해당 방의 멤버 목록을 갱신하는 메서드 호출
			roomUpdateAll(userSession);
			// 새로운 채팅방에서 입장하는 메시지 전송
			Iterator<ChatMultiVO> iterator = sessionUsersMap.get(newRoom).iterator();
			while(iterator.hasNext()){
				ChatMultiVO chVo = iterator.next();
				if(!chVo.getSession().equals(userSession)){
					chVo.getSession().getBasicRemote().sendText(buildJsonData("System", newCharVo.getMember_name() + "님이 입장했습니다.", userSession));
				}
			}
*/	
		}
	
		if("message".equals(chatMessageVo.getCommand()) ){
			// username이 있으면 해당 채팅방 전체에게 메시지를 보낸다.
			sendToAll(room, username, chatMessageVo.getMessage());
			return;
		}
		
	}
	
	// 채팅 방 목록과 해당 방의 멤버 목록을 보내는 메서드
	public void roomUpdateAll(Session userSession) throws IOException{
//		System.out.println("roomUpdateAll ==> " + userSession);
//		for(String roomName : sessionUsersMap.keySet()) {
//			for(ChatMultiVO chatVo : sessionUsersMap.get(roomName)) {
//				if(userSession != chatVo.getSession())
//					chatVo.getSession().getBasicRemote().sendText(buildJsonData(null, null, chatVo.getSession()));
//			}
//		}
	}
	
	/**
	 * 해당 채팅방 전체에게 메시지를 보낸다.
	 * @param room 채팅방이름
	 * @param username 사용자 이름
	 * @param message 메시지
	 * @throws IOException
	 */
	public void sendToAll(String room, String username, String message) throws IOException{
		// username이 있으면 채팅방 전체에게 메시지를 보낸다.
		if(sessionUsersMap.containsKey(room)) {
			Iterator<ChatMultiVO> iterator = sessionUsersMap.get(room).iterator();
			while(iterator.hasNext()){
				iterator.next().getSession().getBasicRemote().sendText(buildJsonData(username, message, null));
			}
		}
	}
	
	/**
	 * 웹소켓을 닫으면 해당 유저를 유저리스트에서 뺀다.
	 * @param userSession
	 * @throws IOException */
	@OnClose
	public void handleClose(Session userSession) throws IOException{
//		System.out.println("\n사용자" + (Integer.parseInt(userSession.getId()) + 1) + " 접속 종료");
		String room = (String) userSession.getUserProperties().get("room");
		
		String delName = null;
		Iterator<ChatMultiVO> chatIter = sessionUsersMap.get(room).iterator();
		
		while(chatIter.hasNext()){
			ChatMultiVO chatVo = chatIter.next();
			if(userSession.equals(chatVo.getSession())){
				delName = chatVo.getMember_name();
				//sessionUsers.remove(chatVo);
				chatIter.remove();
			}
		}
		if(sessionUsersMap.get(room).size() > 0 ) {	
			sendToAll(room, "System", delName + "님이 퇴장했습니다.");
		}else {
			// 채팅방에 멤버가 하나도 없으면 채팅방을 삭제한다.
			if(!"9999".equals(room)) {
				sessionUsersMap.remove(room);
			}
		}
		// 채팅 방 목록과 해당 방의 멤버 목록을 갱신하는 메서드 호출
		roomUpdateAll(userSession);
	}
	
	/**
     * 웹 소켓이 에러가 나면 호출되는 이벤트
     * @param t
     */
    @OnError
    public void handleError(Throwable t){
        t.printStackTrace();
    }
    
	
	/**
	 * json타입의 메시지 만들기
	 * @param username
	 * @param message
	 * @return
	 */
	public String buildJsonData(String username, String message, Session userSession){
		IChatService chatService = ChatServiceImpl.getInstacne();
		IMemberService memberService = MemberServiceImpl.getService();
		ChatDetailVO cdvo = new ChatDetailVO();
		
		if(message.contains("퇴장했습니다") || message.contains("입장했습니다") || message == null) {
			return "";
		}
		
		int dotIndex = message.lastIndexOf(".");
		String msg = message.substring(0, dotIndex);
		int roomNumber = 0;
//		System.out.println(message);
		if (message.indexOf(".") == -1) {
			roomNumber = 9999;
		} else {
			roomNumber = Integer.parseInt(message.substring(dotIndex + 1));
		}
		
		if (username == null) {
			username = "9999";
		} else {
			cdvo.setMember_idx(Integer.parseInt(username));			
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatTime = format.format(System.currentTimeMillis());
		String memberName = memberService.getMemberName(Integer.parseInt(username));
		cdvo.setChat_room_idx(roomNumber);
		cdvo.setChat_detail_content(msg);
		cdvo.setChat_detail_date(formatTime);
		cdvo.setMember_name(memberName);
		//chatService.sendChat(cdvo);
		
		Gson gson = new Gson();
		Map<String, String> jsonMap = new HashMap<String, String>();
		
		if(message!=null) {
//			jsonMap.put("message", username + " : " + message);
			jsonMap.put("message", cdvo.getChat_detail_content());
			jsonMap.put("name", cdvo.getMember_name());
			jsonMap.put("date", cdvo.getChat_detail_date());
			jsonMap.put("member_idx", cdvo.getMember_idx()+"");
		}
		
		if(userSession!=null) {
			List<String> roomList = new ArrayList<String>(sessionUsersMap.keySet());
			//System.out.println("roomList ===> " + roomList);
			
			String room = (String) userSession.getUserProperties().get("room");
			
			List<String> roomMemList = new ArrayList<>();
			for(ChatMultiVO dditCharVo : sessionUsersMap.get(room))	{
				roomMemList.add(dditCharVo.getMember_name());
			}
			//System.out.println("roomMemList ===> " + roomMemList);
			
			jsonMap.put("roomName", room);
			jsonMap.put("roomList", gson.toJson(roomList));
			jsonMap.put("roomMemList", gson.toJson(roomMemList));
		}
		String strJson = gson.toJson(jsonMap);
//		System.out.println("strJson = " + strJson);
		
		return strJson;
		
		/*
		JsonObject jsonObject = Json.createObjectBuilder().add("message", username+" : "+message).build();
		StringWriter stringwriter = new StringWriter();
		try(JsonWriter jsonWriter = Json.createWriter(stringwriter)){
			jsonWriter.write(jsonObject);
		};
		System.out.println("stringwriter = " + stringwriter.toString());
		return stringwriter.toString();
		*/
		
	}
}





