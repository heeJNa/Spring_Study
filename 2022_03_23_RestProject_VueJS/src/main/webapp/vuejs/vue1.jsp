<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		VueJS : 자바스크립트 라이브러리
		----- AngularJS에서 파생 => 누적 (유지보수)
		IT 단점 => 주춤하면 시대를 못따라감
		AngularJS === Jquery ============= React ========= VueJS
		---------    -------			   -----		   ----- 가상돔, 양방향, 라이브러리가 많지 않다	
		양방향    라이브러리양(무겁다)   속도가 빠르다
    								 가상돔을 이용하기 때문
    								      단방향 => class
    								        |
    								     Hooks (function)  
    								  ----------------------- 데이터를 읽어서 화면 (View)
    				데이터 관리 <===> 데이터를 출력 ==> MVC (Redux,vuex)   
    														============ mobx, saga (Framework)
    	= 가상돔? (메모리) => 게임 (더블버퍼링)
    	  ------ 트리형태 	
    	=> 가상돔에 올리는 방법
    	----------------------- 함수지원 => mounted()
        = 형식
        	<div>
        		<div id="aaa">
        		 	==
        		 	==
        		 	==
        		</div>
        	</div
        
        	<script>
        		new Vue({
        			1. 영역 (제어하는 영역) 지정
        			el:'css선택자' => id = #aaa, class = .aaa => container
        			data:{ => 멤버변수 지정 => 출력내용에 관련된 변수
        			  1) 목록 : [] (List)
        			  2) 객체 : {} (VO)  ==>{키:값,키:값}
        			  						 --
        			  						 멤버변수 => JS의 객체 => 표현하는 방법 JSON
        			  3) 문자열 : ''
        			  4) 정수 : 1
        			}
        			methods:{
        				사용자 정의 함수 : 이벤트 처리 함수
        			},
        			생명주기 함수
        			beforeCreate : vue객체 생성 전
        			created : vue 객체 생성 완료
        			beforeMount : 가상돔에 HTML을 올리기전
        			--------------- componentWillMount() : React함수
        			***mounted : 가상돔에 올라간 상태 => $(function(){}), window.onload
        			---------- componentDidMount()
        			beforeUpdate : 수정전
        			------------ componentWillUpdate()
        			***updated : 수정 완료 => 내용 변경
        			---------- componentDidUpdate()
        			beforeDestroy : 메모리 해제 전
        			destroyed : 메모리 해제 완료
        			
        		})
        	</script>
          디렉티브 : v-if, v-else : 조건문
          			 v-show
          			 v-for : 반복문
          			 v-model : 양방향
          이벤트 처리
          			v-on:click
          			v-on:change
          			v-on:keyup
          			v-on:keydown
          사용자 정의 이벤트 (이벤트 버스 => 실시간 채팅)
          			$emit()
          스프링 연동 (axios)	
          			axios.get('url').then(res=>{})
          				  ---------  ------------  res가 결과값 => {처리}
          				  	요청	
          라우터 (화면 이동) => include
          		rount('/','이동')  												
--%>
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
		<h1>{{message}}</h1>
	</div>
	<script>
		new Vue({
			el:'#app',
			data:{
				message:'Hello VueJS'
			},
			beforeCreate:function(){
				console.log("befroeCreate Call...");
			},created:function(){
				console.log("Created Call...");
			},beforeMount:function(){
				console.log("befroeMount Call...");
			},mounted:function(){
				console.log("mounted Call...");
			},beforeUpdate:function(){
				console.log("befroeUpdate Call...");
			},updated:function(){
				console.log("updated Call...");
			},beforeDestroy:function(){
				console.log("befroeDestroy Call...");
			},destroyed:function(){
				console.log("destroyed Call...");
			}
			
		})
	</script>
</body>
</html>