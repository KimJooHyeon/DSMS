package service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ApplicantDAOImpl;
import dao.IApplicantDAO;

import vo.ApplicantVO;

public class ApplicantServiceImpl implements IApplicantService {
	private static IApplicantDAO dao;
	private static IApplicantService service;

	private ApplicantServiceImpl() {
		dao = ApplicantDAOImpl.getDao();

	}

	public static IApplicantService getService() {
		if (service == null) {
			service = new ApplicantServiceImpl();
		}
		return service;
	}
	@Override
	public boolean addApplicant(ApplicantVO avo) {
		return dao.addApplicant(avo);
	}

	@Override
	public boolean delApplicant(ApplicantVO avo) {
			return dao.delApplicant(avo);
	}

	@Override
	public boolean modifyApplicant(ApplicantVO avo) {
			return dao.modifyApplicant(avo);
	}

	@Override
	public List<ApplicantVO> getAllApplicant() {
			return dao.getAllApplicant();
	}

	@Override
	public List<ApplicantVO> getAllApplicantPaging(Map map) {
		List<ApplicantVO> list = null;
		int pageNo = 0;
		int totalRecord;
		int pagePerRecordCnt = 10;
		int groupPerPageCnt = 5;
		int recordEndNo;
		int recordStartNo;
		int totalPage;
		int groupNo;
		int pageEndNo;
		int pageStartNo;
		int prevPageNo;
		int nextPageNo;
		recordStartNo = (int) map.get("recordStart");
		if (pageNo < 1) pageNo = 1;

		
			totalRecord = dao.getAllApplicantCount();
		
			recordEndNo = pageNo * pagePerRecordCnt;
			recordStartNo = recordEndNo - (pagePerRecordCnt - 1);
			if (recordEndNo > totalRecord) recordEndNo = totalRecord;				
			totalPage = totalRecord / pagePerRecordCnt +
					(totalRecord % pagePerRecordCnt > 0 ? 1 : 0);
			if (pageNo > totalPage) pageNo = totalPage;
			groupNo = pageNo/ groupPerPageCnt +
					(pageNo % groupPerPageCnt > 0 ? 1 : 0);
			pageEndNo = groupNo * groupPerPageCnt;
			pageStartNo = pageEndNo - (groupPerPageCnt - 1);
			if (pageStartNo > totalPage) pageEndNo = totalPage;
			prevPageNo = pageStartNo - groupPerPageCnt;
			nextPageNo = pageStartNo + groupPerPageCnt;
			if (prevPageNo < 1) prevPageNo = 1;
			if (nextPageNo > totalPage) {
				nextPageNo = totalPage / groupPerPageCnt * 
						groupPerPageCnt + 1;
				nextPageNo = totalPage;
			}
			list = dao.getAllApplicantPaging(map);
		return list;
	}
}
