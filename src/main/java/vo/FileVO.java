package vo;

public class FileVO {
	private String file_name;
	private int board_idx;
	private String file_path;
	private String file_type;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "FileVO [file_name=" + file_name + ", board_idx=" + board_idx + ", file_path=" + file_path
				+ ", file_type=" + file_type + "]";
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public FileVO(String file_name, int board_idx, String file_path) {
		super();
		this.file_name = file_name;
		this.board_idx = board_idx;
		this.file_path = file_path;
	}

	public FileVO() {
	}

}
