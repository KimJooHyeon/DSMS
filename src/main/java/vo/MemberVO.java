package vo;

public class MemberVO {
    private int member_idx;
    private String member_id;
    private String member_pass;
    private String member_name;
    private String member_regno;
    private String member_tel;
    private String member_email;
    private String member_job;
    private String member_state;
    
    public MemberVO() {}

	public MemberVO(int member_idx, String member_id, String member_pass, String member_name, String member_regno,
			String member_tel, String member_email, String member_job, String member_state) {
		super();
		this.member_idx = member_idx;
		this.member_id = member_id;
		this.member_pass = member_pass;
		this.member_name = member_name;
		this.member_regno = member_regno;
		this.member_tel = member_tel;
		this.member_email = member_email;
		this.member_job = member_job;
		this.member_state = member_state;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pass() {
		return member_pass;
	}

	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_regno() {
		return member_regno;
	}

	public void setMember_regno(String member_regno) {
		this.member_regno = member_regno;
	}

	public String getMember_tel() {
		return member_tel;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_job() {
		return member_job;
	}

	public void setMember_job(String member_job) {
		this.member_job = member_job;
	}

	public String getMember_state() {
		return member_state;
	}

	public void setMember_state(String member_state) {
		this.member_state = member_state;
	}

	@Override
	public String toString() {
		return "MemberVO [member_idx=" + member_idx + ", member_id=" + member_id + ", member_pass=" + member_pass
				+ ", member_name=" + member_name + ", member_regno=" + member_regno + ", member_tel=" + member_tel
				+ ", member_email=" + member_email + ", member_job=" + member_job + ", member_state=" + member_state
				+ "]";
	}
    
}