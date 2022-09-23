<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna 게시판 (내용 상세보기)</title>
</head>
<body>
<form>


<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">  
  <tr height="30">
     <td align="center" width="125">글번호</td>
     <td align="center" width="125" align="center">${cartPro.num}</td>
     <td align="center" width="125">조회수</td>
     <td align="center" width="125" align="center">${cartPro.readcount}</td>
  </tr>
  <tr height="30">
     <td align="center" width="125">작성자</td>
     <td align="center" width="125" align="center">${cartPro.writer}</td>
     <td align="center" width="125">작성일</td>
     <td align="center" width="125" align="center">${article.reg_date}</td>
  </tr>
  <tr height="30">
     <td align="center" width="125">글제목</td>
     <td align="center" width="375" align="center" colspan="3">${article.subject}</td>
  </tr>
  <tr>
    <td align="center" width="125">글내용</td>
    <td align="left" width="375" colspan="3">
       <pre>${article.content}</pre>
  	</td>
  </tr>
  <tr height="30">      
     <td colspan="4" align="right" > 
	  	<input type="button" value="글수정" onclick="document.location.href='/Project2/updateForm.do?num=${article.num}&pageNum=${pageNum}'">
	   	&nbsp;&nbsp;&nbsp;&nbsp;
	  	<input type="button" value="글삭제" onclick="document.location.href='/Project2/deleteForm.do?num=${article.num}&pageNum=${pageNum}'">
     	&nbsp;&nbsp;&nbsp;&nbsp;
      	<input type="button" value="답글쓰기" onclick="document.location.href='/Project2/writeForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">
	    &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="글목록" onclick="document.location.href='/Project2/qna.do?pageNum=${pageNum}'">
     </td>
  </tr>
</table>    
</form>      
</body>
</html>