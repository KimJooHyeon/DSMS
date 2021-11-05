package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ApplicantVO;
import vo.AttendanceVO;
import vo.ClassRoomVO;
import vo.CounselingNameVO;
import vo.CounselingVO;
import vo.MemberVO;
import vo.StudentInfoVO;
import vo.StudentOfClassRoomVO;
import vo.StudentVO;

public interface IStudentService {
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 추가 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean addStudent(StudentVO stvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 삭제 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean delStudent(StudentVO stvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 수업을 듣고있는 모든 학생 정보 list로 return
	 * @throws ServletException
	 * @throws IOException
	 */
	List<StudentVO> getAllStudent();
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 한 학생의 정보 return
	 * @throws ServletException
	 * @throws IOException
	 */
	StudentVO getOneStudent(StudentVO stvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 정보 수정 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean modifyStudent(String json);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 상담 일지 추가 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean createCounselingVO(CounselingVO cvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 상담 일지 수정 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean modifyCounselingVO(CounselingVO cvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 학생 상담 일지 삭제 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean delCounselingVO(int idx);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 해당 학생의 모든 상담 일지 가져오기
	 * @throws ServletException
	 * @throws IOException
	 */
	List<CounselingVO> getAllCounselingVO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 해당 학생의 상담 일지중 선택한 상담일지 가져오기
	 * @throws ServletException
	 * @throws IOException
	 */
	CounselingNameVO getOneCounselingVO(int idx);
	
	List<StudentVO> getAllCounselStudent(MemberVO mvo);
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return 반 배정 후 성공 시 true
	 * @throws ServletException
	 * @throws IOException
	 */
	boolean assignClass(ClassRoomVO crvo);
	
	boolean addAttendance(AttendanceVO avo);
	
	boolean modifyAttendance(AttendanceVO avo);
	
	boolean delAttendance(AttendanceVO avo);
	
	List<AttendanceVO> getAllAttendance();
	
	List<AttendanceVO> getAllAttendanceStudentName();
	
	AttendanceVO getOneAttendance(AttendanceVO avo);
	
	List<ClassRoomVO> getAllClassRoomInfo(ClassRoomVO crvo);

	List<StudentOfClassRoomVO> selectAllClassStudent(int room);
	
	List<StudentInfoVO> getClassRoomStudentInfo(int room);
	
	List<StudentVO> getClassRoomStudent(MemberVO vo);
	
	List<StudentVO> searchStudent(String name);
	
	List<AttendanceVO> getStudentAttendDetail(int idx);
	
	boolean modifyStudentAttend(String json);
	
	List<StudentVO> searchCounselStudent(String name);
	
	List<CounselingVO> getCounselList(StudentVO svo);
	
	List<StudentOfClassRoomVO> selectAllClassStudent(StudentOfClassRoomVO scvo);
	
	List<ApplicantVO> getPassApplicant();
	
	boolean roomSetDel(String json);
	
	boolean roomSetMod(String json);
}
