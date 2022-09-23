package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;
import project.BoardDTO;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	   int num=Integer.parseInt(request.getParameter("num"));
	   String pageNum=request.getParameter("pageNum");
	   System.out.println("UpdateFormAction에서의 pageNum=>"+pageNum);
	   BoardDAO dbPro=new BoardDAO();
	   BoardDTO article=dbPro.updateGetArticle(num);
 	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("article",article);
	
	   return "/updateForm.jsp";
	}
}
