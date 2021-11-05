package service;

import java.util.List;
import java.util.Map;

import vo.BoardVO;
import vo.CommentNameVO;
import vo.CommentVO;
import vo.FileVO;

public interface IBoardService {

	
	
	
	
	/**
	 * 전체 글 갯수 조회
	 * @return
	 */
	public int boardCount();
	
	
	/**
	 * 유저가 작성한 글과 유저의 정보를 받아와서 정상 등록시 true리턴
	 * @param vo
	 * @return
	 */
	int writeArticle(Map<String, Object>paramMap);
	
	/**
	 * 유저가 게시글 삭제 후 성공시 true
	 * @param member_idx
	 * @return
	 */
	int deleteBoard(int board_idx);
	
	/**
	 * 유저가 게시글 수정 후 성공시 true 
	 * @param vo
	 * @return
	 */
	int modifyArticle(BoardVO vo);
	
	/**
	 * 최신 게시글의 5개를 가져온다 
	 * @param vo
	 * @return
	 */
	List<BoardVO> getLatestPost();
	
	
	/**
	 * 유저가 작성한 게시글 리스트 정보를 받아와서 리스트 조회
	 * @param vo
	 * @return
	 */
	List<BoardVO> getAllArticle(Map<String, Object> paramMap);
	
	
	/**
	 * 게시글 상세 내역을 반환한다.
	 * @param vo
	 * @return
	 */
	BoardVO getOneArticle(BoardVO vo);
	
	
	/**
	 * 파일 리스트 조회
	 * @param vo
	 * @return
	 */
	List<FileVO> getAllFile(int board_idx);
	
	
	/**
	 * 게시글에 댓글 작성 후 성공시 true
	 * @param vo
	 * @return
	 */
	CommentNameVO writeComment(CommentVO vo);
	
	/**
	 * 게시글의 기록한 댓글을 수정후 성공시
	 * @param vo
	 * @return
	 */
	int modifyCommnet(CommentVO vo);
	
	/**
	 * 해당 게시글의 댓글 목록을 가져온다.
	 * @param vo
	 * @return
	 */
	List<Map<String, Object>> getAllComment(int board_idx);

	
	
	
	/**
	 * 조회수 증가 메서드
	 * @param board_idx
	 * @return
	 */
	int updateBoardHit(int board_idx);
	
	/**
	 * 댓글 삭제 메서드
	 * @param vo
	 * @return
	 */
	int deleteComment(CommentVO vo);
	

}
