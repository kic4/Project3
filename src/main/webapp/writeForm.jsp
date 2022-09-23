<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Qna 게시판</title>
<script language="JavaScript" src="script.js"></script>
</head>
<body>
	<b>글쓰기</b>
	<form method="post" name="writeform" action="/Project2/writePro.do" onsubmit="return writeSave()">
	<input type="hidden" name="num" value="${num}">
	<input type="hidden" name="ref" value="${ref}">
	<input type="hidden" name="re_step" value="${re_step}">
	<input type="hidden" name="re_level" value="${re_level}">
	<table>
		<tr>
			<td width="70" align="center">제 목</td>
			<td width="330">
			<c:if test="${num==0}">
				<input type="text" size="40" maxlength="50" name="subject">
			</c:if>
			<c:if test="${num!=0}">
				<input type="text" size="40" maxlength="50" name="subject" value="[re]">
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="70" align="center">이름</td>
			<td width="150"><input type="text" size="10" maxlength="10" name="writer"></td>
			<td width="70">암호</td>
			<td width="150"><input type="password" size="8" maxlength="12" name="passwd"></td>
		</tr>
		<tr>
			<td width="70" align="center">내 용</td>
			<td width="330"><textarea name="content" rows="13" cols="40"></textarea></td>
		</tr>
		<tr>
			<td>
			<input type="submit" value="작성">
			<input type="reset" value="다시작성">
			<input type="button" value="취소" OnClick="window.location='/Project2/qna.do'">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>