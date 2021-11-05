package vo;

public class StudentVO {
    private int student_idx;
    private String student_name;
    private String student_addr;
    private int student_age;
    private String student_tel;
    private String student_education;
    private String student_gradute;
    
    public StudentVO() {}
    
	public StudentVO(int student_idx, String student_name, String student_addr, int student_age, String student_tel,
			String student_education, String student_gradute) {
		super();
		this.student_idx = student_idx;
		this.student_name = student_name;
		this.student_addr = student_addr;
		this.student_age = student_age;
		this.student_tel = student_tel;
		this.student_education = student_education;
		this.student_gradute = student_gradute;
	}
	public int getStudent_idx() {
		return student_idx;
	}
	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getStudent_addr() {
		return student_addr;
	}
	public void setStudent_addr(String student_addr) {
		this.student_addr = student_addr;
	}
	public int getStudent_age() {
		return student_age;
	}
	public void setStudent_age(int student_age) {
		this.student_age = student_age;
	}
	public String getStudent_tel() {
		return student_tel;
	}
	public void setStudent_tel(String student_tel) {
		this.student_tel = student_tel;
	}
	public String getStudent_education() {
		return student_education;
	}
	public void setStudent_education(String student_education) {
		this.student_education = student_education;
	}
	public String getStudent_gradute() {
		return student_gradute;
	}
	public void setStudent_gradute(String student_gradute) {
		this.student_gradute = student_gradute;
	}

	@Override
	public String toString() {
		return "StudentVO [student_idx=" + student_idx + ", student_name=" + student_name + ", student_addr="
				+ student_addr + ", student_age=" + student_age + ", student_tel=" + student_tel
				+ ", student_education=" + student_education + ", student_gradute=" + student_gradute + "]";
	}
	
	
}
