package vo;

public class WorkCompanyVO {
	private int company_idx;
	private int student_idx;
	private String company_name;
	private String company_addr;
	private String company_addr2;
	private String company_section;
	private double latitude;
	private double longitude;

	public WorkCompanyVO() {
	}

	public WorkCompanyVO(int company_idx, int student_idx, String company_name, String company_addr,
			String company_addr2, String company_section, double latitude, double longitude) {
		super();
		this.company_idx = company_idx;
		this.student_idx = student_idx;
		this.company_name = company_name;
		this.company_addr = company_addr;
		this.company_addr2 = company_addr2;
		this.company_section = company_section;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getCompany_idx() {
		return company_idx;
	}

	public void setCompany_idx(int company_idx) {
		this.company_idx = company_idx;
	}

	public int getStudent_idx() {
		return student_idx;
	}

	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_addr() {
		return company_addr;
	}

	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}

	public String getCompany_addr2() {
		return company_addr2;
	}

	public void setCompany_addr2(String company_addr2) {
		this.company_addr2 = company_addr2;
	}

	public String getCompany_section() {
		return company_section;
	}

	public void setCompany_section(String company_section) {
		this.company_section = company_section;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "WorkCompanyVO [company_idx=" + company_idx + ", student_idx=" + student_idx + ", company_name="
				+ company_name + ", company_addr=" + company_addr + ", company_addr2=" + company_addr2
				+ ", company_section=" + company_section + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
