<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['KeyWord', 'KeyWordCount'],
          <c:forEach var="kvo" items="${list}">
          	['<c:out value="${kvo.word}"/>', <c:out value="${kvo.count}"/>],
          </c:forEach>
        ]);

        var options = {
          title: '단어분석',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
</script>
</head>
<body>
	<div class="wrapper row2">
	  <div id="breadcrumb" class="clear"> 
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">커뮤니티</a></li>
	      <li><a href="#">묻고답하기</a></li>
	    </ul>
	  </div>
	</div>
	<div class="wrapper row3">
	  <main class="container clear">
	  	<div class="row">
	  		<h3 class="text-center">상세보기</h3>
			<table class="table">
				<tr>
					<th width=20% class="text-center">번호</th>
					<td width=30% class="text-center">${vo.no}</td>
					<th width=20% class="text-center">작성일</th>
					<td width=30% class="text-center">${vo.dbday}</td>
				</tr>
				<tr>
					<th width=20% class="text-center">이름</th>
					<td width=30% class="text-center">${vo.name}</td>
					<th width=20% class="text-center">조회수</th>
					<td width=30% class="text-center">${vo.hit}</td>
				</tr>
				<tr>
					<th width=20% class="text-center">제목</th>
					<td colspan="3">${vo.subject}</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200">
						<pre style="white-space:pre-wrap;border: none">${vo.content}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<c:if test="${count!=1 }">
							<a href="../reply/reply.do?no=${vo.no }" class="btn btn-sm btn-info">답변</a>
						</c:if>
						<a href="../reply/update.do?no=${vo.no }" class="btn btn-sm btn-danger">수정</a>
						<a href="../reply/delete.do?no=${vo.no }" class="btn btn-sm btn-success">삭제</a>
						<a href="../reply/list.do" class="btn btn-sm btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="piechart_3d" style="height: 500px;"></div>
	  </main>
	</div>  
</body>
</html>