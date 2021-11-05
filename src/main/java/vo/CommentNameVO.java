package vo;

import java.util.Date;

public class CommentNameVO {
	private int comment_idx;
    private int board_idx;
    private String comment_date;
    private String comment_content;
    private int member_idx;
    private String member_name;
    
    public CommentNameVO() {}

	public CommentNameVO(int comment_idx, int board_idx, String comment_date, String comment_content, int member_idx,
			String member_name) {
		super();
		this.comment_idx = comment_idx;
		this.board_idx = board_idx;
		this.comment_date = comment_date;
		this.comment_content = comment_content;
		this.member_idx = member_idx;
		this.member_name = member_name;
	}

	public int getComment_idx() {
		return comment_idx;
	}

	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getComment_date() {
		return comment_date;
	}

	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	@Override
	public String toString() {
		return "CommentNameVO [comment_idx=" + comment_idx + ", board_idx=" + board_idx + ", comment_date="
				+ comment_date + ", comment_content=" + comment_content + ", member_idx=" + member_idx
				+ ", member_name=" + member_name + "]";
	}
    
    
}
