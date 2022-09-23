<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Qna 게시판(삭제)</title>

</head>
<body>
<form method="post" name="cartDelete" action="/Project2/cartDeletePro.do?user_id=${user_id}&cart_id=${cart_id}" onsubmit="return deleteSave()">
 	<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  		<tr height="30">
    		<td align=center>
      		<b>정말 삭제하시겠습니까?</b></td>
  		</tr>
 		<tr height="30">
    		<td align=center>
      			<input type="submit" value="글삭제" >
      			<input type="button" value="취소" onclick="document.location.href='/Project2/cartListForm.do'">     
   			</td>
 		</tr>  
	</table> 
</form>
</body>
</html> 
