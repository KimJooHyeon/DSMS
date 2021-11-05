package dao;

import java.util.List;
import java.util.Map;

import vo.Schedule;
import vo.ScheduleVO;

public interface IScheduleDAO {

	public boolean addSchedule(ScheduleVO svo);
	
	public boolean delSchedule(int idx);
	
	public List<ScheduleVO> getAllSchedule();
	
	public List<Schedule> getMonthSchedule(ScheduleVO svo);
	
	public boolean modifySchedule(ScheduleVO svo);
	
	public List<ScheduleVO> getThisWeekSchedul();
	
	
	
}
