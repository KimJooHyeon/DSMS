package vo;

public class ChatDetailVO {
	private int chat_detail_idx;
	private int chat_room_idx;
	private int member_idx;
	private String chat_detail_content;
	private String chat_detail_date;
	private String member_name;
	private String all_member_name;

	public ChatDetailVO() {
	}

	public ChatDetailVO(int chat_detail_idx, int chat_room_idx, int member_idx, String chat_detail_content,
			String chat_detail_date, String member_name, String all_member_name) {
		super();
		this.chat_detail_idx = chat_detail_idx;
		this.chat_room_idx = chat_room_idx;
		this.member_idx = member_idx;
		this.chat_detail_content = chat_detail_content;
		this.chat_detail_date = chat_detail_date;
		this.member_name = member_name;
		this.all_member_name = all_member_name;
	}

	public int getChat_detail_idx() {
		return chat_detail_idx;
	}

	public void setChat_detail_idx(int chat_detail_idx) {
		this.chat_detail_idx = chat_detail_idx;
	}

	public int getChat_room_idx() {
		return chat_room_idx;
	}

	public void setChat_room_idx(int chat_room_idx) {
		this.chat_room_idx = chat_room_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getChat_detail_content() {
		return chat_detail_content;
	}

	public void setChat_detail_content(String chat_detail_content) {
		this.chat_detail_content = chat_detail_content;
	}

	public String getChat_detail_date() {
		return chat_detail_date;
	}

	public void setChat_detail_date(String chat_detail_date) {
		this.chat_detail_date = chat_detail_date;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getAll_member_name() {
		return all_member_name;
	}

	public void setAll_member_name(String all_member_name) {
		this.all_member_name = all_member_name;
	}

	@Override
	public String toString() {
		return "ChatDetailVO [chat_detail_idx=" + chat_detail_idx + ", chat_room_idx=" + chat_room_idx + ", member_idx="
				+ member_idx + ", chat_detail_content=" + chat_detail_content + ", chat_detail_date=" + chat_detail_date
				+ ", member_name=" + member_name + ", all_member_name=" + all_member_name + "]";
	}

}
