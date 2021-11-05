package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.ChatDetailVO;
import vo.ChatListVO;
import vo.ChatRoomVO;
import vo.MemberVO;

public class ChatDAOImpl implements IChatDAO {
	private SqlMapClient smc;
	private static ChatDAOImpl dao;

	private ChatDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}

	public static ChatDAOImpl getInstance() {
		if (dao == null) {
			dao = new ChatDAOImpl();
		}
		return dao;
	}

//	@Override
//	public List<ChatListVO> getAllChatRooms(int member_idx) {
//		List<ChatListVO> list = null;
//
//		try {
//			list = smc.queryForList("chat.getAllChatList", member_idx);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public List<ChatDetailVO> getOneChatRoom(ChatDetailVO cdvo) {
		List<ChatDetailVO> list = null;
		try {
			list = smc.queryForList("chat.getOneChat", cdvo);
//			for (ChatDetailVO chatDetailVO : list) {
//				System.out.println("반환값 "+chatDetailVO);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean delChatRoom(ChatRoomVO crvo, ChatDetailVO cdvo) {
		boolean flag = false;

		try {
			if (smc.delete("chat.deleteChatDetail", cdvo) > 0
					&& smc.delete("chat.deleteChatRoom", crvo.getchat_room_idx()) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean createChatRoom(ChatRoomVO crvo) {
		boolean flag = false;

		try {
			Object obj = smc.insert("chat.insertChatRoom", crvo);

			if (obj == null) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean sendChat(ChatDetailVO cdvo) {
		boolean flag = false;

		try {
			Object obj = smc.insert("chat.sendChat", cdvo);

			if (obj == null) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<String> getMemberName(int room) {
		List<String> list = null;
		
		try {
			list = (List<String>)smc.queryForList("chat.getMemberName", room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String getLastContent(int chat_room_idx) {
		String str = null;
		
		try {
			str = (String)smc.queryForObject("chat.getLastContent", chat_room_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	public List<Integer> getRoomList(int member_idx) {
		List<Integer> list = null;
		try {
			list = smc.queryForList("chat.getRoomList", member_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<String> getRoomUser(int chat_room_idx) {
		List<String> list = null;
		
		try {
			list = smc.queryForList("chat.getRoomUser", chat_room_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Integer> searchChatRoom(String member_name, MemberVO user) {
		List<Integer> list = null;
		ChatDetailVO vo = new ChatDetailVO();
		vo.setMember_name(member_name);
		vo.setMember_idx(user.getMember_idx());
		
		try {
			list = smc.queryForList("chat.searchChatRoom", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int getSeq() {
		int seq = 0;
		try {
			seq = (int)smc.queryForObject("chat.getSeq");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seq;
	}
}
