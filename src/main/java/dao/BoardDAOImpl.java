package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.ApplicantVO;
import vo.BoardVO;
import vo.CommentNameVO;
import vo.CommentVO;
import vo.FileVO;

public class BoardDAOImpl implements IBoardDAO {
	
	private static BoardDAOImpl instance = null;
	private static SqlMapClient smc = null;	
	private BoardDAOImpl() {
		if(smc == null) smc = SqlMapClientFactory.getSqlMapClient(); 
	}
	public static BoardDAOImpl getInstance() {
		if(instance == null) instance = new BoardDAOImpl();
		return instance;
	}
	
	@Override
	public int writeArticle(BoardVO bvo) {
		int cnt = 0;
		try {
//			Object obj = smc.insert("board.insertBoard",bvo);
//			if (obj==null) {
//				cnt = 1;
//			}
			cnt = (int)smc.insert("board.insertBoard",bvo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public int modifyArticle(BoardVO boardup) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", boardup);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	
	@Override
	public List<BoardVO> getAllArticle(Map<String, Object> paramMap) {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("board.selectLatestBoard", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> getAllComment(int board_idx) {
		List<Map<String, Object>> list = null;
		try {
			list = smc.queryForList("board.selectComment", board_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteBoard(int board_idx) {
		int cnt = 0;
		try {
			cnt = (int)smc.delete("board.deleteBoard",board_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public BoardVO getOneArticle(BoardVO bvo) {
		BoardVO boardInfo = null;
		try {
			boardInfo = (BoardVO) smc.queryForObject("board.selectBoard", bvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardInfo;
	}

	@Override
	public int updateBoardHit(int board_idx) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoardHit",board_idx);
			} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		}
	
	@Override
	public int writeComment(CommentVO cvo) {
		try {
			Object obj = smc.insert("board.insertComment",cvo);
			if(obj == null) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int modifyCommnet(CommentVO cvo) {
		int cnt = 0;
		try {
			cnt = (int) smc.update("board.updateComment",cvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	

	@Override
	public List<BoardVO> getLatestPost(CommentVO cvo) {
		return null;
	}
	
	
	@Override
	public int deleteComment(CommentVO cvo) {
		int cnt = 0;
		try {
			cnt = (int) smc.delete("board.deleteComment",cvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	@Override
	public int filesave(FileVO vo) {
		int cnt = 0;
		try {
			cnt = (int) smc.insert("board.fileSave",vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	@Override
	public List<FileVO> getAllFile(int board_idx) {
		List<FileVO> list = null;
		try {
			list = smc.queryForList("board.getAllFile", board_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int boardCount() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("board.countList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public CommentNameVO getLastComment(CommentVO vo) {
		CommentNameVO result = null;
		try {
			result = (CommentNameVO)smc.queryForObject("board.getLastComment", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int contList() {
		int cnt = 0;
		try {
			cnt = (int) smc.queryForObject("board.countList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public List<BoardVO> getLatestPost() {
		List<BoardVO> list = null;
		try {
			list = smc.queryForList("board.getNewArticle");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
