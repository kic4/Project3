package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.CartDAO;
import project.CartDTO;

public class CartContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String user_id=request.getParameter("user_id");
		int cart_id=Integer.parseInt(request.getParameter("cart_id"));
		int package_no=Integer.parseInt(request.getParameter("package_no"));
		int package_count=Integer.parseInt(request.getParameter("package_count"));		
		System.out.println("CartListAction의 user_id=>"+user_id+",cart_id="+cart_id+
				",package_no="+package_no+",package_count="+package_count);
		 
		CartDAO cart=new CartDAO();
		CartDTO cartPro= cart.getCart(user_id, package_no);
		
		request.setAttribute("user_id", user_id);
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("package_no", package_no);
		request.setAttribute("package_count", package_count);
		
		return "/cartContent.jsp";
	}

}
