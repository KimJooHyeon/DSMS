package dao;

import java.util.List;
import java.util.Map;

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

public interface IStudentDAO {

	boolean addStudent(StudentVO stvo);
	
	boolean delStudent(StudentVO stvo);
	
	List<StudentVO> getAllStudnet();
	
	StudentVO getOneStudent(StudentVO stvo);
	
	boolean modifyStudent(StudentInfoVO stvo);
	
	boolean createCounselingVO(CounselingVO cvo);
	
	boolean modifyCounselingVO(CounselingVO cvo);
	
	boolean delCounselingVO(int idx);
	
	List<CounselingVO> getAllCounselingVO(CounselingVO cvo);
	
	CounselingNameVO getOneCounselingVO(int idx);
	
	boolean assignClass(ClassRoomVO crvo);
	
	boolean addAttendance(AttendanceVO atvo);
	
	boolean modifyAttendance(AttendanceVO atvo);
	
	boolean delAttendance(AttendanceVO atvo);
	
	List<AttendanceVO> getAllAttendance();
	
	AttendanceVO getOneAttendance(AttendanceVO atvo);

	List<ClassRoomVO> getAllClassRoomInfo(ClassRoomVO crvo);

	List<AttendanceVO> getAllAttendanceStudentName();
	
	List<StudentOfClassRoomVO> selectAllClassStudent(int room); 

	List<StudentInfoVO> getClassRoomStudentInfo(int room);
	
	List<StudentVO> getStudentNames(int room);
	
	List<StudentVO> getAdminStudentNames();
	
	List<StudentVO> searchStudent(String name);
	
	List<AttendanceVO> getStudentAttendDetail(int idx);
	
	boolean modifyStudentAttend(AttendanceVO vo);
	
	List<StudentVO> getCounselStudentAdmin();
	
	List<StudentVO> getCounselStudentTeach(MemberVO mvo);
	
	List<StudentVO> searchCounselStudent(String name);
	
	List<CounselingVO> getCounselList(StudentVO svo);
	
	boolean modifyStudent(StudentVO stvo);
	
	List<StudentOfClassRoomVO> selectAllClassStudent(StudentOfClassRoomVO scvo); 
	
	
	List<ApplicantVO> getPassApplicant();
	
	boolean roomSetDel(int idx);
	boolean roomSetMod(ClassRoomVO vo);
	
	boolean addLicense(LicenseVO vo);
	boolean addProjectResult(ProjectResultVO vo);
	
}
