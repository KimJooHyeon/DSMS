package vo;

public class BoardVO {
	private int board_idx;
	private int member_idx;
	private String board_category;
	private String board_title;
	private String board_content;
	private int board_hits;
	private String board_date;
	private String member_name;
	
	@Override
	public String toString() {
		return "BoardVO [board_idx=" + board_idx + ", member_idx=" + member_idx + ", board_category=" + board_category
				+ ", board_title=" + board_title + ", board_content=" + board_content + ", board_hits=" + board_hits
				+ ", board_date=" + board_date + ", member_name=" + member_name + "]";
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getBoard_category() {
		return board_category;
	}

	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_hits() {
		return board_hits;
	}

	public void setboard_hits(int board_hits) {
		this.board_hits = board_hits;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}


}
