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
	margin:0 auto;
	width:600px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<div class="row">
			<form method="post" action="update_ok.do">
				<table class="table">
					<tr>
						<td width=20% class="text-right warning">이름</td>
						<td width=80%>
							<input type="text" name="name" size=15 class="input-sm" required value="${vo.name }">
							<input type="hidden" name="no" value="${vo.no }">
						</td>
					<tr>	
						<td width=20% class="text-right warning">제목</td>
						<td width=80%>
							<input type="text" name="subject" size=45 class="input-sm" required value="${vo.subject }">
						</td>
					</tr>	
					<tr>	
						<td width=20% class="text-right warning">내용</td>
						<td width=80%>
							<textarea rows="8" cols="50" name="content" required>${vo.content }</textarea>
						</td>
					</tr>	
					<tr>	
						<td width=20% class="text-right warning">비밀번호</td>
						<td width=80%>
							<input type="text" name="pwd" size=10 class="input-sm" required>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="수정" class="btn btn-sm btn-danger">
							<input type="button" value="취소" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>