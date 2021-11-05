package service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dao.IMemberDAO;
import dao.MemberDAOImpl;
import util.CreateLicenseNumber;
import util.CryptoUtil;
import util.JSONUtil;
import util.LicenseNumberPool;
import util.MessageUtil;
import util.TemporaryPassword;
import vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO dao;
	private static IMemberService service;
	private final String key = "9akly5dsms3ddit1";

	private MemberServiceImpl() {
		dao = MemberDAOImpl.getDao();
	}

	public static IMemberService getService() {
		if (service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public MemberVO logIn(MemberVO vo) {
		try {
			vo.setMember_id(CryptoUtil.encryptAES256(vo.getMember_id(), key));
			vo.setMember_pass(CryptoUtil.sha512(vo.getMember_pass()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		
		
		return dao.logIn(vo);
	}

	@Override
	public boolean logOut(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signUp(MemberVO vo) {
		try {
			String id = CryptoUtil.encryptAES256(vo.getMember_id(), key);
			String pw = CryptoUtil.sha512(vo.getMember_pass());
			String regno = CryptoUtil.sha512(vo.getMember_regno());
			
			vo.setMember_id(id);
			vo.setMember_pass(pw);
			vo.setMember_regno(regno);
			
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		if(vo.getMember_job().equals("teach")) {
			vo.setMember_job("1");
		} else {
			vo.setMember_job("2");
		}
		
		return dao.signUp(vo);
	}

	@Override
	public boolean idCheck(String id) {
		
		try {
			id = CryptoUtil.encryptAES256(id, key);
			System.out.println(id);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return dao.idCheck(id);
	}

	@Override
	public boolean userCheck(MemberVO vo) {
		try {
			vo.setMember_regno(CryptoUtil.sha512(vo.getMember_regno()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return dao.userCheck(vo);
	}

	@Override
	public boolean phoneNumberCheck(String num) {
		return dao.phoneNumberCheck(num);
	}

	@Override
	public boolean sendLicense(String num) {
		String license = CreateLicenseNumber.getinstance().licenseNumber();
		
		LicenseNumberPool.getInstance().setLicense(num, license);
		return MessageUtil.getInstance().sendMessage(num, license);
	}

	@Override
	public boolean licenseCheck(String license, String phone) {
		System.out.println(license);
		System.out.println(phone);
		String current = LicenseNumberPool.getInstance().getLicense(phone);
		System.out.println(current);
		if(current.equals(license)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean emailCheck(String email) {
		return dao.emailCheck(email);
	}

	@Override
	public String searchId(MemberVO vo) {
		
		try {
			String regno = CryptoUtil.sha512(vo.getMember_regno());
			vo.setMember_regno(regno);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String id = dao.searchId(vo);
		if(id != null) {
			try {
				id = CryptoUtil.decryptAES256(id, key);
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public boolean resetPw(MemberVO vo) {
		try {
			String id = CryptoUtil.encryptAES256(vo.getMember_id(), key);
			String regno = CryptoUtil.sha512(vo.getMember_regno());
			vo.setMember_regno(regno);
			vo.setMember_id(id);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		MemberVO newVO = dao.resetPw(vo);
		
		if(newVO != null) {
			String pw = TemporaryPassword.getInstance().getTemporaryPassword();
			String originPw = pw;
			
			try {
				pw = CryptoUtil.sha512(pw);
				newVO.setMember_pass(pw);
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			boolean modifyCheck = modifyPass(newVO);
			
			boolean sendPwCheck = sendTemporaryPass(originPw, newVO.getMember_tel());
			
			return modifyCheck && sendPwCheck;
		}
		return false;
	}

	@Override
	public boolean secession(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyPass(MemberVO vo) {
		return dao.modifyPass(vo);
	}

	@Override
	public boolean modifyUserInfo(MemberVO vo) {
		
		if(!vo.getMember_pass().isEmpty()) {
			try {
				String pw = CryptoUtil.sha512(vo.getMember_pass());
				vo.setMember_pass(pw);
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return dao.modifyUserInfo(vo);
	}

	@Override
	public List<MemberVO> getAllMember(int idx) {
		return dao.getAllMember(idx);
	}

	@Override
	public boolean sendTemporaryPass(String pw, String phone) {
		return MessageUtil.getInstance().sendMessage(phone, pw);
	}
	
	@Override
	public MemberVO getOneMember(MemberVO vo) {
		MemberVO user = dao.getOneMember(vo); 
		try {
			String id = CryptoUtil.decryptAES256(vo.getMember_id(), key);
			if(user.getMember_job().equals("1")) {
				user.setMember_job("교사");
			} else {
				user.setMember_job("행정");
			}
			
			
			user.setMember_id(id);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}		
		return user;
	}
	
	@Override
	public List<MemberVO> getManageMember() {
		return dao.getManageMember();
	}
	
	@Override
	public String getMemberName(int member_idx) {
		return dao.getMemberName(member_idx);
	}
	
	@Override
	public boolean modifyManage(String json) {
		List<Map<String, String>> list = JSONUtil.getJsonMap(json);
		List<MemberVO> memList = new ArrayList<>();
		
		for(Map<String, String> item : list) {
			MemberVO vo = new MemberVO();
			int idx = Integer.parseInt(item.get("idx"));
			String state = item.get("state");
			
			vo.setMember_idx(idx);
			vo.setMember_state(state);
			memList.add(vo);
		}
		
		for(MemberVO item : memList) {
			boolean result = dao.modifyManage(item);
			if(!result) {
				return false;
			}
		}
		
		return true;
	}
}
