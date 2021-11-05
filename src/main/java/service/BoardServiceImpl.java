package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import dao.BoardDAOImpl;
import vo.ApplicantVO;
import vo.BoardVO;
import vo.CommentNameVO;
import vo.CommentVO;
import vo.FileVO;

public class BoardServiceImpl implements IBoardService {
	
	private static BoardServiceImpl instance = null;
	BoardDAOImpl dao = null;

	private BoardServiceImpl() {
		if (dao == null)
			dao = BoardDAOImpl.getInstance();
	}

	public static BoardServiceImpl getInstance() {
		if (instance == null)
			instance = new BoardServiceImpl();
		return instance;
	}
	
		
	
	@Override
	public int deleteBoard(int board_idx) {
		return dao.deleteBoard(board_idx);
	}

	@Override
	public int modifyArticle(BoardVO vo) {
		return dao.modifyArticle(vo);
	}

	@Override
	public List<BoardVO> getLatestPost() {
		List<BoardVO> list = dao.getLatestPost();
		
		if(list != null) {
			for(BoardVO item : list) {
				String category = item.getBoard_category();
				if(category == null) {
					category = item.getBoard_idx() + "";	
				} else {
					int num = Integer.parseInt(category.trim());
					if(num == 1) {
						category = "공지";
					} else if(num == 2) {
						category = "긴급";
					}					
				}
				item.setBoard_category(category);
			}
		}
		
		return list;
	}

	@Override
	public int writeArticle(Map<String, Object>paramMap) {
		BoardVO boardInsert = (BoardVO) paramMap.get("boardVO");
		FileVO vo = (FileVO) paramMap.get("fileVO");
		
		int cnt = 0;
		int board_idx= dao.writeArticle(boardInsert);

		
		if(board_idx>0 && vo!=null) {
			 vo.setBoard_idx(board_idx);
			 cnt =dao.filesave(vo);
		}else {
			 cnt = board_idx;
		}
		return cnt;
	}
	
	@Override
	public CommentNameVO writeComment(CommentVO vo) {
		
		int cnt = dao.writeComment(vo);
		
		if(cnt > 0) {
			return dao.getLastComment(vo);			
		}
		
		return null;
	}
	
	@Override
	public List<BoardVO> getAllArticle(Map<String, Object> paramMap) {
		List<BoardVO> list = null;
		try {
			list = dao.getAllArticle(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BoardVO getOneArticle(BoardVO vo) {
		BoardVO boardInfo = new BoardVO();
		boardInfo = dao.getOneArticle(vo);
		return boardInfo;
	}


	@Override
	public int modifyCommnet(CommentVO vo) {
		return dao.modifyCommnet(vo);
	}

	@Override
	public List<Map<String, Object>> getAllComment(int board_idx) {
		List<Map<String, Object>> list = null;
		try {
			list = dao.getAllComment(board_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BoardVO> getList(BoardVO boardVO) {

		return null;
	}


	@Override
	public int updateBoardHit(int board_idx) {
		return	dao.updateBoardHit(board_idx);
	}

	@Override
	public int deleteComment(CommentVO vo) {
		return dao.deleteComment(vo);
	}

	@Override
	public List<FileVO> getAllFile(int board_idx) {
		return dao.getAllFile(board_idx);
	}

	

	@Override
	public int boardCount() {
		int cnt = 0;
		try {
			cnt = dao.boardCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
