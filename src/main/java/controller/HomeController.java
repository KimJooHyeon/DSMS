package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.CompanyServiceImpl;
import service.IBoardService;
import service.ICompanyService;
import vo.BoardVO;
import vo.RankVO;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		IBoardService boardService = BoardServiceImpl.getInstance();
		ICompanyService companyService = CompanyServiceImpl.getInstance();
		RequestDispatcher rd = null;
		
		
		if(cmd.equals("index")) {
			List<RankVO> workCompanyRank = companyService.getWorkCompanyRank();
			List<BoardVO> latestPost = boardService.getLatestPost();
			
			
			request.setAttribute("post", latestPost);
			request.setAttribute("rank", workCompanyRank);
			
			rd = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
		}
		
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
