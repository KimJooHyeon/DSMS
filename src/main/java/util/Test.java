package util;

import java.util.List;

import service.BoardServiceImpl;
import vo.BoardVO;

public class Test {
	public static void main(String[] args) {
		BoardServiceImpl service = BoardServiceImpl.getInstance();
		
		
		List<BoardVO> latestPost = service.getLatestPost();
		
		for(BoardVO item : latestPost) {
			System.out.println(item);
		}
	}
}
