package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num=0,ref=1,re_step=0,re_level=0;
		
		if(request.getParameter("num")!=null){
		     num=Integer.parseInt(request.getParameter("num"));
		     ref=Integer.parseInt(request.getParameter("ref"));
		     re_step=Integer.parseInt(request.getParameter("re_step"));
		     re_level=Integer.parseInt(request.getParameter("re_level"));
		     System.out.println("content.jsp에서 넘어온 매개변수확인");
		     System.out.println("num=>"+num+",ref="+ref+",re_step="+re_step+",re_level="+re_level);
		   }
		   request.setAttribute("num", num);
		   request.setAttribute("ref", ref);
		   request.setAttribute("re_step", re_step);
		   request.setAttribute("re_level", re_level);
		
		return "/writeForm.jsp";
	}
}
