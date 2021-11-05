package service;

import java.util.List;
import vo.MemberVO;

public interface IMemberService {
	/**
	 * 유저 아이디와 비밀번호를 받아서 확인 후 성공시 유저 정보 세션에 저장 후 true 리턴
	 * @param vo
	 * @return
	 */
	MemberVO logIn(MemberVO vo);
	
	/**
	 * 세션에 저장되어있는 유저 정보를 삭제하고 정상적으로 삭제가 완료되면 true리턴
	 * @param vo
	 * @return
	 */
	boolean logOut(MemberVO vo);
	
	/**
	 * 유저 정보에 널값이 없는지 확인하고 정상적으로 저장이 됐으면 true리턴
	 * @param vo
	 * @return
	 */
	boolean signUp(MemberVO vo);
	
	/**
	 * 비동기로 입력받아오는 아이디를 체크해서 사용 가능하면 true 리턴
	 * @param id
	 * @return
	 */
	boolean idCheck(String id);
	
	/**
	 * 회원가입 여부 확인후 가입 가능하면 true리턴
	 * @param vo
	 * @return
	 */
	boolean userCheck(MemberVO vo);
	
	/**
	 * 핸드폰 번호 중복 확인 후 사용 가능한 핸드폰 번호면 true 리턴
	 * @param num
	 * @return
	 */
	boolean phoneNumberCheck(String num);
	
	/**
	 * 유저의 핸드폰 번호를 받아서 문자로 인증번호를 보낸후 전송완료시 true리턴
	 * @param num
	 * @return
	 */
	boolean sendLicense(String num);
	
	/**
	 * 유저가 입력한 인증번호가 맞는지 확인후 맞으면 true리턴
	 * @param license
	 * @return
	 */
	boolean licenseCheck(String license, String phone);
	
	/**
	 * 비동기로 유저의 이메일을 확인 후 중복이 없으면 true 리턴
	 * @param email
	 * @return
	 */
	boolean emailCheck(String email);
	
	/**
	 * 유저의 이름과 주민번호를 받아서 맞는 아이디를 리턴
	 * @param vo
	 * @return
	 */
	String searchId(MemberVO vo);
	
	/**
	 * 유저의 아이디, 이름, 주민번호를 받아서 확인후 임시비밀번호를 문자로 전송후 성공시 true리턴
	 * @param vo
	 * @return
	 */
	boolean resetPw(MemberVO vo);
	
	/**
	 * 유저 정보를 받아와서 탈퇴처리후 성공시 true리턴
	 * @param vo
	 * @return
	 */
	boolean secession(MemberVO vo);
	
	boolean modifyPass(MemberVO vo);
	
	/**
	 * 유저의 정보를 받아와서 변경 후 성공 시 true리턴
	 * @param vo
	 * @return
	 */
	boolean modifyUserInfo(MemberVO vo);
	
	/**
	 * 학원의 모든 선생님들 리스트를 가져온다
	 * @return
	 */
	List<MemberVO> getAllMember(int idx);
	
	boolean sendTemporaryPass(String pw, String phone);
	public MemberVO getOneMember(MemberVO vo);
	List<MemberVO> getManageMember();
	
	/**
	 * 
	 * @param member_idx
	 * @return member_idx를 받아와서 유저 이름 리턴
	 */
	String getMemberName(int member_idx);
	boolean modifyManage(String json);
}
