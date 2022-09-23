<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Qna 게시판(글수정)</title>
<script language="JavaScript" src="script.js"></script>
</head>
<body>  
<form method="post" name="writeform" action="/Project2/updatePro.do" onsubmit="return writeSave()">
<table>
	<input type="hidden" name="num" value="${article.num}">
	<input type="hidden" name="pageNum" value="${pageNum}"> 
	<tr>
		<td width="70" align="center">제 목</td>
		<td width="330">
			<input type="text" size="40" maxlength="50" name="subject" value="${article.subject}">
		</td>
	</tr>
	<tr>
		<td width="70" align="center">이름</td>
		<td width="150"><input type="text" size="10" maxlength="10" name="writer" value="${article.writer}"></td>
		<td width="70">암호</td>
		<td width="150"><input type="password" size="8" maxlength="12" name="passwd"></td>
	</tr>
	<tr>
		<td width="70" align="center">내 용</td>
		<td width="330"><textarea name="content" rows="13" cols="40">${article.content}</textarea></td>
	</tr>
	<tr>
		<td>
		<input type="submit" value="수정완료">
		<input type="reset" value="다시작성">
		<input type="button" value="취소" OnClick="window.location='/Project2/qna.do'">
		</td>
	</tr>
</table>
</form>     
</body>
</html>      
