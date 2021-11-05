package dao;

import java.util.List;

import vo.MemberVO;

public interface IMemberDAO {

	MemberVO logIn(MemberVO mvo);
	
	boolean logOut(MemberVO mvo);
	
	boolean signUp(MemberVO mvo);
	
	boolean idCheck(String id);
	
	boolean userCheck(MemberVO mvo);
	
	boolean phoneNumberCheck(String num);
	
	boolean sendLicense(MemberVO mvo);
	
	boolean licenseCheck(MemberVO mvo);
	
	boolean emailCheck(String email);
	
	String searchId(MemberVO mvo);
	
	MemberVO resetPw(MemberVO mvo);
	
	boolean secession(MemberVO mvo);
	
	boolean modifyPass(MemberVO mvo);
	
	boolean modifyUserInfo(MemberVO mvo);
	
	List<MemberVO> getAllMember(int idx);
	
	MemberVO getOneMember(MemberVO vo);
	
	int getClassRoomNumber(MemberVO vo);
	
	String getMemberName(int member_idx);
	
	List<MemberVO> getManageMember();
	
	boolean modifyManage(MemberVO vo);
}
