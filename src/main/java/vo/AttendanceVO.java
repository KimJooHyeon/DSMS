package vo;

public class AttendanceVO {
	private int student_idx;
	private String attend_date;
	private String attend_in_time;
	private String attend_out_time;
	private String attend_division;
	
	public AttendanceVO() {	}
	
	
	
	public AttendanceVO(int student_idx, String attend_date, String attend_in_time, String attend_out_time,
			String attend_division) {
		super();
		this.student_idx = student_idx;
		this.attend_date = attend_date;
		this.attend_in_time = attend_in_time;
		this.attend_out_time = attend_out_time;
		this.attend_division = attend_division;
	}



	public int getStudent_idx() {
		return student_idx;
	}



	public void setStudent_idx(int student_idx) {
		this.student_idx = student_idx;
	}



	public String getAttend_date() {
		return attend_date;
	}



	public void setAttend_date(String attend_date) {
		this.attend_date = attend_date;
	}



	public String getAttend_in_time() {
		return attend_in_time;
	}



	public void setAttend_in_time(String attend_in_time) {
		this.attend_in_time = attend_in_time;
	}



	public String getAttend_out_time() {
		return attend_out_time;
	}



	public void setAttend_out_time(String attend_out_time) {
		this.attend_out_time = attend_out_time;
	}



	public String getAttend_division() {
		return attend_division;
	}



	public void setAttend_division(String attend_division) {
		this.attend_division = attend_division;
	}



	@Override
	public String toString() {
		return "AttendanceVO [student_idx=" + student_idx + ", attend_date=" + attend_date + ", attend_in_time="
				+ attend_in_time + ", attend_out_time=" + attend_out_time + ", attend_division=" + attend_division
				+ "]";
	}
	
	
	
	
}
