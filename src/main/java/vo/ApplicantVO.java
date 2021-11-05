package vo;


public class ApplicantVO {
	private int applicant_idx;
	private String applicant_name;
	private int applicant_age;
	private String applicant_tel;
	private String applicant_pass;
	private int applicant_score;
	
	public ApplicantVO() {
		// TODO Auto-generated constructor stub
	}

	public ApplicantVO(int applicant_idx, String applicant_name, int applicant_age, String applicant_tel,
			String applicant_pass, int applicant_score) {
		super();
		this.applicant_idx = applicant_idx;
		this.applicant_name = applicant_name;
		this.applicant_age = applicant_age;
		this.applicant_tel = applicant_tel;
		this.applicant_pass = applicant_pass;
		this.applicant_score = applicant_score;
	}

	public int getApplicant_idx() {
		return applicant_idx;
	}

	public void setApplicant_idx(int applicant_idx) {
		this.applicant_idx = applicant_idx;
	}

	public String getApplicant_name() {
		return applicant_name;
	}

	public void setApplicant_name(String applicant_name) {
		this.applicant_name = applicant_name;
	}

	public int getApplicant_age() {
		return applicant_age;
	}

	public void setApplicant_age(int applicant_age) {
		this.applicant_age = applicant_age;
	}

	public String getApplicant_tel() {
		return applicant_tel;
	}

	public void setApplicant_tel(String applicant_tel) {
		this.applicant_tel = applicant_tel;
	}

	public String getApplicant_pass() {
		return applicant_pass;
	}

	public void setApplicant_pass(String applicant_pass) {
		this.applicant_pass = applicant_pass;
	}

	public int getApplicant_score() {
		return applicant_score;
	}

	public void setApplicant_score(int applicant_score) {
		this.applicant_score = applicant_score;
	}

	@Override
	public String toString() {
		return "ApplicantVO [applicant_idx=" + applicant_idx + ", applicant_name=" + applicant_name + ", applicant_age="
				+ applicant_age + ", applicant_tel=" + applicant_tel + ", applicant_pass=" + applicant_pass
				+ ", applicant_score=" + applicant_score + "]";
	}
	
	
}
