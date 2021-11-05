package vo;

public class CounselingNameVO {
	private int counseling_idx;
    private int teacher_idx;
    private String student_name;
    private String counseling_title;
    private String counseling_date;
    private String counseling_content;
    
    public CounselingNameVO() {}

	public CounselingNameVO(int counseling_idx, int teacher_idx, String student_name, String counseling_title,
			String counseling_date, String counseling_content) {
		super();
		this.counseling_idx = counseling_idx;
		this.teacher_idx = teacher_idx;
		this.student_name = student_name;
		this.counseling_title = counseling_title;
		this.counseling_date = counseling_date;
		this.counseling_content = counseling_content;
	}

	public int getCounseling_idx() {
		return counseling_idx;
	}

	public void setCounseling_idx(int counseling_idx) {
		this.counseling_idx = counseling_idx;
	}

	public int getTeacher_idx() {
		return teacher_idx;
	}

	public void setTeacher_idx(int teacher_idx) {
		this.teacher_idx = teacher_idx;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getCounseling_title() {
		return counseling_title;
	}

	public void setCounseling_title(String counseling_title) {
		this.counseling_title = counseling_title;
	}

	public String getCounseling_date() {
		return counseling_date;
	}

	public void setCounseling_date(String counseling_date) {
		this.counseling_date = counseling_date;
	}

	public String getCounseling_content() {
		return counseling_content;
	}

	public void setCounseling_content(String counseling_content) {
		this.counseling_content = counseling_content;
	}

	@Override
	public String toString() {
		return "CounselingNameVO [counseling_idx=" + counseling_idx + ", teacher_idx=" + teacher_idx + ", student_name="
				+ student_name + ", counseling_title=" + counseling_title + ", counseling_date=" + counseling_date
				+ ", counseling_content=" + counseling_content + "]";
	}
    
    
}
