package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;
import project.CartDAO;
import project.CartDTO;

public class CartAddProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
	    CartDTO cart=new CartDTO();
	    cart.setCart_id(Integer.parseInt(request.getParameter("cart_id")));
	    cart.setUser_id(request.getParameter("user_id"));
	    cart.setPackage_no(Integer.parseInt(request.getParameter("package_no")));
	    cart.setPackage_count(Integer.parseInt(request.getParameter("package_count")));
	    
	    CartDAO cartPro = new CartDAO();
	    cartPro.cartAdd(cart);
	    
		
	    return "/cartAddPro.jsp";
	}

}
