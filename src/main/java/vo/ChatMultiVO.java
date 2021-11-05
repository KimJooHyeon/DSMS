package vo;

import javax.websocket.Session;

public class ChatMultiVO {
	private String member_name;
	private Session session;

	public ChatMultiVO() {
		// TODO Auto-generated constructor stub
	}

	public ChatMultiVO(String member_name, Session session) {
		super();
		this.member_name = member_name;
		this.session = session;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
