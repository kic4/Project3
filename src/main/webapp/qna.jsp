<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/jinscript.css">
<title>Qna 게시판</title>
</head>
<body>
	<header>
		<div class="cart">
			<img src="img/icon_cart.png" alt="장바구니 버튼"><a href="/Project2/cart.jsp">장바구니</a>
		</div>
		<div class="mypage">
			<img src="img/icon_mypage.png" alt="마이페이지 버튼"><a href="">마이페이지 </a>
		</div>
		<div class="logout">
			<a href="">로그아웃</a>
		</div>
		<div>
			<ul class="dropdownmenu">
				<li><a href="#">영어
						<ul>
							<li><a href="#">왕초보!영어</a></li>
							<li><a href="#">레벨업!영어</a></li>
						</ul>
				</a></li>
				<li><a href="#">일본어
						<ul>
							<li><a href="#">왕초보!일본어</a></li>
							<li><a href="#">레벨업!일본어</a></li>
						</ul>
				</a></li>
				<li><a href="#">중국어
						<ul>
							<li><a href="#">왕초보!중국어</a></li>
							<li><a href="#">레벨업!중국어</a></li>
						</ul>
				</a></li>
				<li><a href="#">스페인어
						<ul>
							<li><a href="#">왕초보!스페인어</a></li>
							<li><a href="#">레벨업!스페인어</a></li>
						</ul>
				</a></li>
				<li><a href="#">커뮤니티
						<ul>
							<li><a href="#">공지사항</a></li>
							<li><a href="/Project2/qna.do">Q&A</a></li>
							<li><a href="#">이벤트</a></li>
						</ul>
				</a></li>
			</ul>
		</div>
		<div>
			<ul class="sidemenu">
				<li>커뮤니티</li>
				<li><a href="#">공지사항</a></li>
				<li style="background-color:#F6D8CE"><a href="/Project2/qna.do"><b>Q&A</b></a></li>
				<li><a href="#">이벤트</a></li>
			</ul>
		</div>
	</header>	
	<div class="qna">
	<center>
	<h2><b>QnA 게시판</b></h2>
		<table class="write">
			<tr>
				<td>
					<a href="/Project2/writeForm.do">글쓰기</a>
				</td>
			</tr>
		</table>
		<c:if test="${count==0}">
   			<tr>
       			<td align="center">게시판에 저장된 글이 없습니다.</td>
   			</tr>
			</table>
		</c:if>
		<c:if test="${count > 0}">
			<table class="qna_board">
				<thead>
				<tr height="30">
					<td align="center" width="50">번호</td>
					<td align="center" width="250">제목</td>
					<td align="center" width="100">작성자</td>
					<td align="center" width="150">작성일</td>
					<td align="center" width="50">조회수</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="article"  items="${articleList}">
		 	 	<tr height="30">
		   	 		<th width="50" align="center">
		              <c:out value="${number}" />
		              <c:set var="number"  value="${number-1}" />
		   			</th>
		   			
		   			<td  width="250" >
			 		<c:if test="${article.re_level > 0}">
				  		<img src="img/level.gif" width="${7*article.re_level}" height="16">
			 	  		<img src="img/re.gif">
			 		</c:if>
			 		<c:if test="${article.re_level==0}">
					<img src="img/level.gif" width="${7*article.re_level}" height="16">   
			    	</c:if>
		      		<a href="/Project2/content.do?num=${article.num}&pageNum=${currentPage}">${article.subject}</a>
		      		</td>
		      		
		    		<td align="center"  width="100">${article.writer}</a></td>
		    		<td align="center"  width="150">${article.reg_date}</td>
		    		<td align="center"  width="50">${article.readcount}</td>
		  		</tr>
		    	</c:forEach>
		    	</tbody>
			</table>
		</c:if>
		
		<!-- 페이징 처리 -->
		<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0?0:1)}" />
			<c:set var="startPage" value="${currentPage-((currentPage-1)%blockSize) }" />     
			<c:set var="endPage" value="${startPage+blockSize-1}" />
		<c:if test="${endPage > pageCount }" >
			<c:set var="endPage"  value="${endPage=pageCount}" />
		</c:if>
		<c:if test="${startPage > blockSize}">
			<a href="/Project2/qna.do?pageNum=${startPage-blockSize}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href="/Project2/qna.do?pageNum=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${endPage <pageCount}">
      		<a href="/Project2/qna.do?pageNum=${startPage+blockSize}">[다음]</a>
 		</c:if>  
		</c:if>
	</center>
	</div>
	<section>
		<ul class="company_info">
			<li>
				<ul>
					<li>(주)데이원컴퍼니</li>
					<li>대표이사: 이강민</li>
					<li>개인정보책임관리자: 이강민</li>
					<li>원격평생교육원 제 원-572호</li>
				</ul>
			</li>
			<li>
				<ul>
					<li><b>사업자번호</b>: 810-86-00658</li>
					<li><b>통신판매업번호:</b></li>
					<li>제 2017-서울강남-01977호</li>
					<li><b>주소</b>: 서울특별시 강남구 강남대로 364, 10층 11층 15층(역삼동,마왕빌딩)</li>
				</ul>
			</li>
			<li>
				<ul>
					<li><b>Email</b>:</li>
					<li>고객센터: <a href="mailto:mylight_help@fastcampus.co.kr">mylight_help@fastcampus.co.kr</a></li>
					<li>마케팅: <a href="mailto:my_light@fastcampus.co.kr">my_light@fastcampus.co.kr</a></li>
					<li><b>대표번호</b>: 02-508-8993</li>
					<li><b>운영시간</b>: 10:00~19:00</li>
					<li>(점심시간 12시~13시 /주말 및 공휴 제외)</li>
				</ul>
			</li>
		</ul>
	</section>
	<footer class="ft1">
		<div>Copyright Ⓒ 2020 (주)데이원컴퍼니.All rights reserved</div>
		<nav class="nv3">
			<ul>
				<li><a href="#">이용약관</a></li>
				<li class="l1">|</li>
				<li><a href="#">개인정보처리방침</a></li>
			</ul>
			<br>
		</nav>
	</footer>
</body>
</html>
