package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.NoticeCompanyVO;
import vo.RankVO;
import vo.WorkCompanyVO;

public class CompanyDAOImpl implements ICompanyDAO {
	private SqlMapClient smc;
	private static CompanyDAOImpl dao;
	
	private CompanyDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static CompanyDAOImpl getInstance() {
		if (dao == null) {
			dao = new CompanyDAOImpl();
		}
		return dao;
	}
	@Override
	public boolean addWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("company.insertWorkCompany", wvo);
			
			if (obj == null) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;
		
		try {
			if (smc.delete("company.deleteWorkCompany", wvo) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<WorkCompanyVO> getAllWorkCompany() {
		List<WorkCompanyVO> list = null;
		
		try {
			list = smc.queryForList("company.selectAllWorkCompany");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public WorkCompanyVO getOneWorkCompany(WorkCompanyVO wvo) {
		try {
			wvo = (WorkCompanyVO) smc.queryForObject("company.selectOneWorkCompany", wvo.getCompany_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wvo;
	}

	@Override
	public boolean modifyWorkCompany(WorkCompanyVO wvo) {
		boolean flag = false;
		
		try {
			if (smc.update("company.updateWorkCompany", wvo.getCompany_idx()) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<RankVO> getWorkCompanyRank() {
		List<RankVO> list = null;
		
		try {
			list = smc.queryForList("company.selectWorkCompanyCityRank");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addNoticeCompany(NoticeCompanyVO nvo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("company.insertNoticeCompany", nvo);
			if (obj != null) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delNoticeCompany(NoticeCompanyVO nvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.delete("company.deleteNoticeCompany", nvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<NoticeCompanyVO> getAllNoticeCompany() {
		List<NoticeCompanyVO> list = null;
		try {
			list = smc.queryForList("company.selectAllNoticeCompany");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NoticeCompanyVO getOneNoticeCompany(NoticeCompanyVO nvo) {
		try {
			nvo = (NoticeCompanyVO) smc.queryForObject("company.selectOneNoticeCompany", nvo.getCompany_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return nvo;
	}

	@Override
	public boolean modifyNoticeCompany(NoticeCompanyVO nvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.update("company.updateNoticeCompany", nvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

}
