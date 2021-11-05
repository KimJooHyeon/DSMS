package vo;

public class PageVO {

	private int page; // 페이지 번호
	private int pageSize; // 페이지당 건수
	private int paginationSize; // 조회할 페이지 개수

	

	public PageVO(int page, int pageSize, int paginationSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.paginationSize = paginationSize;
	}



	public int getPaginationSize() {
		return paginationSize;
	}



	public void setPaginationSize(int paginationSize) {
		this.paginationSize = paginationSize;
	}



	public PageVO() {

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + ", paginationSize=" + paginationSize + "]";
	}


}
