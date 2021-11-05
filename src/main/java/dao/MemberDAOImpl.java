package dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	private SqlMapClient smc;
	private static MemberDAOImpl dao;
	
	private MemberDAOImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IMemberDAO getDao() {
		if (dao == null) {
			dao = new MemberDAOImpl();
		}
		return dao;
	}
	
	@Override
	public MemberVO logIn(MemberVO mvo) {
		MemberVO result = null;
		try {
			result = (MemberVO)smc.queryForObject("member.memberCheck", mvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean logOut(MemberVO mvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signUp(MemberVO mvo) {
		try {
			Object obj = smc.insert("member.insertMember", mvo);
			if(obj == null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean idCheck(String id) {
		boolean result = false;
		
		try {
			int cnt = (int)smc.queryForObject("member.memberIdCheck", id);
			if(cnt == 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean userCheck(MemberVO mvo) {
		boolean result = false;
		try {
			int cnt = (int)smc.queryForObject("member.memberRegnoCheck", mvo);
			if(cnt == 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean phoneNumberCheck(String num) {
		boolean result = false;
		
		try {
			int cnt = (int)smc.queryForObject("member.memberTelCheck", num);
			if(cnt == 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean sendLicense(MemberVO mvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean licenseCheck(MemberVO mvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean emailCheck(String email) {
		boolean result = false;
		
		try {
			int cnt = (int)smc.queryForObject("member.memberEmailCheck", email);
			if(cnt == 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String searchId(MemberVO mvo) {
		String id = null;
		try {
			id = smc.queryForObject("member.findMemberId", mvo) + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public MemberVO resetPw(MemberVO mvo) {
		MemberVO vo = new MemberVO();
		try {
			vo = (MemberVO)smc.queryForObject("member.findMemberPass", mvo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean secession(MemberVO mvo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyPass(MemberVO mvo) {
		try {
			int result = smc.update("member.updateMemberPass", mvo);
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyUserInfo(MemberVO mvo) {
		
		try {
			int result = smc.update("member.modifyUserInfo", mvo);
			if(result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<MemberVO> getAllMember(int idx) {
		List<MemberVO> list = null;
		try {
			list = smc.queryForList("member.getAllMember", idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public MemberVO getOneMember(MemberVO vo) {
		MemberVO user = null;
		try {
			user = (MemberVO) smc.queryForObject("member.getOneMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int getClassRoomNumber(MemberVO vo) {
		int room = 0;
		try {
			room = (int) smc.queryForObject("member.getClassRoomNumber", vo.getMember_idx());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			room = 0;
		}
		
		return room;
	}
	
	@Override
	public List<MemberVO> getManageMember() {
		List<MemberVO> list = null;
		
		try {
			list = smc.queryForList("member.getManageMember");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public String getMemberName(int member_idx) {
		String str = null;
		
		try {
			str = (String) smc.queryForObject("member.getMemberName", member_idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	public boolean modifyManage(MemberVO vo) {
		
		try {
			int cnt = smc.update("member.modifyManage", vo);
			if(cnt > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
