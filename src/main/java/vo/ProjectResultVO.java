package vo;

public class ProjectResultVO {
    private int student_idx;
    private String project_division;
    private String project_content;
	
    public ProjectResultVO() {
		// TODO Auto-generated constructor stub
	}
    
    public ProjectResultVO(int student_idx, String project_division, String project_content) {
		super();
		this.student_idx = student_idx;
		this.project_division = project_division;
		this.project_content = project_content;
	}

	public int getStudent_idx() {
		return student_idx;
	}

	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}

	public String getProject_division() {
		return project_division;
	}

	public void setProject_division(String project_division) {
		this.project_division = project_division;
	}

	public String getProject_content() {
		return project_content;
	}

	public void setProject_content(String project_content) {
		this.project_content = project_content;
	}

	@Override
	public String toString() {
		return "ProjectResultVO [student_idx=" + student_idx + ", project_division=" + project_division
				+ ", project_content=" + project_content + "]";
	}
    
    
}
