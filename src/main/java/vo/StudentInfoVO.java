package vo;

public class StudentInfoVO {
	private int student_idx;
	private String student_name;
	private String student_age;
	private String class_number;
	private String student_addr;
	private String student_tel;
	private String license_name;
	private String student_education;
	private int basic_project;
	private int middle_project;
	private int final_project;
	private String student_graduate;
	
	public StudentInfoVO() {}

	public StudentInfoVO(int student_idx, String student_name, String student_age, String class_number,
			String student_addr, String student_tel, String license_name, String student_education, int basic_project,
			int middle_project, int final_project, String student_graduate) {
		this.student_idx = student_idx;
		this.student_name = student_name;
		this.student_age = student_age;
		this.class_number = class_number;
		this.student_addr = student_addr;
		this.student_tel = student_tel;
		this.license_name = license_name;
		this.student_education = student_education;
		this.basic_project = basic_project;
		this.middle_project = middle_project;
		this.final_project = final_project;
		this.student_graduate = student_graduate;
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

	public String getStudent_age() {
		return student_age;
	}

	public void setStudent_age(String student_age) {
		this.student_age = student_age;
	}

	public String getClass_number() {
		return class_number;
	}

	public void setClass_number(String class_number) {
		this.class_number = class_number;
	}

	public String getStudent_addr() {
		return student_addr;
	}

	public void setStudent_addr(String student_addr) {
		this.student_addr = student_addr;
	}

	public String getStudent_tel() {
		return student_tel;
	}

	public void setStudent_tel(String student_tel) {
		this.student_tel = student_tel;
	}

	public String getLicense_name() {
		return license_name;
	}

	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}

	public String getStudent_education() {
		return student_education;
	}

	public void setStudent_education(String student_education) {
		this.student_education = student_education;
	}

	public int getBasic_project() {
		return basic_project;
	}

	public void setBasic_project(int basic_project) {
		this.basic_project = basic_project;
	}

	public int getMiddle_project() {
		return middle_project;
	}

	public void setMiddle_project(int middle_project) {
		this.middle_project = middle_project;
	}

	public int getFinal_project() {
		return final_project;
	}

	public void setFinal_project(int final_project) {
		this.final_project = final_project;
	}

	public String getStudent_graduate() {
		return student_graduate;
	}

	public void setStudent_graduate(String student_graduate) {
		this.student_graduate = student_graduate;
	}

	@Override
	public String toString() {
		return "StudentInfoVO [student_idx=" + student_idx + ", student_name=" + student_name + ", student_age="
				+ student_age + ", class_number=" + class_number + ", student_addr=" + student_addr + ", student_tel="
				+ student_tel + ", license_name=" + license_name + ", student_education=" + student_education
				+ ", basic_project=" + basic_project + ", middle_project=" + middle_project + ", final_project="
				+ final_project + ", student_graduate=" + student_graduate + "]";
	}

	
	
}
