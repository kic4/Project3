package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartDeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String user_id=request.getParameter("user_id");
		int cart_id=Integer.parseInt(request.getParameter("cart_id"));

	    System.out.println("CartDeleteFormAction의 user_id=>"+user_id+",cart_id=>"+cart_id);
	    
	    request.setAttribute("user_id", user_id);
	    request.setAttribute("cart_id", cart_id);
		
		return "/cartDeleteForm.jsp";
	}

}
