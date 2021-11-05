package service;

import java.util.List;

import vo.NoticeCompanyVO;
import vo.RankVO;
import vo.WorkCompanyVO;

public interface ICompanyService {
	/**
	 * 
	 * @param wvo
	 * @return 취업한 회사 추가 후 성공 시 true
	 */
	boolean addWorkCompany(WorkCompanyVO wvo);
	
	/**
	 * 
	 * @param wvo
	 * @return 취업한 회사 삭제 후 성공 시 true
	 */
	boolean delWorkCompany(WorkCompanyVO wvo);
	
	/**
	 * 
	 * @return 취업한 회사 리스트 반환
	 */
	List<String> getAllWorkCompany();
	
	/**
	 * 
	 * @param wvo
	 * @return 취업한 회사 정보 반환
	 */
	WorkCompanyVO getOneWorkCompany(WorkCompanyVO wvo);
	
	/**
	 * 
	 * @param wvo
	 * @return 취업한 회사 정보 수정 후 성공 시 true
	 */
	boolean modifyWorkCompany(WorkCompanyVO wvo);
	
	/**
	 * 
	 * @param wvo
	 * @return 취업한 회사 중 top5 반환
	 */
	List<RankVO> getWorkCompanyRank();
	
	/**
	 * 
	 * @param nvo
	 * @return 공고 회사 추가 후 성공 시 true
	 */
	boolean addNoticeCompany(NoticeCompanyVO nvo);
	
	/**
	 * 
	 * @param nvo
	 * @return 공고 회사 삭제 후 성공 시 true
	 */
	boolean delNoticeCompany(NoticeCompanyVO nvo);
	
	/**
	 * 
	 * @return 공고 회사 리스트 반환
	 */
	List<NoticeCompanyVO> getAllNoticeCompany();
	
	/**
	 * 
	 * @param nvo
	 * @return 공고 회사 정보 반환
	 */
	NoticeCompanyVO getOneNoticeCompany(NoticeCompanyVO nvo);
	
	/**
	 * 
	 * @param nvo
	 * @return 공고 회사 정보 수정
	 */
	boolean modifyNoticeCompany(NoticeCompanyVO nvo);
}
