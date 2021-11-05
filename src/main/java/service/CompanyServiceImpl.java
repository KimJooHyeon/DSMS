package service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import dao.CompanyDAOImpl;
import dao.ICompanyDAO;
import vo.NoticeCompanyVO;
import vo.RankVO;
import vo.WorkCompanyVO;

public class CompanyServiceImpl implements ICompanyService {
	private ICompanyDAO dao;
	private static CompanyServiceImpl service;

	private CompanyServiceImpl() {
		dao = CompanyDAOImpl.getInstance();
	}

	public static CompanyServiceImpl getInstance() {
		if (service == null) {
			service = new CompanyServiceImpl();
		}
		return service;
	}

	@Override
	public boolean addWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;

		if (dao.addWorkCompany(wvo)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;

		if (dao.delWorkCompany(wvo)) {
			flag = true;
		}
		return flag;

	}

	@Override
	public List<String> getAllWorkCompany() {
		List<WorkCompanyVO> list = dao.getAllWorkCompany();
		List<String> jsonList = new ArrayList<>();
		
		for (WorkCompanyVO vo : list) {
			JSONObject inner = new JSONObject();
			inner.put("lat", vo.getLatitude());
			inner.put("lng", vo.getLongitude());
			jsonList.add(inner.toJSONString());
		}
		return jsonList;
	}

	@Override
	public WorkCompanyVO getOneWorkCompany(WorkCompanyVO wvo) {
		return dao.getOneWorkCompany(wvo);
	}

	@Override
	public boolean modifyWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;

		if (dao.modifyWorkCompany(wvo)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<RankVO> getWorkCompanyRank() {
		List<RankVO> list = dao.getWorkCompanyRank();
		int top = 0;
		
		for(RankVO vo : list) {
			top = vo.getCnt() > top ? vo.getCnt() : top;
		}
		
		for(RankVO vo : list) {
			int per = (100 * vo.getCnt()) / top;
			vo.setPer(per);
		}
		
		return list;
	}

	@Override
	public boolean addNoticeCompany(NoticeCompanyVO nvo) {
		boolean flag = false;
		
		if (dao.addNoticeCompany(nvo)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delNoticeCompany(NoticeCompanyVO nvo) {
		boolean flag = false;
		
		if (dao.delNoticeCompany(nvo)) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<NoticeCompanyVO> getAllNoticeCompany() {
		return dao.getAllNoticeCompany();
	}

	@Override
	public NoticeCompanyVO getOneNoticeCompany(NoticeCompanyVO nvo) {
		return dao.getOneNoticeCompany(nvo);
	}

	@Override
	public boolean modifyNoticeCompany(NoticeCompanyVO nvo) {
		boolean flag = false;
		
		if (dao.modifyNoticeCompany(nvo)) {
			flag = true;
		}
		return flag;
	}

}
