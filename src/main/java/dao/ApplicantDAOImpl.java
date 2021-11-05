package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.ApplicantVO;

public class ApplicantDAOImpl implements IApplicantDAO {
	private SqlMapClient smc;
	private static ApplicantDAOImpl dao;
	
	public static IApplicantDAO getDao() {
		if (dao == null) {
			dao = new ApplicantDAOImpl();
		}
		return dao;
	}
	
	private ApplicantDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	@Override
	public boolean addApplicant(ApplicantVO avo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("applicant.insertApplicant",avo);
			if (obj == null) {
				flag = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delApplicant(ApplicantVO avo) {
		int cnt = 0;
		boolean flag = false;
		try {
			System.out.println(avo);
			cnt = smc.delete("applicant.deleteApplicant", avo);
			System.out.println(cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modifyApplicant(ApplicantVO avo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.update("applicant.updateApplicant", avo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<ApplicantVO> getAllApplicant() {
		List<ApplicantVO> list = null;
		try {
			list = smc.queryForList("applicant.selectAllApplicant");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ApplicantVO> getAllApplicantPaging(Map map) {
		
		List<ApplicantVO> list = null;
		try {
			list = smc.queryForList("applicant.selectAllApplicantBetween", map);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
		
	}

	@Override
	public int getAllApplicantCount() {
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("applicant.selectAllApplicantCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
		
	}

	

}
