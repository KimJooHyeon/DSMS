package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import service.ApplicantServiceImpl;
import service.IApplicantService;
import util.JSONUtil;
import vo.ApplicantVO;

@WebServlet("/applicant")
public class ApplicantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApplicantController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		RequestDispatcher rd = null;
		IApplicantService service = ApplicantServiceImpl.getService();
		
		if (cmd.equals("start")) {
			List<ApplicantVO> allApplicant = service.getAllApplicant();
			request.setAttribute("applicantList", allApplicant);
			rd = request.getRequestDispatcher("/WEB-INF/views/applicant.jsp");
		} else if(cmd.equals("already")) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			int recordStartNo = 1;
			recordStartNo = Integer.parseInt(request.getParameter("recordStartNo"));
			map.put("recordStartNo", recordStartNo);
			List<ApplicantVO> alreadyApplicant = service.getAllApplicantPaging(map);

			request.setAttribute("alreadyApplicant", alreadyApplicant);
		} else if(cmd.equals("add")){
			String json = request.getParameter("addJson");
			List<Map<String,String>> jsonMap = JSONUtil.getJsonMap(json);
//						System.out.println("testdata" + jsonMap);
			ApplicantVO vo = new ApplicantVO();
			for(Map<String, String> jmap : jsonMap) {
				String name = (String)jmap.get("name");
				int age = Integer.parseInt(String.valueOf(jmap.get("age")));
				String tel = (String)jmap.get("tel");
				int score= Integer.parseInt(String.valueOf(jmap.get("score")));
				String pass= (String)jmap.get("pass");
				vo.setApplicant_name(name);
				vo.setApplicant_age(age);
				vo.setApplicant_tel(tel);
				vo.setApplicant_score(score);
				vo.setApplicant_pass(pass);
				boolean result = service.addApplicant(vo);

				request.setAttribute("result", result);
			}
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/addApplicantResult.jsp");
		} else if (cmd.equals("delete")) {

			String json = request.getParameter("deleteJson");
			List<Map<String,String>> jsonMap = JSONUtil.getJsonMap(json);
			for(Map<String, String> jmap : jsonMap) {
				ApplicantVO vo = new ApplicantVO();
				
				String name = (String)jmap.get("name");
				int age = Integer.parseInt(String.valueOf(jmap.get("age")));
				String tel = (String)jmap.get("tel").trim() + "  ";
				int score= Integer.parseInt(String.valueOf(jmap.get("score")));
				String pass= (String)jmap.get("pass");
				
				vo.setApplicant_name(name);
				vo.setApplicant_age(age);
				vo.setApplicant_tel(tel);
				vo.setApplicant_score(score);
				vo.setApplicant_pass(pass);
				boolean result = service.delApplicant(vo);
				request.setAttribute("result", result);
			}
			rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/deleteApplicantResult.jsp");
		}
		
		rd.forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
