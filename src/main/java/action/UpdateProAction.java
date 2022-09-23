package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;
import project.BoardDTO;

// /writePro.do
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		     request.setCharacterEncoding("utf-8");
		     String pageNum=request.getParameter("pageNum");
		     System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);
		     BoardDTO article=new BoardDTO();		     
		     article.setNum(Integer.parseInt(request.getParameter("num")));
		     article.setWriter(request.getParameter("writer"));
		     article.setSubject(request.getParameter("subject"));
		     article.setContent(request.getParameter("content"));//글내용
		     article.setPasswd(request.getParameter("passwd"));
		     
		    BoardDAO dbPro=new BoardDAO();
		    int check=dbPro.updateArticle(article);
		   
		    request.setAttribute("pageNum", pageNum);
		    request.setAttribute("check", check);
		    
		return "/updatePro.jsp";
	}
}
