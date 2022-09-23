package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.BoardDAO;
import project.BoardDTO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
	    BoardDTO article=new BoardDTO();
	    
	    article.setNum(Integer.parseInt(request.getParameter("num")));
	    article.setWriter(request.getParameter("writer"));
	    article.setSubject(request.getParameter("subject"));
	    article.setPasswd(request.getParameter("passwd"));
	    article.setReg_date(new Timestamp(System.currentTimeMillis()));
	    article.setRef(Integer.parseInt(request.getParameter("ref")));
	    article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	    article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
	    article.setContent(request.getParameter("content"));
	    article.setIp(request.getRemoteAddr());
	    BoardDAO dbPro=new BoardDAO();
	    dbPro.insertArticle(article);
		return "/writePro.jsp";
	}

}
