package vo;

public class ChatRoomVO {
	private int chat_room_idx;
	private int member_idx;
	private String member_chat_attend;

	public ChatRoomVO() {
	}

	public ChatRoomVO(int chat_room_idx, int member_idx, String member_chat_attend) {
		super();
		this.chat_room_idx = chat_room_idx;
		this.member_idx = member_idx;
		this.member_chat_attend = member_chat_attend;
	}

	public int getchat_room_idx() {
		return chat_room_idx;
	}

	public void setchat_room_idx(int chat_room_idx) {
		this.chat_room_idx = chat_room_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_chat_attend() {
		return member_chat_attend;
	}

	public void setMember_chat_attend(String member_chat_attend) {
		this.member_chat_attend = member_chat_attend;
	}

	@Override
	public String toString() {
		return "ChatRoomVO [chat_room_idx=" + chat_room_idx + ", member_idx=" + member_idx + ", member_chat_attend="
				+ member_chat_attend + "]";
	}

}
