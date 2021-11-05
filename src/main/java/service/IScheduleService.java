package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Schedule;
import vo.ScheduleVO;

public interface IScheduleService {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 캘린더에 스케줄 추가 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean addSchedule(ScheduleVO vo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 캘린더에서 스케줄 삭제 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean delSchedule(int idx);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 모든 스케줄 가져와서 list로 return
	 * @throws ServletException
	 * @throws IOException
	 */
	List<ScheduleVO> getAllSchedule();
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 한 달치 스케줄 가져오기
	 * @throws ServletException
	 * @throws IOException
	 */
	List<Schedule> getMonthSchedule(ScheduleVO vo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 등록되어있는 스케줄 수정 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean modifySchedule(ScheduleVO vo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 이번 주 스케줄 리스트 가져오기
	 * @throws ServletException
	 * @throws IOException
	 */
	List<ScheduleVO> getThisWeekSchedul(ScheduleVO vo);
}
