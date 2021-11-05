package service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import dao.IScheduleDAO;
import dao.ScheduleDAOImpl;
import vo.Schedule;
import vo.ScheduleVO;

public class ScheduleServiceImpl implements IScheduleService {
	private IScheduleDAO dao;
	private static IScheduleService service;

	private ScheduleServiceImpl() {
		dao = ScheduleDAOImpl.getDao();
	}

	public static IScheduleService getService() {
		if (service == null) {
			service = new ScheduleServiceImpl();
		}
		return service;
	}

	@Override
	public boolean addSchedule(ScheduleVO vo) {
		return dao.addSchedule(vo);
	}

	@Override
	public boolean delSchedule(int idx) {
		return dao.delSchedule(idx);
	}

	@Override
	public List<ScheduleVO> getAllSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> getMonthSchedule(ScheduleVO vo) {
		return dao.getMonthSchedule(vo);
	}

	@Override
	public boolean modifySchedule(ScheduleVO vo) {
		return false;
	}

	@Override
	public List<ScheduleVO> getThisWeekSchedul(ScheduleVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
