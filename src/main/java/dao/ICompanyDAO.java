package dao;

import java.util.List;

import vo.NoticeCompanyVO;
import vo.RankVO;
import vo.WorkCompanyVO;

public interface ICompanyDAO {

	public boolean addWorkCompany(WorkCompanyVO wvo);
	
	public boolean delWorkCompany(WorkCompanyVO wvo);
	
	public List<WorkCompanyVO> getAllWorkCompany();
	
	public WorkCompanyVO getOneWorkCompany(WorkCompanyVO wvo);
	
	public boolean modifyWorkCompany(WorkCompanyVO wvo);
	
	public List<RankVO> getWorkCompanyRank();
	
	public boolean addNoticeCompany(NoticeCompanyVO nvo);
	
	public boolean delNoticeCompany(NoticeCompanyVO nvo);
	
	public List<NoticeCompanyVO> getAllNoticeCompany();

	public NoticeCompanyVO getOneNoticeCompany(NoticeCompanyVO nvo);
	
	public boolean modifyNoticeCompany(NoticeCompanyVO nvo);
	
	

}
