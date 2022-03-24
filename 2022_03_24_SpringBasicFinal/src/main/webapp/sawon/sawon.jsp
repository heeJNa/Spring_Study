<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div id="app">
		<h1>이름:{{sawon.sabun}}</h1>
		<h1>이름:{{sawon.name}}</h1>
		<h1>이름:{{sawon.dept}}</h1>
		<h1>이름:{{sawon.job}}</h1>
	</div>
	<script>
		new Vue({
			el:'#app',
			data:{
				sawon:{}
			},
			mounted:function(){
				axios.get('http://localhost:8080/web/sawon/list.do')
				.then(res=>{
					this.sawon=res.data
				})
			}
		})
	</script>
</body>
</html>