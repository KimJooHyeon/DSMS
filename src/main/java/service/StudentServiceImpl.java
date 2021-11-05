package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.IStudentDAO;
import dao.MemberDAOImpl;
import dao.StudentDAOImpl;
import util.JSONUtil;
import vo.ApplicantVO;
import vo.AttendanceVO;
import vo.ClassRoomVO;
import vo.CounselingNameVO;
import vo.CounselingVO;
import vo.LicenseVO;
import vo.MemberVO;
import vo.ProjectResultVO;
import vo.StudentInfoVO;
import vo.StudentOfClassRoomVO;
import vo.StudentVO;

public class StudentServiceImpl implements IStudentService {

	private IStudentDAO dao = StudentDAOImpl.getInstance();
	private static IStudentService service;
	
	
	public static IStudentService getInstance() {
		if (service == null) {
			service = new StudentServiceImpl();
		}
		return service;
	}

	@Override
	public boolean addStudent(StudentVO stvo) {
		
		return dao.addStudent(stvo);
	}

	@Override
	public boolean delStudent(StudentVO stvo) {
		return dao.delStudent(stvo);
	}

	@Override
	public List<StudentVO> getAllStudent() {
		return dao.getAllStudnet();
	}
	
	@Override
	public List<StudentVO> getAllCounselStudent(MemberVO mvo) {
		List<StudentVO> list = null;
		
		
		if(mvo.getMember_job().equals("0")) {
			list = dao.getCounselStudentAdmin();
		} else if(mvo.getMember_job().equals("1")) {
			list = dao.getCounselStudentTeach(mvo);
		}
		
		return list;
	}

	@Override
	public StudentVO getOneStudent(StudentVO stvo) {
		return dao.getOneStudent(stvo);
	}

	@Override
	public boolean modifyStudent(String json) {
		
		List<Map<String, String>> jsonMap = JSONUtil.getJsonMap(json);
		boolean result = false;
		
		for(Map<String, String> item : jsonMap) {
			item.put("tel", item.get("tel").trim());
			
			StudentInfoVO vo = new StudentInfoVO();
			vo.setStudent_idx(Integer.parseInt(item.get("idx")));
			vo.setStudent_addr(item.get("addr"));
			vo.setStudent_tel(item.get("tel"));
			vo.setStudent_education(item.get("education"));
			vo.setStudent_graduate(item.get("graduate"));
			vo.setBasic_project(Integer.parseInt(item.get("basic-project")));
			vo.setMiddle_project(Integer.parseInt(item.get("middle-project")));
			vo.setFinal_project(Integer.parseInt(item.get("last-project")));
			
			result = dao.modifyStudent(vo);
			if(!result) {
				return false;
			}
		}
		
		return result;
	}

	@Override
	public boolean createCounselingVO(CounselingVO cvo) {
		return dao.createCounselingVO(cvo);
	}
	
	@Override
	public boolean modifyCounselingVO(CounselingVO cvo) {
		return dao.modifyCounselingVO(cvo);
	}
	
	@Override
	public boolean delCounselingVO(int idx) {
		return dao.delCounselingVO(idx);
	}

	@Override
	public List<CounselingVO> getAllCounselingVO(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CounselingNameVO getOneCounselingVO(int idx) {
		return dao.getOneCounselingVO(idx);
	}

	@Override
	public boolean addAttendance(AttendanceVO avo) {
		return dao.addAttendance(avo);
	}

	@Override
	public boolean modifyAttendance(AttendanceVO avo) {
		return dao.modifyAttendance(avo);
	}

	@Override
	public boolean delAttendance(AttendanceVO avo) {
		return dao.delAttendance(avo);
	}

	@Override
	public List<AttendanceVO> getAllAttendance() {
		return dao.getAllAttendance();
	}

	@Override
	public AttendanceVO getOneAttendance(AttendanceVO avo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassRoomVO> getAllClassRoomInfo(ClassRoomVO crvo) {
		return dao.getAllClassRoomInfo(crvo);
	}

	@Override
	public boolean assignClass(ClassRoomVO crvo) {
		// TODO Auto-generated method stub
		return dao.assignClass(crvo);
	}

	@Override
	public List<AttendanceVO> getAllAttendanceStudentName() {
		// TODO Auto-generated method stub
		return dao.getAllAttendanceStudentName();
	}

	@Override
	public List<StudentOfClassRoomVO> selectAllClassStudent(int room) {
		return dao.selectAllClassStudent(room);
	}

	@Override
	public List<StudentInfoVO> getClassRoomStudentInfo(int room) {
		return dao.getClassRoomStudentInfo(room);
	}
	
	@Override
	public List<StudentVO> getClassRoomStudent(MemberVO vo) {
		
		int room = 0;
		List<StudentVO> list = null;
		
		if(vo.getMember_job().equals("0")) {
			list = dao.getAdminStudentNames();
		} else if(vo.getMember_job().equals("1")) {			
			room = MemberDAOImpl.getDao().getClassRoomNumber(vo);			
			list = dao.getStudentNames(room);
		}
		
		return list;
	}
	
	@Override
	public List<StudentVO> searchStudent(String name) {
		return  dao.searchStudent(name);
	}
	
	@Override
	public List<AttendanceVO> getStudentAttendDetail(int idx) {
		return dao.getStudentAttendDetail(idx);
	}
	
	@Override
	public boolean modifyStudentAttend(String json) {
		
		List<Map<String, String>> jsonMap = JSONUtil.getJsonMap(json);

		for(Map<String, String> item : jsonMap) {
			String year = item.get("year");
			String month = item.get("month");
			String day = item.get("day");
			String date = year + month + day;
			String insertDate = year + "/" + month + "/" + day;
			String state = item.get("state");
			String in = "20" + date + item.get("check-in").replace(":", "");
			String out = "20" + date + item.get("check-out").replace(":", "");
			int idx = Integer.parseInt(item.get("idx"));
			
			AttendanceVO vo = new AttendanceVO(idx, insertDate, in, out, state);
			boolean result = dao.modifyStudentAttend(vo);
			if(!result) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public List<StudentVO> searchCounselStudent(String name) {
		return dao.searchCounselStudent(name);
	}
	
	@Override
	public List<CounselingVO> getCounselList(StudentVO svo) {
		return dao.getCounselList(svo);
	}
	
	@Override
	public List<StudentOfClassRoomVO> selectAllClassStudent(StudentOfClassRoomVO scvo) {
		return dao.selectAllClassStudent(scvo);
	}
	
	@Override
	public List<ApplicantVO> getPassApplicant() {
		return dao.getPassApplicant();
	}
	
	@Override
	public boolean roomSetDel(String json) {
		List<Map<String, String>> map = JSONUtil.getJsonMap(json);
		List<Integer> list = new ArrayList<>();
		
		for(Map<String, String> item : map) {
			int idx = Integer.parseInt(item.get("idx") + "");
			
			list.add(idx);
		}
		
		for(Integer item : list) {
			boolean result = dao.roomSetDel(item);
			if(!result) return false;
		}
		
		return true;
	}
	
	@Override
	public boolean roomSetMod(String json) {

		List<Map<String, String>> map = JSONUtil.getJsonMap(json);
		List<ClassRoomVO> list = new ArrayList<>();
		
		
		for(Map<String, String> item : map) {
			int idx = Integer.parseInt(item.get("idx") + "");
			int room = Integer.parseInt(item.get("room").substring(0, 3) + "");
			int mem = Integer.parseInt(item.get("teacher") + "");
			
			String name = item.get("name");
			int age = Integer.parseInt(item.get("age") + "");
			String tel = item.get("tel");
			
			ClassRoomVO vo = new ClassRoomVO(mem, idx, room);
			list.add(vo);
			System.out.println(vo);
			StudentVO student = new StudentVO();
			student.setStudent_age(age);
			student.setStudent_idx(idx);
			student.setStudent_tel(tel);
			student.setStudent_name(name);
			student.setStudent_addr("");
			student.setStudent_education("");
			student.setStudent_gradute("");
			dao.addStudent(student);
			
			ProjectResultVO pvo = new ProjectResultVO();
			pvo.setStudent_idx(idx);
			dao.addProjectResult(pvo);
			
			LicenseVO lvo = new LicenseVO();
			lvo.setStudent_idx(idx);
			dao.addLicense(lvo);
			
			
			dao.roomSetMod(vo);
		}
		
		for(ClassRoomVO vo : list) {
			boolean result = dao.roomSetDel(vo.getStudent_idx());
			if(!result) return false;
		}
		
		
		return true;
	}
}
