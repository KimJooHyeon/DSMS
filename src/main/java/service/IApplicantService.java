package service;

import java.util.List;
import java.util.Map;

import vo.ApplicantVO;

public interface IApplicantService {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 지원자 추가 후 성공 시 true
	 */
	boolean addApplicant(ApplicantVO avo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 지원자 삭제 후 성공 시 true
	 */
	boolean delApplicant(ApplicantVO avo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 지원자 수정 후 성공 시 true
	 */
	boolean modifyApplicant(ApplicantVO avo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 지원자 리스트 반환
	 */
	List<ApplicantVO> getAllApplicant();
	
	List<ApplicantVO> getAllApplicantPaging(Map map);
}