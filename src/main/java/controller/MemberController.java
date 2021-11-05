package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import service.IMemberService;
import service.MemberServiceImpl;
import util.JSONUtil;
import vo.MemberVO;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		IMemberService memberService = MemberServiceImpl.getService();
		RequestDispatcher rd = null;
		
		System.out.println("넘어온 값 : " + cmd);
		
		if(cmd.equals("login.do")) {
			String userId = request.getParameter("id");
			String userPw = request.getParameter("pw");
			
			MemberVO member = new MemberVO();
			member.setMember_id(userId);
			member.setMember_pass(userPw);
			
			MemberVO result = memberService.logIn(member);
			
			if(result != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", result);
				
				if(result.getMember_state().equals("N")) {
					request.setAttribute("state", "N");
				} else {
					request.setAttribute("state", "Y");					
				}
			}

			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/loginDo.jsp");
			
		} else if(cmd.equals("idCheck.do")) {
			String id = request.getParameter("id");
			boolean result = memberService.idCheck(id);
			
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/idCheckDo.jsp");
		} else if(cmd.equals("userCheck.do")) {
			String name = request.getParameter("name");
			String regNo = request.getParameter("regNo");
			
			MemberVO vo = new MemberVO();
			vo.setMember_name(name);
			vo.setMember_regno(regNo);
			
			boolean result = memberService.userCheck(vo);
			
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/userCheckDo.jsp");
		} else if(cmd.equals("phoneCheck.do")) {
			String phone = request.getParameter("phone");
			
			boolean result = memberService.phoneNumberCheck(phone);
			
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/phoneCheckDo.jsp");
		} else if(cmd.equals("emailCheck.do")) {
			String email = request.getParameter("email");
			
			boolean result = memberService.emailCheck(email);
			
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/emailCheckDo.jsp");
		} else if(cmd.equals("signUpCheck.do")) {
			String name = request.getParameter("name");
			String regno = request.getParameter("regNo");
			
			MemberVO vo = new MemberVO();
			vo.setMember_name(name);
			vo.setMember_regno(regno);
			
			boolean result = memberService.userCheck(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/userCheckDo.jsp");
		} else if(cmd.equals("sendLicense.do")) {
			String phone = request.getParameter("phone");
			
			boolean result = memberService.sendLicense(phone);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/sendLicenseDo.jsp");
		} else if(cmd.equals("licenseCheck.do")) {
			String license = request.getParameter("license");
			String phone = request.getParameter("phone");
			
			
			boolean result = memberService.licenseCheck(license, phone);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/licenseCheckDo.jsp");
		} else if(cmd.equals("signUp.do")) {
			Map<String, String[]> data = request.getParameterMap();
			Map<String, String> map = new HashMap<>();
			
			for(String key : data.keySet()) {
				map.put(key, data.get(key)[0]);
			}
			
			String id = map.get("sign-up-id");
			String pw = map.get("sign-up-pw");
			String name = map.get("sign-up-name");
			String regno = map.get("sign-up-reg-no");
			String tel = map.get("sign-up-phone");
			String job = map.get("sign-up-job");
			String eamil = map.get("sign-up-email");
			
			MemberVO vo = new MemberVO(0, id, pw, name, regno, tel, eamil, job, null);
			
			boolean result = memberService.signUp(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/signUpDo.jsp");
		} else if(cmd.equals("searchId.do")) {
			String name = request.getParameter("name");
			String regno = request.getParameter("regno");
			
			MemberVO vo = new MemberVO();
			vo.setMember_name(name);
			vo.setMember_regno(regno);
			
			String id = memberService.searchId(vo);
			
			request.setAttribute("userId", id);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/searchIdDo.jsp");
		} else if(cmd.equals("resetPass.do")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String regno = request.getParameter("regno");
			
			MemberVO vo = new MemberVO();
			vo.setMember_id(id);
			vo.setMember_name(name);
			vo.setMember_regno(regno);
			
			boolean result = memberService.resetPw(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/resetPwDo.jsp");
		} else if(cmd.equals("myPage.do")) {
			HttpSession session = request.getSession();
			MemberVO user = (MemberVO)session.getAttribute("user");
			
			MemberVO vo = memberService.getOneMember(user);
			request.setAttribute("userInfo", vo);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/my-page.jsp");
		} else if(cmd.equals("modifyInfo.do")) {
			Map<String, String[]> data = request.getParameterMap();
			Map<String, String> map = new HashMap<>();
			MemberVO user = (MemberVO) request.getSession().getAttribute("user");

			for(String key : data.keySet()) {
				map.put(key, data.get(key)[0]);
			}
			
			MemberVO vo = new MemberVO();
			String id = user.getMember_id();
			String pw = map.get("user-pw");
			String tel = map.get("user-tel");
			String email = map.get("user-email");
			vo.setMember_id(id);
			vo.setMember_pass(pw);
			vo.setMember_tel(tel);
			vo.setMember_email(email);
			
			boolean result = memberService.modifyUserInfo(vo);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/modifyUserInfoDo.jsp");
		} else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/logout.jsp");
		} else if(cmd.equals("memberManage")) {
			List<MemberVO> list = memberService.getManageMember();
			
			request.setAttribute("list", list);			
			
			rd = request.getRequestDispatcher("/WEB-INF/views/member-manage.jsp");
		} else if(cmd.equals("manageModify")) {
			String json = request.getParameter("json");
			
			boolean result = memberService.modifyManage(json);
			request.setAttribute("result", result);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/manageModify.jsp");
		}
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
