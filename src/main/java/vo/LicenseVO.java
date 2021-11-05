package vo;

public class LicenseVO {
    private int student_idx;
    private String license_name;
    
    public LicenseVO() {
		// TODO Auto-generated constructor stub
	}
    
	public LicenseVO(int student_idx, String license_name) {
		super();
		this.student_idx = student_idx;
		this.license_name = license_name;
	}
	public int getStudent_idx() {
		return student_idx;
	}
	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}
	public String getLicense_name() {
		return license_name;
	}
	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}
	@Override
	public String toString() {
		return "LicenseVO [student_idx=" + student_idx + ", license_name=" + license_name + "]";
	}
    
    
}
