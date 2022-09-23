<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
   <c:if test="${check==1}">
   <script>
   		alert("글 삭제를 완료했습니다.");
   </script>
   <meta http-equiv="Refresh" content="0;url=/Project2/indexControl.jsp?content=qna.jsp">
  </c:if>
  <c:if test="${check==0}">
   <script>
        alert("비밀번호가 맞지않습니다.\n비밀번호를 다시 확인해주세요!");
        history.go(-1);
   </script>
  </c:if> 
  