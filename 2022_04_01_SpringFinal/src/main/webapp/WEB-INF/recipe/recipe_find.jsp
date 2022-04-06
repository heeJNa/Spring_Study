<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="wrapper row2">
	  <div id="breadcrumb" class="clear"> 
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">레시피</a></li>
	      <li><a href="#">레시피 검색</a></li>
	    </ul>
	  </div>
	</div>
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <!-- main body -->
	    <div class="content"> 
	      <div id="gallery">
	        <figure>
	          <header class="heading">
	          		<input type="text" size=20 class="input-sm" style="float: left" v-model="fd" :value="fd">
	          		<input type="button" value="검색" class="btn btn-sm btn-danger">
	          </header>
	          <ul class="nospace clear">
		          <li class="one_quarter first" v-for="(vo,index) in recipe_list" v-if="index%4==0"><a :href="'../recipe/detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title"></a></li>
		          <li class="one_quarter" v-else><a :href="'../recipe/detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title"></a></li>
	          </ul>
	        </figure>
	      </div>
		      <nav class="pagination">
		        <!-- class="current" -->
		       	 <ul>
		          <li><button v-on:click="prev()">이전</button></li>
		          <li>{{curpage}} page / {{totalpage}} pages</li>
		          <li><button v-on:click="next()">다음</button></li>
		        </ul>
		       </nav>    
	       </div>
	    <!-- / main body -->
	    <div class="clear"></div>
	  </main>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				recipe_list:[],
				curpage:1,
				totalpage:0,
				fd:'샐러드'
			},
			mounted:function(){
				this.recipeData();
			},
			methods:{
				recipeData:function(){
					let _this=this;
	    			axios.get("http://localhost:8080/web/recipe/recipe_find_vue.do",{
	    				params:{
	    					page:_this.curpage,
	    					fd:_this.fd
	    				}
	    			// then(res=>{})
	    			}).then(function(res){
	    				console.log(res.data)
	    				_this.recipe_list=res.data;
	    				_this.curpage=res.data[0].curpage;
	    				_this.totalpage=res.data[0].totalpage;
	    			})
	    		},
	    		prev:function(){
	    			this.curpage=this.curpage>1?this.curpage-1:this.curpage;
	    			this.recipeData();
	    		},
	    		next:function(){
	    			this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
	    			this.recipeData();
	    		},
	    		find:function(){
	    			this.curpage=1;
	    			this.recipeData();
	    		}
	    	}
	    })
	</script>
</body>
</html>