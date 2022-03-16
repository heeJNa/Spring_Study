<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin:0 auto;
	width:960px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>일반게시판(spring-MVC사용)</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-warning">새글</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr class="success">
					<th class="text-center" width=10%>번호</th>
					<th class="text-center" width=45%>제목</th>
					<th class="text-center" width=15%>이름</th>
					<th class="text-center" width=20%>작성일</th>
					<th class="text-center" width=10%>조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td class="text-center" width=10%>${vo.no }</td>
						<td width=45%><a href="detail.do?no=${vo.no}">${vo.subject }</a></td>
						<td class="text-center" width=15%>${vo.name }</td>
						<td class="text-center" width=20%>
							<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="text-center" width=10%>${vo.hit }</td>
					</tr>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-left">
						<input type="checkbox" value="N" name="fs" >이름
						<input type="checkbox" value="S" name="fs" >제목
						<input type="checkbox" value="C" name="fs" >내용
						<input type="text" name="ss" class="input-sm" size=15>
						<input type="submit" value="검색" class="btn btn-sm btn-info">
					</td>
					<td class="text-right">
						<a href="#" class="btn btn-sm btn-danger">이전</a>
							${curpage } page / ${totalpage } pages
						<a href="#" class="btn btn-sm btn-danger">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>