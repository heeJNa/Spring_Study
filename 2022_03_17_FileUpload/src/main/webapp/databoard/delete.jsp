<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	면접: 형상관리, 협업, 프로젝트 (개인) 동작과정
 --%>    
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
	width:350px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>삭제하기</h1>
		<div class="row">
			<form method="post" action="delete_ok.do">
				<table class="table">
					<tr>
						<td class="text-center">
							비밀번호 <input type=password name=pwd class="input-sm" size=15>
							<input type="hidden" name=no value="${no }">
						</td>
					</tr>
					<tr>
						<td class="text-center">
							<input type="submit" value="삭제" class="btn btn-sm btn-danger">
							<input type="button" value="취소" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>