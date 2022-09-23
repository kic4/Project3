package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFormAction implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String content = request.getParameter("content");
		if (content == null) {
			content = "FirstView.jsp";
		}
		
		
		return "/mainForm.jsp";
	}
}
