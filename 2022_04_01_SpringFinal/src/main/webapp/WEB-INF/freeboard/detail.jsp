<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-sm btn-danger">수정</a>
						<a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-sm btn-success">삭제</a>
						<a href="../freeboard/list.do" class="btn btn-sm btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
	  </main>
	</div>  
	
</body>
</html>