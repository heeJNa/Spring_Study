<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper row2">
	  <div id="breadcrumb" class="clear"> 
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">레시피</a></li>
	      <li><a href="#">레시피 목록</a></li>
	    </ul>
	  </div>
	</div>
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <!-- main body -->
	    <div class="content"> 
	      <div id="gallery">
	        <figure>
	          <header class="heading">총<span style="color:green;font-size: 40px">176,109</span> 개의 맛있는 레시피가 있습니다</header>
	          <ul class="nospace clear">
		          <c:forEach var="vo" items="${rList }" varStatus="s">
		          	<c:if test="${s.index%4==0 }">
		          		<li class="one_quarter first"><a href="../recipe/detail_before.do?no=${vo.no }"><img src="${vo.poster }" title="${vo.title }"></a></li>
		          	</c:if>
		          	<c:if test="${s.index%4!=0 }">
		          		<li class="one_quarter"><a href="../recipe/detail_before.do?no=${vo.no }"><img src="${vo.poster }" title="${vo.title }"></a></li>
		          	</c:if>
		          </c:forEach>
	          </ul>
	        </figure>
	      </div>
	      <nav class="pagination">
	        <ul>
	         <c:if test="${startPage>1 }">
	          <li><a href="../recipe/list.do?page=${startPage-1 }">&laquo; Previous</a></li>
	          </c:if>
	          <c:forEach var="i" begin="${startPage }" end="${endPage }">
	          	<c:if test="${i==curpage }">
	          		<c:set var="style" value="class=current"></c:set>
	          	</c:if>
	          	<c:if test="${i!=curpage }">
	          		<c:set var="style" value=""></c:set>
	          	</c:if>
	           	<li ${style }><a href="../recipe/list.do?page=${i }" style="color:black;font-weight: 500">${i }</a></li>
	          </c:forEach>
	          <c:if test="${endPage<totalpage }">
	          	<li><a href="../recipe/list.do?page=${endPage+1 }">Next &raquo;</a></li>
	          </c:if>	
	        </ul>
	      </nav>
	    </div>
	    <!-- / main body -->
	    <h3>최근 방문 레시피</h3>
	    <hr>
	    <div class="row">
	    	<c:forEach var="vo" items="${cList }" varStatus="s">
	    		<c:if test="${s.index<6 }">
	    			<a href="../recipe/detail.do?no=${vo.no }">
	    				<img src="${vo.poster }" style="width: 150px;height: 150px"
							title="${vo.title }"	    				
	    				>
	    			</a>
	    		</c:if>
	    	</c:forEach>
	    </div>	
	    <div class="clear"></div>
	  </main>
	</div>
</body>
</html>