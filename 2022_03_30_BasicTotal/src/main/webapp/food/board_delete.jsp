<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
  margin-top: 50px;
}
.row{
  margin: 0px auto;
  width:950px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">삭제하기</h1>
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-center">
					비밀번호 <input type=password class="input-sm" id="pwd" size=15 v-model="pwd">
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<button class="btn btn-sm btn-danger" v-on:click="del()">삭제</button>
						<button class="btn btn-sm btn-primary" v-on:click="cancel()">취소</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				pwd:'',
				no:${no},
				msg:''
			},
			methods:{
				cancel:function(){
					history.back()
				},
				del:function(){
					if(this.pwd==""){
						// 유효성 검사
						let p=document.getEelementById("pwd");
						p.focus();
						return;
					}
					// 서버로 값을 전
					axios.get('http://localhost:8080/web/food/board_delete_ok.do',{
						params:{
							pwd:this.pwd,
							no:this.no
						}
					}).then(res=>{
						this.msg=res.data
						console.log(this.msg)
						if(this.msg=="YES"){
							location.href="board.do";
						}else{
							alert("비밀번호가 틀립니다!")
							let i=document.getElementById("pwd");
							this.pwd="";
							i.focus();
						}
					})
				}
			}
		})
	</script>
</body>
</html>