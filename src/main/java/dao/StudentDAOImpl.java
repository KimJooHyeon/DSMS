package dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
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

public class StudentDAOImpl implements IStudentDAO {
	
	private SqlMapClient smc;
	private static StudentDAOImpl dao;
	public static IStudentDAO getInstance() {
		if (dao == null) {
			dao = new StudentDAOImpl();
		}
		return dao;
	}
	
	public StudentDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	@Override
	public boolean addStudent(StudentVO stvo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("student.insertStudent",stvo);
			if (obj == null) {
				flag = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delStudent(StudentVO stvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.delete("student.deleteStudent", stvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<StudentVO> getAllStudnet() {
		List<StudentVO> list = null;
		try {
			list = smc.queryForList("student.selectAllStudent");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public StudentVO getOneStudent(StudentVO stvo) {

		try {
			stvo = (StudentVO) smc.queryForObject("student.selectOneStudent", stvo.getStudent_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return stvo;

	}

	@Override
	public boolean modifyStudent(StudentInfoVO stvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			System.out.println(stvo);
			cnt = smc.update("student.updateStudentInfo", stvo);
			cnt = smc.update("student.updateStudentProject", stvo);
			cnt = smc.update("student.updateStudentLicense", stvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<CounselingVO> getAllCounselingVO(CounselingVO cvo) {
		List<CounselingVO> list = null;
		try {
			list = smc.queryForList("counseling.selectAllCounseling");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CounselingNameVO getOneCounselingVO(int idx) {
		CounselingNameVO vo = null;
		try {
			vo = (CounselingNameVO) smc.queryForObject("counseling.selectOneCounseling", idx);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return vo;
	}

	@Override
	public boolean assignClass(ClassRoomVO crvo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("classRoom.insertClassRoom",crvo);
			if (obj == null) {
				flag = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public boolean addAttendance(AttendanceVO atvo) {
		boolean flag = false;
		
		try {
			Object obj = smc.insert("attendance.insertAttendance", atvo);
			if (obj == null) {
				flag = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean modifyAttendance(AttendanceVO atvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.update("attendance.updateAttendance", atvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}
	

	@Override
	public boolean delAttendance(AttendanceVO atvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.delete("attendance.deleteAttendance", atvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<AttendanceVO> getAllAttendanceStudentName() {
		List<AttendanceVO> list = null;
		try {
			list = smc.queryForList("attendance.selectAllAttendanceStudentName");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public AttendanceVO getOneAttendance(AttendanceVO atvo) {
		try {
			atvo = (AttendanceVO) smc.queryForObject("attendance.selectOneNoticeCompany",
					atvo.getStudent_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return atvo;
	}

	@Override
	public boolean createCounselingVO(CounselingVO cvo) {
		try {
			Object result = smc.insert("counseling.insertCounseling", cvo);
			if(result == null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyCounselingVO(CounselingVO cvo) {
		
		try {
			int cnt = smc.update("counseling.updateCounseling", cvo);
			if(cnt > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delCounselingVO(int idx) {
		try {
			int delete = smc.delete("counseling.deleteCounseling", idx);
			if(delete > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ClassRoomVO> getAllClassRoomInfo(ClassRoomVO crvo) {
		List<ClassRoomVO> list = null;
		try {
			list = smc.queryForList("classRoom.selectClassRoomInfo");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<AttendanceVO> getAllAttendance() {
		List<AttendanceVO> list = null;
		try {
			list = smc.queryForList("attendance.selectAllAttendance");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentOfClassRoomVO> selectAllClassStudent(int room) {
		List<StudentOfClassRoomVO> list = null;
		try {
			list = smc.queryForList("classRoom.selectClassRoomInfo", room);
			System.out.println(list.size());
			for(StudentOfClassRoomVO item : list) {
				System.out.println(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentInfoVO> getClassRoomStudentInfo(int room) {
		List<StudentInfoVO> list = null;
		
		try {
			list = smc.queryForList("student.classRoomStudentInfo", room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<StudentVO> getStudentNames(int room) {
		List<StudentVO> list = null;
	
		try {
			list = smc.queryForList("student.getStudentNames", room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<StudentVO> getAdminStudentNames() {
		List<StudentVO> list = null;
		try {
			list = smc.queryForList("student.adminStudentNames");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<StudentVO> searchStudent(String name) {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("student.searchStudent", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<AttendanceVO> getStudentAttendDetail(int idx) {
		List<AttendanceVO> list = null;
		
		try {
			list = smc.queryForList("student.attendDetail", idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean modifyStudentAttend(AttendanceVO vo) {
		try {
			int result = smc.update("student.modifyStudentAttend", vo);
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public List<StudentVO> getCounselStudentAdmin() {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("student.adminStudentCounsel");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<StudentVO> getCounselStudentTeach(MemberVO mvo) {
		List<StudentVO> list = null;
		
		try {
			list = smc.queryForList("student.teacherStudentCounsel", mvo.getMember_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<StudentVO> searchCounselStudent(String name) {
		List<StudentVO> list = null; 
		
		try {
			list = smc.queryForList("student.searchCounselStudent", name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<CounselingVO> getCounselList(StudentVO svo) {
		List<CounselingVO> list = null;
		
		try {
			list = smc.queryForList("counseling.selectAllCounseling", svo.getStudent_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean modifyStudent(StudentVO stvo) {
		int cnt = 0;
		boolean flag = false;
		try {
			cnt = smc.update("student.updateStudent", stvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (cnt != 0) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public List<StudentOfClassRoomVO> selectAllClassStudent(StudentOfClassRoomVO scvo) {
		List<StudentOfClassRoomVO> list = null;
		
		try {
			list = smc.queryForList("classRoom.selectClassRoomInfo");	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<ApplicantVO> getPassApplicant() {
		
		List<ApplicantVO> list = null;
		
		try {
			list = smc.queryForList("student.getPassApplicant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean roomSetDel(int idx) {
		
		try {
			int cnt = smc.delete("applicant.roomSetDel", idx);
			if(cnt > 0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean roomSetMod(ClassRoomVO vo) {
		
		try {
			Object obj = smc.insert("classRoom.insertApplicant", vo);
			if(obj == null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean addLicense(LicenseVO vo) {
		
		try {
			Object insert = smc.insert("student.insertLicense", vo);
			if(insert == null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean addProjectResult(ProjectResultVO vo) {
		
		try {
			Object insert = smc.insert("student.insertPR", vo);
			if(insert == null) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
