<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Qna 게시판(삭제)</title>
<script language="JavaScript">           
  function deleteSave(){	
	if(document.delForm.passwd.value==''){
	alert("비밀번호를 입력하십시요.");
	document.delForm.passwd.focus();
	return false;
 	}
}         
</script>
</head>
<body>
<form method="post" name="delForm" action="/Project2/deletePro.do?pageNum=${pageNum}&num=${num}" onsubmit="return deleteSave()">
 	<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  		<tr height="30">
    		<td align=center>
      		<b>비밀번호를 입력해 주세요.</b></td>
  		</tr>
  		<tr height="30">
     	<td align=center >비밀번호 :   
       		<input type="password" name="passwd" size="8" maxlength="12">
		</td>
 		</tr>
 		<tr height="30">
    		<td align=center>
      			<input type="submit" value="글삭제" >
      			<input type="button" value="취소" onclick="document.location.href='/Project2/qna.do?pageNum=${pageNum}'">     
   			</td>
 		</tr>  
	</table> 
</form>
</body>
</html> 
