package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String pageNum=request.getParameter("pageNum");
	    //추가
	    String passwd=request.getParameter("passwd");//입력 O
	    int num=Integer.parseInt(request.getParameter("num"));
	    System.out.println("deletePro.do의 num=>"
	                +num+",passwd="+passwd+",pageNum=>"+pageNum);
	    
	    BoardDAO dbPro=new BoardDAO();
	    int check=dbPro.deleteArticle(num,passwd);
	    
	    //공유
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("check", check);
		
		return "/deletePro.jsp";
	}
}
