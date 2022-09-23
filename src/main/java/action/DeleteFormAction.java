package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
	    System.out.println("deleteForm.do의 num=>"+num+",pageNum=>"+pageNum);
	    
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
		
		return "/deleteForm.jsp";
	}

}
