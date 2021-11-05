package vo;

public class Schedule {
	private String member_name;
	private int schedule_idx;
    private int member_idx;
    private String schedule_title;
    private String start_date;
    private String end_date;
    private String schedule_content;
    private String text_color;
    private String background_color;
    private String schedule_type;
    
    public Schedule() {}

	public Schedule(String member_name, int schedule_idx, int member_idx, String schedule_title, String start_date,
			String end_date, String schedule_content, String text_color, String background_color,
			String schedule_type) {
		this.member_name = member_name;
		this.schedule_idx = schedule_idx;
		this.member_idx = member_idx;
		this.schedule_title = schedule_title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.schedule_content = schedule_content;
		this.text_color = text_color;
		this.background_color = background_color;
		this.schedule_type = schedule_type;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public int getSchedule_idx() {
		return schedule_idx;
	}

	public void setSchedule_idx(int schedule_idx) {
		this.schedule_idx = schedule_idx;
	}

	public int getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}

	public String getSchedule_title() {
		return schedule_title;
	}

	public void setSchedule_title(String schedule_title) {
		this.schedule_title = schedule_title;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getSchedule_content() {
		return schedule_content;
	}

	public void setSchedule_content(String schedule_content) {
		this.schedule_content = schedule_content;
	}

	public String getText_color() {
		return text_color;
	}

	public void setText_color(String text_color) {
		this.text_color = text_color;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getSchedule_type() {
		return schedule_type;
	}

	public void setSchedule_type(String schedule_type) {
		this.schedule_type = schedule_type;
	}

	@Override
	public String toString() {
		return "Schedule [member_name=" + member_name + ", schedule_idx=" + schedule_idx + ", member_idx=" + member_idx
				+ ", schedule_title=" + schedule_title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", schedule_content=" + schedule_content + ", text_color=" + text_color + ", background_color="
				+ background_color + ", schedule_type=" + schedule_type + "]";
	}
	
    
}
