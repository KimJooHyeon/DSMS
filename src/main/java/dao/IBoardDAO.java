package dao;

import java.util.List;
import java.util.Map;

import vo.ApplicantVO;
import vo.BoardVO;
import vo.CommentNameVO;
import vo.CommentVO;
import vo.FileVO;

public interface IBoardDAO {
	
	int contList();
	
	int boardCount();
	
	int writeArticle(BoardVO bvo);
	
	int modifyArticle(BoardVO bvo);
	
	BoardVO getOneArticle(BoardVO bvo);
	
	int writeComment(CommentVO cvo);

	int deleteComment(CommentVO cvo);
	
	int modifyCommnet(CommentVO cvo);
	
	List<BoardVO> getLatestPost(CommentVO cvo);

	List<BoardVO> getAllArticle(Map<String, Object> paramMap);

	int deleteBoard(int board_idx);
	
	int updateBoardHit(int board_idx);

	List<Map<String, Object>> getAllComment(int board_idx);
	
	int filesave(FileVO vo);
	
	List<FileVO> getAllFile(int board_idx);
	
	CommentNameVO getLastComment(CommentVO vo);
	
	List<BoardVO> getLatestPost();
}
