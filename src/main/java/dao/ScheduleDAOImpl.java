package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.Schedule;
import vo.ScheduleVO;

public class ScheduleDAOImpl implements IScheduleDAO {
	private SqlMapClient smc;
	private static ScheduleDAOImpl dao;
	
	private ScheduleDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IScheduleDAO getDao() {
		if (dao == null) {
			dao = new ScheduleDAOImpl();
		}
		return dao;
	}

	@Override
	public boolean addSchedule(ScheduleVO svo) {
		
		try {
			Object obj = smc.insert("schedule.addSchedule", svo);
			
			if(obj == null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delSchedule(int idx) {
		
		try {
			int result = smc.delete("schedule.delSchedule", idx);
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<ScheduleVO> getAllSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> getMonthSchedule(ScheduleVO svo) {
		List<Schedule> list = null;
		
		try {
			list = (List<Schedule>)smc.queryForList("schedule.getMonthSchedule", svo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean modifySchedule(ScheduleVO svo) {
		
		try {
			int result = smc.update("schedule.modifySchedule", svo);
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<ScheduleVO> getThisWeekSchedul() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
