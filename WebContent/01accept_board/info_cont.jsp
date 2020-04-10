<%@page import="com.test.model.InfoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap-3.4.1.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<style type="text/css">

	table{
		border-top: 1px solid gray;	
		border-bottom: 1px solid gray;	
		width: 90%;
	}
	#cont{
		border-right: 1px solid gray;	
		border-left: 1px solid gray;	
		background-color: lightgray;	
	}
	#title{
		font-size: 18px;
		color: balck;
		font-weight: bold;
	}
	#info{
		font-size: 13px;
		color: gray;
	}
 	#date{
		font-size: 13px;
		color: gray;
	} 
	#ownComm{
		height: 80pt;
		width: 60%;
		padding-bottom: 4%;
		font-size: 50%;
	}
	pre{
		background-color: lightgray;
		border: none;
	}
	.reply{
		width: "300px;";
	}
	.preNext{
		cursor: pointer;
		height: 30pt;
		border-bottom : 1px dotted lightgray; 
	}
	.contbutton{
		background-color:#eee ;
	}
	.contbutton:hover{
		background-color: #DEDEDE;
		border-color: #b4b4b4;
	}
	
	ul{
		list-style: none;
	}
</style>
</head>
<body>
	
	<c:set var="dto" value="${Cont }" />
	<div align="center">
		<br /><br /><br />
		<div align="right" style="padding-right: 10%;">
			<input class="btn contbutton" type="button" value="목록" 
				onclick="location.href='accept_list.do?mno=-1&accept=1'" />
		</div>
		<br />
		<div>
		<table cellspacing="0" id="cont">
			<tr>	
				<td style="padding-top: 2%;" colspan="2">
					<div class="col-xs-1" style="margin-left: -4%;"></div>
					<div class="col-xs-8">
						<span id="title">${dto.getBoard_title() }</span><span id="info">|정보광장 </span>
					</div>
					<div class="col-xs-2" style="margin-left: 12%;">
						<span id="date">${dto.getBoard_date()}</span></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr style="border-top: 1px solid gray; border-bottom: none" />
					<div class="col-xs-9 " align="left">
						<pre>작성자 : ${dto.getBoard_writer() }</pre>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">				
					<br />
					<div class="col-xs-10 col-md-8 col-xs-offset-1 col-md-offset-2" 
						style="margin-bottom: 2%; margin-right: 10%;">
					<pre style="height: auto">${dto.getBoard_cont() }</pre></div>
				</td>
			</tr>
			<tr >
				<td colspan="2" align="center">
					<br /><br />
					
				</td>
			</tr>
			<tr>
				<td align="center" style="padding-top:1%;" colsapn="2">
					<div class="rows">
					<div class="col-md-2"></div>
					<div class="col-xs-8">					
						<textarea rows="10" 
					style="height: 80%;width:80%;  margin-bottom:1%;"
								></textarea>
					</div>
					<div class="col-xs-4 col-md-2 col-lg-2">	
					<input type="button" 
						class="btn btn-primary"id="ownComm" 
						style="font-size: 50%; margin-top: 40%;"value="댓글작성" />
					</div>
					</div>
					
				</td>
			</tr>
			
			<tr>
				<td colspan="2"><hr width="100%" /></td>
			</tr>
				<tr>
					<td colspan="2">
						<div class="row">
							<div class="col-sm-1 "></div>
							<div class="col-sm-8 col-md-8">
							글쓴이 : 누구누구
							<br />
							내가 누구인가 너는 누구인가 나는 그것이 너무나도 궁금하다.
							ㅁㄴㅇㄻㄴㅇㄹㄴㅁㅇㄻㅇㄴㄹ
							ㅁㄴㅇㄻㅇㄻㄴㅇㄻㅇㄴㄹ
							ㄴㅇㄹㄴㅇㅁㄹㅇㄴㄹㅇㄴㅁㄻㄹ
							ㅁㄴㅇㄹㅇㄴㅁㄻㅇㄴㄹㅇㄴㅁㄹㅇㅁㄴㄹㅇㄴㅁㄹ
							</div>
							<div class="col-sm-2"align="center">
								<input type="button" class="btn btn-info input-lg"id="showTr" value="작성"/>
								
							</div>
						<br />
						</div>
						<div class="row hide" id="test">
							<br />
							<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
								<div class="col-sm-1 col-md-1 col-lg-1"></div>
								<div class="col-xs-1 col-sm-3 col-md-2 col-lg-2">
				
									<img alt="" src="./uploadFile/화살표.png"
										style="width:80%; height:80%;" />
									
								</div>
								<div class="col-xs-9 col-sm-6 col-md-8 col-lg-9">
									<textarea 
									style="width: 100%;" rows="6"></textarea>
								</div>
								<div class="col-xs-2 col-sm-2 col-md-3 col-lg-1">
									<ul>
										<li style="margin-bottom: 150%;">
											<input class="btn btn-info"type="button"
													 value="작성" />
										</li>
										<li>
											<input class="btn btn-info"type="button"
													id="cancle" value="취소" />
										</li>
									</ul>
								</div>
								<div class=" col-sm-1 col-md-1"></div>
						</div>
						</div>
					</td>
				</tr>
		</table>
		<div class="row" style="margin-top: 1%; margin-bottom: 3%;">
				<div class="col-sm-7 col-sm-offset-2" align="center">
					<input class="btn contbutton" type="button" value="답글 작성"
						style="margin-left: 7.5%;" />
				</div>
			<c:if test="${dto.getBoard_mno() == sessionScope.userNo }">
			<div class="col-sm-2" style=" margin-left: 0.5%;">
				<input type="button" class="btn contbutton" value="글수정" />
				<input type="button" class="btn contbutton"value="글삭제" />
			</div>
			</c:if>
		</div>
		<table style="margin-bottom: 4%;">	
			<thead>
				<tr style="margin-top: 5%; border-bottom: 1px solid gray; height: 20pt;">
					<th width="10%" class="text-center"></th>
					<th width="45%" >글제목</th>
					<th width="16%" class="text-center">작성자</th>
					<th width="10%" class="text-center">조회수</th>
					 <th width="20%" class="text-center">작성일자</th>
				</tr>
			</thead>
			<c:if test="${next.getBoard_title() != null }">
				<tr class="text-align preNext"
					onclick="location.href='add1HitInfo.do?board_no=${next.getBoard_no() }'">
					<td><img src="./uploadFile/pre_icon.gif">다음글</td>
					<td style="align: none;">${next.getBoard_title() }</td>
					<td>${next.getBoard_writer() }</td>
					<td>${next.getBoard_hit() }</td>
					<td>${next.getBoard_date() }</td>
				</tr>
			</c:if>
			<c:if test="${pre.getBoard_title()!= null }">
				<tr class="text-align preNext"
					onclick="location.href='add1HitInfo.do?board_no=${pre.getBoard_no() }'">
					<td>
						<img src="./uploadFile/next_icon.gif">이전글</td>
					<td style="align: none;">${pre.getBoard_title() }</td>
					<td>${pre.getBoard_writer() }</td>
					<td>${pre.getBoard_hit() }</td>
					<td>${pre.getBoard_date() }</td>
				</tr>
			</c:if>
		</table>
		</div>
	</div>
	<input type="hidden" id="mno" value="${dto.getBoard_mno() }" />
	<input type="hidden" id="userNo" value="${sessionScope.userNo }" />
<script type="text/javascript">

	$("#showTr").click(function(){
			$("#test").attr('class', 'row show');
			$("#showTr").attr('class','btn hide');
		
		});
</script>

</body>
</html>