package vo;

public class ClassRoomVO {
	private int member_idx;
	private int student_idx;
	private int class_number;
	
	public ClassRoomVO() {}

	public ClassRoomVO(int member_idx, int student_idx, int class_number) {
		super();
		this.member_idx = member_idx;
		this.student_idx = student_idx;
		this.class_number = class_number;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public int getStudent_idx() {
		return student_idx;
	}

	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}

	public int getClass_number() {
		return class_number;
	}

	public void setClass_number(int class_number) {
		this.class_number = class_number;
	}

	@Override
	public String toString() {
		return "ClassRoomVO [member_idx=" + member_idx + ", student_idx=" + student_idx + ", class_number="
				+ class_number + "]";
	}
	
	
}
