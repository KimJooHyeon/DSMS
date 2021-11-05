package util;

import java.util.List;

import vo.BoardVO;

public class Paging {
	// 총 페이지 수
	private int totalPageCnt;
	// 시작 페이지
	private int startPage;
	// 마지막 페이지
	private int endPage;
	// 현재 페이지
	private int nowPage;
	// 게시글 수
	private int totalArticleCnt;
	// 한 페이지에 보여줄 게시글 수
	private int onePageArticleCnt;
	
	public Paging(int totalArticleCnt, int onePageArticleCnt, int nowPage) {
		this.totalArticleCnt = totalArticleCnt;
		this.onePageArticleCnt = onePageArticleCnt;
		this.nowPage = nowPage;
		
		if(totalArticleCnt % onePageArticleCnt == 0) {
			totalPageCnt = totalArticleCnt / onePageArticleCnt;
		} else {
			totalPageCnt = totalArticleCnt / onePageArticleCnt + 1;
		}
		
		this.startPage = Math.max(1, nowPage - 4);
		this.endPage = Math.min(totalPageCnt, nowPage + 5);
	}
	
	public List<BoardVO> showBoardList() {
//		NewBoardDAO dao = NewBoardDAO.getInstance();
//		List<BoardVO> list = dao.paging(nowPage * 10 - 9, nowPage * 10);		
//		return list;
		return null;
	}
	
	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalArticleCnt() {
		return totalArticleCnt;
	}

	public void setTotalArticleCnt(int totalArticleCnt) {
		this.totalArticleCnt = totalArticleCnt;
	}

	public int getOnePageArticleCnt() {
		return onePageArticleCnt;
	}

	public void setOnePageArticleCnt(int onePageArticleCnt) {
		this.onePageArticleCnt = onePageArticleCnt;
	}
}
