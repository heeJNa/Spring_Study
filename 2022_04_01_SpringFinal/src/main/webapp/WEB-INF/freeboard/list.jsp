<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
  margin: 0px auto;
  width:850px;
}
h1{
	text-align:center;
}
</style>
</head>
<body>
	<div class="wrapper row2">
	  <div id="breadcrumb" class="clear"> 
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">커뮤니티</a></li>
	      <li><a href="#">자유게시판</a></li>
	    </ul>
	  </div>
	</div>
	<div class="wrapper row3">
	  <main class="container clear">
	  	<div class="row">
	  		<h3 class="text-center">자유게시판</h3>
	  		<table class="table">
	  			<tr>
	  				<td>
	  					<a href="insert.do" class="btn btn-sm btn-info">새글</a>
	  				</td>
	  			</tr>
	  		</table>
	  		<table class="table table-striped">
	  			<tr>
	  				<th width=10% class="text-center">번호</th>
	  				<th width=45% class="text-center">제목</th>
	  				<th width=15% class="text-center">이름</th>
	  				<th width=15% class="text-center">작성일</th>
	  				<th width=10% class="text-center">조회수</th>
	  			</tr>
	  			<c:set var="count" value="${count }"/>
	  			<c:forEach var="vo" items="${list }">
	  				<tr>
		  				<td width=10% class="text-center">${count }</td>
		  				<td width=45%><a href="../freeboard/detail.do?no=${vo.no}">"${vo.subject }</a></td>
		  				<td width=15% class="text-center">${vo.name }</td>
		  				<td width=15% class="text-center">${vo.dbday }</td>
		  				<td width=10% class="text-center">${vo.hit }</td>
		  			</tr>
		  			<c:set var="count" value="${count-1 }"/>
	  			</c:forEach>
	  		</table>
	  	</div>
	  	<nav class="pagination">
	        <ul>
	         <c:if test="${startPage>1 }">
	          <li><a href="../freeboard/list.do?page=${startPage-1 }">&laquo; Previous</a></li>
	          </c:if>
	          <c:forEach var="i" begin="${startPage }" end="${endPage }">
	          	<c:if test="${i==curpage }">
	          		<c:set var="style" value="class=current"></c:set>
	          	</c:if>
	          	<c:if test="${i!=curpage }">
	          		<c:set var="style" value=""></c:set>
	          	</c:if>
	           	<li ${style }><a href="../freeboard/list.do?page=${i }" style="color:black;font-weight: 500">${i }</a></li>
	          </c:forEach>
	          <c:if test="${endPage<totalpage }">
	          	<li><a href="../freeboard/list.do?page=${endPage+1 }">Next &raquo;</a></li>
	          </c:if>	
	        </ul>
	      </nav>
	  </main>
	</div>  
</body>
</html>