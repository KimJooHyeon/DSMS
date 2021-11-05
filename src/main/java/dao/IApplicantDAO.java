package dao;

import java.util.List;
import java.util.Map;

import vo.ApplicantVO;


public interface IApplicantDAO {

	boolean addApplicant(ApplicantVO avo);
	
	boolean delApplicant(ApplicantVO avo);
		
	boolean modifyApplicant(ApplicantVO avo);
		
	List<ApplicantVO> getAllApplicant();
	
	List<ApplicantVO> getAllApplicantPaging(Map map);

	int getAllApplicantCount();
	
}
