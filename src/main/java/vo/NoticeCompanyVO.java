package vo;

public class NoticeCompanyVO {
    private String company_idx;
    private String company_name;
    private String company_addr;
    private int company_salary;
    private String company_section;
    private int recruit_intake;
    private int latitude;
    private int longitude;
	public String getCompany_idx() {
		return company_idx;
	}
	public void setCompany_idx(String company_idx) {
		this.company_idx = company_idx;
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
	public int getCompany_salary() {
		return company_salary;
	}
	public void setCompany_salary(int company_salary) {
		this.company_salary = company_salary;
	}
	public String getCompany_section() {
		return company_section;
	}
	public void setCompany_section(String company_section) {
		this.company_section = company_section;
	}
	public int getRecuruit_intake() {
		return recruit_intake;
	}
	public void setRecuruit_intake(int recruit_intake) {
		this.recruit_intake = recruit_intake;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public NoticeCompanyVO(String company_idx, String company_name, String company_addr, int company_salary,
			String company_section, int recruit_intake, int latitude, int longitude) {
		this.company_idx = company_idx;
		this.company_name = company_name;
		this.company_addr = company_addr;
		this.company_salary = company_salary;
		this.company_section = company_section;
		this.recruit_intake = recruit_intake;
		this.latitude = latitude;
		this.longitude = longitude;
	}
    public NoticeCompanyVO() {}
}
