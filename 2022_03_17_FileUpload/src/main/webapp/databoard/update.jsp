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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let fileIndex=0;
$(function(){
	$('#pwd').keyup(function(){
		// 비밀번호 검색 => 활성화/ 비활성화
		let pwd=$('#pwd').val()
		let no=$('#pwd').attr("data-no")
		if($.trim(pwd)==""){
			$('#pwd').focus()
			return
		}
		// ajax
		$.ajax({
			type:'post',
			url:'passwordCheck.do',
			data:{"no":no,
				"pwd":pwd},
			success:function(res){
				let r = parseInt($.trim(res))
				if(r==0){
					$('#updateBtn').attr("disabled",true)
					$('#result').text("비밀번호가 틀립니다!")
				}else{
					$('#updateBtn').attr("disabled",false)
					$('#result').text("비밀번호가 맞습니다!")
				}
			}	
			
		})
	})
	$('#updateBtn').click(function(){
		$('#frm').submit()
	})
})
</script>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<div class="row">
			<form action="update_ok.do" method="post" id="frm">
				<table class="table">
					<tr>
						<th class="text-right warning" width=15%>이름</th>
						<td width="85%">
							<input type="text" name=name size=15 class="input-sm" value="${vo.name }">
							<input type="hidden" name=no value="${vo.no }">
						</td>
					</tr>
					<tr>
						<th class="text-right warning" width=15%>제목</th>
						<td width="85%">
							<input type="text" name=subject size=45 class="input-sm" value="${vo.subject }">
						</td>
					</tr>
					<tr>
						<th class="text-right warning" width=15%>내용</th>
						<td width="85%">
							<textarea rows="10" cols="50" name=content>${vo.content }</textarea>
						</td>
					</tr>
					<tr>
						<th class="text-right warning" width=15%>비밀번호</th>
						<td width="85%">
							<input type="password" name=pwd size=10 class="input-sm" id="pwd" data-no="${vo.no }">
							<div id="result">
							
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<input type=button value="수정" class="btn btn-sm btn-danger" id="updateBtn" disabled>
							<input type=button value="취소" class="btn btn-sm btn-info" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>