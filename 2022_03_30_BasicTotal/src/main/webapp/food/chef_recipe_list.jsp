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
  width:100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				
				<form method="post" action="chef_recipe_list.do">
					<input type="text" name=ss size=35 class="input-sm">
					<input type="hidden" name=chef value="${chef }">
					<input type=submit value="검색" class="btn btn-sm btn-primary">
					<a href="chef_recipe_list.do?chef=${chef }&ss=all">전체</a>
				</form>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<c:forEach var="vo" items="${list}">
		        <div class="col-md-3">
				    <div class="thumbnail">
				      <a href="#">
				        <img src="${vo.poster }" alt="Lights" style="width:230px;height:180px">
				        <div class="caption">
				          <p style="font-size: 8px">${vo.title }</p>
				          <p style="font-size: 8px">By&nbsp;${vo.chef }</p>
				        </div>
				      </a>
				    </div>
				  </div>
		      </c:forEach>
		</div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${startPage>1 }">
						<li><a href="chef_recipe_list.do?page=${startPage-1 }&chef=${chef}">&lt;</a></li>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:if test="${curpage==i }">
							<c:set var="style" value="class=active"/>
						</c:if>
						<c:if test="${curpage!=i }">
							<c:set var="style" value=""/>
						</c:if>
						<li><a href="recipe.do?page=${i} }">${i }</a></li>
					</c:forEach>
					<c:if test="${endPage<totalpage }">
						<li ${style }><a href="chef_recipe_list.do?page=${endpage+1}&chef=${chef} ">&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>	
</html>