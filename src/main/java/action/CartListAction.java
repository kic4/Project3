package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;
import project.CartDAO;

//요청명령어에 해당되는 명령어 처리클래스=액션클래스=컨트롤러클래스
public class CartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String user_id=request.getParameter("user_id");
		int cart_id=Integer.parseInt(request.getParameter("cart_id"));
		int package_no=Integer.parseInt(request.getParameter("package_no"));
		int package_count=Integer.parseInt(request.getParameter("package_count"));

		
		List cartList = null; // 화면에 출력할 레코드를 저장할 변수

		CartDAO cart = new CartDAO();
		cartList = cart.getCarts(user_id);

		request.setAttribute("user_id", user_id);
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("package_no", package_no);
		request.setAttribute("package_count", package_count);
		request.setAttribute("cartList", cartList);

		return "/cartListForm.jsp";
	}

}
