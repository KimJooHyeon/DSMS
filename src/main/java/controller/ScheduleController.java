package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import service.IScheduleService;
import service.ScheduleServiceImpl;
import vo.MemberVO;
import vo.Schedule;
import vo.ScheduleVO;

@WebServlet("/schedule")
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		IScheduleService service = ScheduleServiceImpl.getService();
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		ScheduleVO schedule = new ScheduleVO();
		MemberVO user = (MemberVO) session.getAttribute("user");
		
		if(cmd.equals("index")) {
			rd = request.getRequestDispatcher("/WEB-INF/views/schedule.jsp");
		} else if(cmd.equals("getMonthList.do")) {
			String start = request.getParameter("startDate");
			String end = request.getParameter("endDate");
			
			schedule.setStart_date(start);
			schedule.setEnd_date(end);
			
			List<Schedule> list = service.getMonthSchedule(schedule);
			
			request.setAttribute("list", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/getMonthListDo.jsp");
		} else if(cmd.equals("addSchedule.do")) {
			String title = request.getParameter("title");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			String backgroundColor = request.getParameter("backgroundColor");
			String textColor = request.getParameter("textColor");
			
			ScheduleVO s = new ScheduleVO(0, 
										user.getMember_idx(), 
										title,
										start, 
										end, 
										description, 
										textColor, 
										backgroundColor, 
										type);
			
			boolean result = service.addSchedule(s);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/addScheduleDo.jsp");
		} else if(cmd.equals("modifySchedule.do")) {
			int idx = Integer.parseInt(request.getParameter("_id"));
			String title = request.getParameter("title");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String description = request.getParameter("description");
			String type = request.getParameter("type");
			String backgroundColor = request.getParameter("backgroundColor");
			String textColor = request.getParameter("textColor");
			
			ScheduleVO s = new ScheduleVO(idx, 
					user.getMember_idx(),
					title,
					start, 
					end, 
					description, 
					textColor, 
					backgroundColor, 
					type);
			
			
			boolean result = service.modifySchedule(s);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/modifyScheduleDo.jsp");
		} else if(cmd.equals("delSchedule.do")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			
			boolean result = service.delSchedule(idx);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/delScheduleDo.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
