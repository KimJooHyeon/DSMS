package vo;

public class StudentOfClassRoomVO {
	private int class_number;
	private String student_name;
	private String member_name;
	private String member_tel;
	
	public StudentOfClassRoomVO() {}
	
	public StudentOfClassRoomVO(int class_number, String student_name, String member_name, String member_tel) {
		this.class_number = class_number;
		this.student_name = student_name;
		this.member_name = member_name;
		this.member_tel = member_tel;
	}

	public int getClass_number() {
		return class_number;
	}
	public void setClass_number(int class_number) {
		this.class_number = class_number;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	@Override
	public String toString() {
		return "StudentOfClassRoomVO [class_number=" + class_number + ", student_name=" + student_name
				+ ", member_name=" + member_name + ", member_tel=" + member_tel + "]";
	}

	
}
