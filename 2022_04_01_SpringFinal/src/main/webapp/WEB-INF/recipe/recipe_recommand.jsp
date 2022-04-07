<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">

</style>
</head>
<body>
	<div class="wrapper row2">
	  <div id="breadcrumb" class="clear"> 
	    <ul>
	      <li><a href="#">Home</a></li>
	      <li><a href="#">레시피</a></li>
	      <li><a href="#">레시피 추천</a></li>
	    </ul>
	  </div>
	</div>
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <!-- main body -->
	    <div class="content"> 
	      <div id="gallery">
	        <figure>
	        <!-- 
	        	종류별 전체밑반찬메인반찬국/탕찌개디저트면/만두밥/죽/떡퓨전김치/젓갈/장류양념/소스/잼양식샐러드스프빵과자차/음료/술기타
				상황별 전체일상초스피드손님접대술안주다이어트도시락영양식간식야식푸드스타일링해장명절이유식기타
				재료별 전체소고기돼지고기닭고기육류채소류해물류달걀/유제품가공식품류쌀밀가루건어물류버섯류과일류콩/견과류곡류기타
				방법별 전체볶음끓이기부침조림무침비빔찜절임튀김삶기굽기데치기회기타
				테마별 여성/뷰티 엄마/아기 건강/질병 제철요리 추천
	         -->
	          <header class="heading">
	          	<button class="btn btn-sm btn-danger" v-on:click="recipe(1)">종류별</button>
	          	<button class="btn btn-sm btn-success" v-on:click="recipe(2)">상황별</button>
	          	<button class="btn btn-sm btn-info" v-on:click="recipe(3)">재료별</button>
	          	<button class="btn btn-sm btn-warning" v-on:click="recipe(4)">방법별</button>
	          	<button class="btn btn-sm btn-primary" v-on:click="recipe(5)">테마별</button>
	          </header>
	          <div class="row">
	          	<span class="btn btn-sm btn-warning" v-for="data in recom_list" v-on:click="find(data)">{{data}}</span>
	          </div>
	          <div style="height: 50px"></div>
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
				recom_list:[],
				fd:'',
				no:1,
				recipe_list:[],
				curpage:1,
				totalpage:0
			},
			mounted:function(){
				axios.get("http://localhost:8080/web/recipe/recipe_recommand_vue.do",{
					params:{
						no:this.no
					}
				}).then(res=>{
					console.log(res)
					console.log(res.data)
					this.recom_list=res.data;
				})
			},
			methods:{
				recipe:function(no){
					axios.get("http://localhost:8080/web/recipe/recipe_recommand_vue.do",{
						params:{
							no:no
						}
					}).then(res=>{
						console.log(res)
						console.log(res.data)
						this.recom_list=res.data;
					})
				},
				find:function(fd){
					if(this.fd!=fd){
						this.curpage=1
					}
					this.fd=fd;
					axios.get("http://localhost:8080/web/recipe/recipe_recom_data_vue.do",{
						params:{
							fd:fd,
							page:this.curpage
						}
					}).then(res=>{
						console.log(res)
						console.log(res.data)
						this.recipe_list=res.data;
						this.curpage=res.data[0].curpage
						this.totalpage=res.data[0].totalpage
					})
				},
				prev:function(){
	    			this.curpage=this.curpage>1?this.curpage-1:this.curpage;
	    			this.find(this.fd);
	    		},
	    		next:function(){
	    			this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
	    			this.find(this.fd);
	    		},
			}
		})
	</script>
</body>
</html>