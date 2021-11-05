package vo;

public class CounselingVO {
    private int counseling_idx;
    private int teacher_idx;
    private int student_idx;
    private String counseling_title;
    private String counseling_date;
    private String counseling_content;
	
    public CounselingVO() {
	}

	public CounselingVO(int counseling_idx, int teacher_idx, int student_idx, String counseling_title,
			String counseling_date, String counseling_content) {
		super();
		this.counseling_idx = counseling_idx;
		this.teacher_idx = teacher_idx;
		this.student_idx = student_idx;
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

	public int getStudent_idx() {
		return student_idx;
	}

	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
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
		return "CounselingVO [counseling_idx=" + counseling_idx + ", teacher_idx=" + teacher_idx + ", student_idx="
				+ student_idx + ", counseling_title=" + counseling_title + ", counseling_date=" + counseling_date
				+ ", counseling_content=" + counseling_content + "]";
	}
    
    
	
}
