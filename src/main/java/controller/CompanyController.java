package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.ChannelSender;

import service.CompanyServiceImpl;
import service.ICompanyService;
import util.JSONUtil;
import vo.NoticeCompanyVO;

@WebServlet("/company")
public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CompanyController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICompanyService companyService = CompanyServiceImpl.getInstance();
		RequestDispatcher rd = null;
		
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("map")) {
			request.setAttribute("map", companyService.getAllWorkCompany());
			rd = request.getRequestDispatcher("/WEB-INF/views/map.jsp");
		} else if (cmd.equals("notice")) {
			List<NoticeCompanyVO> allNoticeCompany = companyService.getAllNoticeCompany();
			request.setAttribute("NoticeCompanyList", allNoticeCompany);
			rd = request.getRequestDispatcher("/WEB-INF/views/company.jsp");
		} else if (cmd.equals("companySave")) {
			String json = request.getParameter("json");
			List<Map<String,String>> jsonMap = JSONUtil.getJsonMap(json);
			for (Map<String, String> map : jsonMap) {
				NoticeCompanyVO vo = new NoticeCompanyVO();
				String title = (String)map.get("title");
				String addr = (String)map.get("addr");
				int salary = Integer.parseInt(map.get("salary"));
				String section = (String)map.get("section");
				int intake = Integer.parseInt(map.get("intake"));
				vo.setCompany_addr(addr);
				vo.setCompany_name(title);
				vo.setCompany_salary(salary);
				vo.setCompany_section(section);
				vo.setRecuruit_intake(intake);
				boolean result = companyService.addNoticeCompany(vo);
				
				request.setAttribute("result", result);
				rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/noticeCompanyAdd.jsp");
			}
		} else if (cmd.equals("companyDelete")) {
			String json = request.getParameter("json");
			List<Map<String,String>> jsonMap = JSONUtil.getJsonMap(json);
			for (Map<String, String> map : jsonMap) {
				NoticeCompanyVO vo = new NoticeCompanyVO();
				String title = (String)map.get("title");
				String addr = (String)map.get("addr");
				int salary = Integer.parseInt(map.get("salary"));
				String section = (String)map.get("section");
				int intake = Integer.parseInt(map.get("intake"));
				vo.setCompany_idx(map.get("idx") + "");
				boolean result = companyService.delNoticeCompany(vo);
				request.setAttribute("result", result);
				rd = request.getRequestDispatcher("/WEB-INF/views/ajaxView/noticeCompanyDelete.jsp");
			}
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
