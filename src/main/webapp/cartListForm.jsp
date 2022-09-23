<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/jinscript.css">
<title>메인페이지-로그인완료</title>
</head>
<body>
	<header>
		<div class="cart">
			<img src="img/icon_cart.png" alt="장바구니 버튼"><a href="/Project2/cart.do">장바구니</a>
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
							<li><a href="#">Q&A</a></li>
							<li><a href="#">이벤트</a></li>
						</ul>
				</a></li>
			</ul>
		</div>
	</header>
	<div>
		<table class="cartPage" >
			<tr>
				<h2>장바구니</h2>
			</tr>
			<tr>
				<th width="100">장바구니 번호</th>
				<th width="200">고객 아이디</th>
				<th width="100">상품 번호</th>
				<th width="100">수량<th>
			</tr>
			<c:forEach var="cart"  items="${cartList}">
		 	 	<tr height="30">
		   	 		<td align="center"  width="100">${cart_id}</td>
		    		<td align="center"  width="200">${user_id}</td>
		    		<td align="center"  width="100"><a href="/Project2/cartContent.do?package_no=${package_no}&user_id=${user_id}">${package_no}</a></td>
		    		<td align="center"  width="100">${package_count}</td>
		  		</tr>
		    </c:forEach>
		</table>
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
