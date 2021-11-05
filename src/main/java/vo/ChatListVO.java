package vo;

public class ChatListVO {
	private String chat_detail_content;
	private String member_name;
	private int chat_room_idx;
	private String chat_detail_date;

	public ChatListVO() {
		// TODO Auto-generated constructor stub
	}

	public ChatListVO(String chat_detail_content, String member_name, int chat_room_idx, String chat_detail_date) {
		super();
		this.chat_detail_content = chat_detail_content;
		this.member_name = member_name;
		this.chat_room_idx = chat_room_idx;
		this.chat_detail_date = chat_detail_date;
	}

	public String getChat_detail_content() {
		return chat_detail_content;
	}

	public void setChat_detail_content(String chat_detail_content) {
		this.chat_detail_content = chat_detail_content;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public int getChat_room_idx() {
		return chat_room_idx;
	}

	public void setChat_room_idx(int chat_room_idx) {
		this.chat_room_idx = chat_room_idx;
	}

	public String getChat_detail_date() {
		return chat_detail_date;
	}

	public void setChat_detail_date(String chat_detail_date) {
		this.chat_detail_date = chat_detail_date;
	}

	@Override
	public String toString() {
		return "ChatListVO [chat_detail_content=" + chat_detail_content + ", member_name=" + member_name
				+ ", chat_room_idx=" + chat_room_idx + ", chat_detail_date=" + chat_detail_date + "]";
	}

}
