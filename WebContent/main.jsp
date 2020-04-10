<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cafe :in <c:if test="${userNo > 100 }">Welcome, ${userName}님!</c:if>
<c:if test="${userNo <= 100 }">주인님 안녕하세요!</c:if></title>
<link rel="stylesheet" href="css/bootstrap-3.4.1.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<style type="text/css">
	.title{
		border-top:1px solid green;
		border-bottom: 1px solid green;
		margin: 3%;
	}
	.text{
		text-decoration: underline;
		size: 140%;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row" align="center">
			<div class="col-xs-1 col-sm-2 col-md-2 "></div>
			<div class="col-xs-10 col-sm-8 col-md-8 ">
				<hr width="100%" class="title" />
					<c:if test="${userNo > 100 }">
					<h3>안녕하세요? ${userName }님. 회원광장입니다!</h3>
					</c:if>
					<c:if test="${userNo <= 100 }">
					<h2>개미는 뚠뚠♪ 오늘도 뚠뚠♪ 열심히 일을 하네 뚠뚠!♪</h2>
					<h3>오늘도 힘내세유!</h3>
					</c:if>
				<hr width="100%" class="title" />
			</div>
		</div>
	<br /><br />
		<div class="row text-center">
			<a href="accept_list.do?mno=-1&&accept=1" class="text">정보광장</a>&nbsp;&nbsp;
			<c:if test="${userNo <= 100 }" >
			<a id="mrgPage" href="temp_list.do?mno=${userNo }&&accept=0" class="text">대기글 리스트</a>
			<a id="noticePage" href="notice_list.do?mno=-1&&accept=-1" class="text">공지사항 관리</a>
			</c:if>
			<c:if test="${userNo > 100 }">
			<a id = "myPage" href="my_list.do?mno=${userNo }&&accept=-1" class="text">마이 리스트</a>
			</c:if>
		</div>
	</div>
</body>
</html>