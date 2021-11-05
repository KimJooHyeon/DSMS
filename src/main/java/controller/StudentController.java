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

import service.IStudentService;
import service.MemberServiceImpl;
import service.StudentServiceImpl;
import util.JSONUtil;
import vo.ApplicantVO;
import vo.AttendanceVO;
import vo.ClassRoomVO;
import vo.CounselingNameVO;
import vo.CounselingVO;
import vo.MemberVO;
import vo.StudentInfoVO;
import vo.StudentOfClassRoomVO;
import vo.StudentVO;

@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		RequestDispatcher rd = null;
		IStudentService service = StudentServiceImpl.getInstance();
		HttpSession session = request.getSession();
		
		System.out.println("넘어온 값 : " + cmd);
		
		if (cmd.equals("attend")) {
			MemberVO user = (MemberVO) session.getAttribute("user");
			
			List<StudentVO> list = service.getClassRoomStudent(user);
			request.setAttribute("studentList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/attendance.jsp");
		} else if(cmd.equals("index")) {			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-info.jsp");				
		} else if(cmd.equals("info")) {
			int room = Integer.parseInt(request.getParameter("roomNumber"));
			
			List<StudentInfoVO> list = service.getClassRoomStudentInfo(room);
			
			request.setAttribute("list", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/studentInfoDo.jsp");
		} else if(cmd.equals("modify")) {
			String json = request.getParameter("rows");
			
			boolean result = service.modifyStudent(json);
			
			request.setAttribute("result", result);
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/studentInfoModify.jsp");
		} else if(cmd.equals("checkIn")) {
			MemberVO user = (MemberVO) session.getAttribute("user");
			
			List<StudentVO> list = service.getClassRoomStudent(user);
			request.setAttribute("studentList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-check-in.jsp");
		} else if(cmd.equals("search-student")) {
			String name = request.getParameter("student");
			
			List<StudentVO> list = service.searchStudent(name);
			
			request.setAttribute("studentList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/searchStudent.jsp");
		} else if(cmd.equals("studentDetail")) {
			int idx = Integer.parseInt(request.getParameter("studentIdx"));
			
			List<AttendanceVO> list = service.getStudentAttendDetail(idx);
			request.setAttribute("attendList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/attendList.jsp");
		} else if(cmd.equals("studentDetailModify")) {
			String json = request.getParameter("rows");
			
			boolean result = service.modifyStudentAttend(json);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/attendanceModify.jsp");
		} else if(cmd.equals("cons")) {
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			List<StudentVO> list = service.getAllCounselStudent(user);
			request.setAttribute("studentList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-counseling-list.jsp");
		} else if(cmd.equals("search-counsel-name")) {
			String name = request.getParameter("name");
			
			List<StudentVO> list = service.searchCounselStudent(name);
			request.setAttribute("list", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/searchCounselStudent.jsp");
		} else if(cmd.equals("counselDetail")) {
			String name = request.getParameter("_name");
			int idx = Integer.parseInt(request.getParameter("_idx"));
			
			StudentVO student = new StudentVO();
			student.setStudent_name(name);
			student.setStudent_idx(idx);
			
			List<CounselingVO> list = service.getCounselList(student);
			request.setAttribute("counselList", list);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/counselDetail.jsp");
		} else if(cmd.equals("goToWrite")) {
			MemberVO user = (MemberVO)session.getAttribute("user");
			int studentIdx = Integer.parseInt(request.getParameter("idx"));
			StudentVO curStu = new StudentVO();
			curStu.setStudent_idx(studentIdx);
			
			
			List<StudentVO> list = service.getAllCounselStudent(user);
			request.setAttribute("studentList", list);
			
			StudentVO student = service.getOneStudent(curStu);
			request.setAttribute("student", student);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-counseling-write.jsp");			
		} else if(cmd.equals("counselWriteDo")) {
			MemberVO user = (MemberVO) session.getAttribute("user");
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			CounselingVO vo = new CounselingVO(0, user.getMember_idx(), idx, title, null, contents);
			boolean result = service.createCounselingVO(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/counselWrite.jsp");
		} else if(cmd.equals("goToDetail")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			List<StudentVO> list = service.getAllCounselStudent(user);
			request.setAttribute("studentList", list);
			
			CounselingNameVO detail = service.getOneCounselingVO(idx);
			request.setAttribute("detail", detail);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-counseling-detail.jsp");
		} else if(cmd.equals("counselModify")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			List<StudentVO> list = service.getAllCounselStudent(user);
			request.setAttribute("studentList", list);
			
			CounselingNameVO detail = service.getOneCounselingVO(idx);
			request.setAttribute("detail", detail);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/student-counseling-modify.jsp");
		} else if(cmd.equals("counselModifyDo")) {
			MemberVO user = (MemberVO) session.getAttribute("user");
			int idx = Integer.parseInt(request.getParameter("idx"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			CounselingVO vo = new CounselingVO(idx, user.getMember_idx(), 0, title, null, contents);
			boolean result = service.modifyCounselingVO(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/counselModify.jsp");
		} else if(cmd.equals("counselDelDo")) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			boolean result = service.delCounselingVO(idx);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/counselDel.jsp");
		} else if(cmd.equals("classroomlist")) {
			StudentOfClassRoomVO scvo = new StudentOfClassRoomVO();			
			List<StudentOfClassRoomVO> studentList = service.selectAllClassStudent(scvo);
			
			for(StudentOfClassRoomVO item : studentList) {
				System.out.println(item);
			}
			
			request.setAttribute("AllClassStudent", studentList);
			rd = request.getRequestDispatcher("/WEB-INF/views/class-room-list.jsp");
		} else if(cmd.equals("classRoomSet")) {
			List<ApplicantVO> list = service.getPassApplicant();
			request.setAttribute("list", list);
			
			List<MemberVO> allMember = MemberServiceImpl.getService().getAllMember(0);
			request.setAttribute("allMember", allMember);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/class-room.jsp");
		} else if (cmd.equals("classRoomSetModify")) {
			String json = request.getParameter("json");
			
			boolean result = service.roomSetMod(json);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/setModify.jsp");
		} else if(cmd.equals("classRoomSetDelete")) {
			String json = request.getParameter("json");

			boolean result = service.roomSetDel(json);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/setDel.jsp");
		}
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
