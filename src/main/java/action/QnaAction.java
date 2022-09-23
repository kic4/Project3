package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;

//요청명령어에 해당되는 명령어 처리클래스=액션클래스=컨트롤러클래스
public class QnaAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int pageSize = 10, blockSize = 10, count = 0, number = 0;
		/*
		 * pageSize - 페이지당 보여지는 게시물 수, blockSize - 블럭당 보여지는 페이지 수 
		 * count - 전체 글 수, number - 페이지당 맨 처음에 나오는 게시물 번호
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		List articleList = null; // 화면에 출력할 레코드를 저장할 변수

		BoardDAO dbPro = new BoardDAO();
		count = dbPro.getArticleCount();//
		System.out.println("현재 레코드수(count)=>" + count);
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, pageSize);// 첫번째레코드번호,불러올 갯수
			System.out.println("ListAction의 articleList=>" + articleList);
		}
		
		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=>" + number);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);

		return "/qna.jsp";
	}

}
