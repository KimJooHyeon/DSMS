package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import service.BoardServiceImpl;
import vo.BoardVO;
import vo.CommentNameVO;
import vo.CommentVO;
import vo.FileVO;
import vo.PageVO;

@WebServlet("/board")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024
		* 100)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String cmd = request.getParameter("cmd");
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		RequestDispatcher rd = null;
		int board_idx = 0;

		if (cmd.equals("selectList.do")) {
				
		// ----------  페이징  ------------
			
			String page1 = request.getParameter("page"); //page라는 파라미터의 값 가져옴
			
			int page = 0;
			
			if (page1 == null ){ //파라미터가 없으면
				page = 1;
				
			}else { //파라미터가 있으면
				page = Integer.parseInt(page1);
				
			}
			
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("page", page);
			
		//------------------------------------------
			
		    
			List<BoardVO> list1 = null;
			List<BoardVO> list2 = null;
			List<BoardVO> list3 = null;


			paramMap.put("category", 1);
			paramMap.put("cnt", 3);
			list1 = service.getAllArticle(paramMap); // 긴급

			paramMap.put("category", 2);
			paramMap.put("cnt", 5);
			list2 = service.getAllArticle(paramMap); // 공지
			
			int list1Size = list1.size();
			int list2Size = list2.size();
			
			// 조회할 자유 게시판 개수 설정
			int list3Size = 15 - list1Size - list2Size;
			
			
			// 자유 게시판 전체 개수
			int total3Size = service.boardCount();
			
			paramMap.put("category", 3);
			paramMap.put("page", page);
			paramMap.put("pageSize", list3Size);
			list3 = service.getAllArticle(paramMap); // 자유

			
			// 조회할 페이지 개수
			int paginationSize =0;
			int freePage = (int)Math.ceil(total3Size/list3Size) ;
			
			
			if(freePage>5) {
				paginationSize = 5;
			}else {
				paginationSize = freePage;
			}
			
			
			PageVO pageVo = new PageVO(page,list3Size,paginationSize);
			
			
			
			
			
			
			//페이지 별 게시글 조회

			request.setAttribute("BoardList1", list1);// 긴급
			request.setAttribute("BoardList2", list2);// 공지
			request.setAttribute("BoardList3", list3);// 자유
			
			request.setAttribute("pageVO", pageVo);
			request.setAttribute("path", "selectList.do");
			
			
			
			rd = request.getRequestDispatcher("/WEB-INF/views/board-list.jsp");
			

		} else if (cmd.equals("detail")) { // 1

			board_idx = Integer.parseInt(request.getParameter("idx"));
			BoardVO boardInfo = new BoardVO();
			FileVO fileInfo = new FileVO();
			
			service.updateBoardHit(board_idx);

			boardInfo.setBoard_idx(board_idx);
			boardInfo = service.getOneArticle(boardInfo); // 2 , 7

			request.setAttribute("boardInfo", boardInfo); // 8

			// *********** 댓글 *******************
			List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
			commentList = service.getAllComment(board_idx);

			request.setAttribute("commentList", commentList);
			
			List<FileVO> fileList = service.getAllFile(board_idx);
			if(fileList.size() > 0) {
				FileVO file = fileList.get(0);
				request.setAttribute("file", file);
			}
			

			rd = request.getRequestDispatcher("/WEB-INF/views/board-detail.jsp"); // 9

		} else if (cmd.equals("file_download")) {
			// 파일 다운로드
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			board_idx = Integer.parseInt(request.getParameter("idx"));
			
			List<FileVO> fileList = service.getAllFile(board_idx);
			String name = fileList.get(0).getFile_name(); 
			String type = fileList.get(0).getFile_type(); 
			String fileName = name+type;
			String downloadPath = fileList.get(0).getFile_path();

			String filePath = downloadPath + File.separator + fileName;
			File file = new File(filePath);

			OutputStream os = null;
			FileInputStream fis = null;

			if (file.exists()) { // 다운로드할 파일이 있을 때
				// Content-Type 설정하기
				response.setContentType("application/octet-stream; charset=utf-8");

				// response의 헤더 설정
				String headerValue = "attachment; filename=\"" + getDisposition(fileName, getBrowser(request)) + "\"";
				response.setHeader("Content-Disposition", headerValue);

				try {
					// 출력용 스트림 객체 생성
					os = response.getOutputStream();
					
					// 파일 입력용 스트림 객체 생성
					fis = new FileInputStream(file);

					byte[] buffer = new byte[1024 * 100];
					int len = -1;
					while ((len = fis.read(buffer)) > 0) {
						os.write(buffer, 0, len);
					}
					os.flush();

				} catch (Exception e) {
					System.out.println("입출력 오류 : " + e.getMessage());
				} finally {
					if (fis != null) {
						if (fis != null)
							try {
								fis.close();
							} catch (IOException e) {
							}
						if (os != null)
							try {
								os.close();
							} catch (IOException e) {
							}
					}
				}

			} else { // 다운로드할 파일이 없을 때
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().println("<h3>" + fileName + "파일은 존재하지 않습니다.</h3>");
			}
			
		}

		else if (cmd.equals("write")) {
			rd = request.getRequestDispatcher("/WEB-INF/views/board-write.jsp");

		} else if (cmd.equals("modify")) {
			board_idx = Integer.parseInt(request.getParameter("idx"));
			BoardVO boardInfo = new BoardVO();
			boardInfo.setBoard_idx(board_idx);
			boardInfo = BoardServiceImpl.getInstance().getOneArticle(boardInfo); // 2 , 7
			request.setAttribute("boardInfo", boardInfo); // 8
			rd = request.getRequestDispatcher("/WEB-INF/views/board-modify.jsp");

		} else if (cmd.equals("delete")) {

			board_idx = Integer.parseInt(request.getParameter("idx"));
			service.deleteBoard(board_idx);
			response.sendRedirect("/board?cmd=selectList.do");

		} else if (cmd.equals("deleteComment")) {

			CommentVO vo = new CommentVO();
			board_idx = Integer.parseInt(request.getParameter("board_idx"));
			int comment_idx = Integer.parseInt(request.getParameter("comment_idx"));

			vo.setBoard_idx(board_idx);
			vo.setComment_idx(comment_idx);
			service.deleteComment(vo);
			// List<Map<String, Object>> commentList = service.getAllComment(board_idx);
			// request.setAttribute("commentList", commentList);
			BoardVO boardInfo = new BoardVO();
			boardInfo.setBoard_idx(board_idx);
			boardInfo = service.getOneArticle(boardInfo); // 2 , 7

			request.setAttribute("boardInfo", boardInfo); // 8

			List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
			commentList = service.getAllComment(board_idx);

			request.setAttribute("commentList", commentList);

			rd = request.getRequestDispatcher("/WEB-INF/views/board-detail.jsp");

		}
		// else if(cmd.equals("board_hit")) {
		// board_idx= Integer.parseInt(request.getParameter("idx"));
		// service.updateBoardHit(board_idx);
		//
		// }
		else if (cmd.equals("searchData")) {
			String data = request.getParameter("searchDa");
			
		// ----------  페이징  ------------
			
			String page1 = request.getParameter("page"); //page라는 파라미터의 값 가져옴
			
			int page = 0;
			
			if (page1 == null ){ //파라미터가 없으면
				page = 1;
				
			}else { //파라미터가 있으면
				page = Integer.parseInt(page1);
				
			}
			
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("page", page);
			
			
			//-----------------------------------------------------

			List<BoardVO> list1 = null;
			List<BoardVO> list2 = null;
			List<BoardVO> list3 = null;
			
			paramMap.put("search", "Y");
			paramMap.put("searchData", data);

			paramMap.put("category", 1);
			list1 = service.getAllArticle(paramMap); // 긴급

			paramMap.put("category", 2);
			paramMap.put("cnt", 5);
			list2 = service.getAllArticle(paramMap); // 공지
			
			int list1Size = list1.size();
			int list2Size = list2.size();
			
			// 조회할 자유 게시판 개수 설정
			int list3Size = 15 - list1Size - list2Size;
			
			
			// 자유 게시판 전체 개수
			int total3Size = service.boardCount();
			
			paramMap.put("category", 3);
			paramMap.put("page", page);
			paramMap.put("pageSize", list3Size);
			list3 = service.getAllArticle(paramMap); // 자유

			
			// 조회할 페이지 개수
			int paginationSize =0;
			int freePage = (int)Math.ceil(total3Size/list3Size) ;
			
			
			if(freePage>5) {
				paginationSize = 5;
			}else {
				paginationSize = freePage;
			}
			
			
			PageVO pageVo = new PageVO(page,list3Size,paginationSize);

			paramMap.put("category", 3);
			list3 = service.getAllArticle(paramMap); // 자유

			request.setAttribute("BoardList1", list1);// 긴급
			request.setAttribute("BoardList2", list2);// 공지
			request.setAttribute("BoardList3", list3);// 자유
			request.setAttribute("pageVO", pageVo);
			request.setAttribute("path", "searchData");
			
			
			rd = request.getRequestDispatcher("/WEB-INF/views/board-list.jsp");

		}

		if (!cmd.equals("delete")) {
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		RequestDispatcher rd = null;
		BoardVO boardInfo = new BoardVO();

		if (cmd.equals("write")) {

			Map<String, Object> map = new HashMap<String, Object>();
			if (request.getParameter("board_category") != null) {
				
				boardInfo.setBoard_title(request.getParameter("board_title"));
				boardInfo.setBoard_content(request.getParameter("board_content"));
				
				boardInfo.setBoard_category(request.getParameter("board_category"));
				boardInfo.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
				map.put("boardVO", boardInfo);
			}


			// 파일 작성

			String root = request.getSession().getServletContext().getRealPath("/");
			String uploadPath = root + "storage\\files";
			
			
			String fileName = "";
			String filetype = "";

			File fileUploadDir = new File(uploadPath); // 파일경로 만들기
			if (!fileUploadDir.exists()) {
				fileUploadDir.mkdirs();
			}

			for (Part part : request.getParts()) {
				fileName = extractFileName(part);
				
				// 구해온 파일명이 공백("")이면 이것은 파일이 아닌 일반 Form data라는 의미
				if (!"".equals(fileName)) { // 파일명이 공백이 아니며
					// 1개의 업로드 파일 정보를 저장할 VO객체 생성
					FileVO fvo = new FileVO();
					
					int index = fileName.indexOf('.');
					String name = fileName.substring(0, index);
					String type = fileName.substring(index);
					fvo.setFile_name(name);
					fvo.setFile_type(type);
					fvo.setFile_path(uploadPath);
					map.put("fileVO", fvo);

					try {
						part.write(uploadPath + File.separator + fileName); // 파일 저장

					} catch (IOException e) {
						System.out.println("업로드 실패!!!" + e.getMessage());
					}
				}

			}
			service.writeArticle(map);
			response.sendRedirect("/board?cmd=selectList.do");

		} else if (cmd.equals("update")) {

			boardInfo.setBoard_title(request.getParameter("board_title"));
			boardInfo.setBoard_content(request.getParameter("board_content"));

			boardInfo.setBoard_category(request.getParameter("board_category"));
			boardInfo.setMember_idx(Integer.parseInt(request.getParameter("member_idx")));
			boardInfo.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));

			service.modifyArticle(boardInfo);
			response.sendRedirect("/board?cmd=selectList.do");

		} else if (cmd.equals("commentWrite")) {
			response.setCharacterEncoding("utf-8");

			CommentVO vo = new CommentVO();
			String comment_content = request.getParameter("comment_content");
			int board_idx = Integer.parseInt(request.getParameter("board_idx"));
			int member_idx = Integer.parseInt(request.getParameter("member_idx"));

			vo.setMember_idx(member_idx);
			vo.setComment_content(comment_content);
			vo.setBoard_idx(board_idx);

			CommentNameVO comment = service.writeComment(vo);
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(comment);
			if(comment != null) {
				out.println(json.toString());
			}
			
			//response.flushBuffer();
			
			
		} else if (cmd.equals("modifyCommnet")) {
			CommentVO vo = new CommentVO();
			String comment_content = request.getParameter("comment_content");
			int board_idx = Integer.parseInt(request.getParameter("board_idx"));
			int comment_idx = Integer.parseInt(request.getParameter("comment_idx"));

			vo.setBoard_idx(board_idx);
			vo.setComment_content(comment_content);
			vo.setComment_idx(comment_idx);

			service.modifyCommnet(vo);
		}
	}

	private String extractFileName(Part part) {
		String fileName = "";

		// 헤더의 키값이 'Content-Disposition'인 헤더의 value값을 구한다.
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items = contentDisposition.split(";"); // ;이 들어간걸 구분
		for (String item : items) {
			if (item.trim().startsWith("filename")) { // trim : 공백 없애기
				// filename="test1.txt" => '='문자를 찾아서 끝까지인데 "는 필요없어서 -1 해준거
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}

	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");

		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		} else if (header.indexOf("Trident/7.0") > -1) { // IE 11 이상
			return "MSIE";
		}
		return "Firefox";
	}

	private String getDisposition(String fileName, String browser) {
		String encodedFilename = null;
		try {
			if (browser.equals("MSIE")) {
				encodedFilename = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
			} else if (browser.equals("Firefox") || browser.equals("Opera")) {
				// 8859_1 : 한글이 안들어가는 코드로 바꿔라 라는 의미?
				encodedFilename = "\"" + new String(fileName.getBytes("utf-8"), "8859_1") + "\"";
			} else if (browser.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < fileName.length(); i++) {
					char c = fileName.charAt(i);
					if (c > '~') { // '~' : 영어코드값의 맨끝 // (c > '~') = 영어권에서 쓰는 문자범위를 벗어났다
						sb.append(URLEncoder.encode("" + c, "utf-8"));
					} else {
						sb.append(c);
					}
					encodedFilename = sb.toString();
				}

			} else {
				throw new RuntimeException("지원하지 않는 브라우저입니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return encodedFilename;
	}

}
