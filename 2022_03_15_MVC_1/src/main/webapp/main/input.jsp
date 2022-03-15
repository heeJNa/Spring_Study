<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Controller 등록 -->
	<!-- 
			public class DispatcherServlet extends HttpServlet{
				톰캣에 의해서 호출되는 메소드	
				pirvate WebApplicationContext wd;
				public void init(ServletConfig config){
					// web.xml을 읽어서 셋팅
					String path=config.getInitParameter("contextConfigLocation");
						=> path="/WEB-INF/config/application-*.xml"
					wc= new WebApplicationContext(path);
					new ClassPathXMLApplicationContext("app.xml"); 과 동일
					wc=> XML을 파싱하고 클래스를 관리	
					
					=> 경로명 없이 XML를 저장이 가능
						<servlet-name>-servlet.xml
						==============
						appServlet-servlet.xml (default 파일)
				}	
				public void service(HttpServletRequest request,
					HttpServletResponse response){
					1) 해당 Model(Controller)
					2) @RequestMapping을 찾느다
					3) 메소드 호출
					4) 리턴값을 받아서
					5) ViewResolver에 전송
					6) 해당 jsp를 찾아서 request를 전송
					7) => jsp에서 request에 담긴 데이터 출력
						  ---------------- EL/JSTL
				}
				public void destroy(){
					// 새로고침, 화면이동 => 메모리에서 해제
				}			
			}
	 -->
	 <!-- XML이 크면 속도가 느려진다 (XML은 분산처리:기능별 나눠서 처리) 
				기본
						*** application-context.xml : 사용자 정의 클래스 등록, ViewResolver설정
						*** application-datasource.xml : DataSource설정, MyBatis설정
						*** application-websocket.xml : 실시간 처리
						==========================================================
						application-security.xml : 보안(Spring보안 보다는 블록체인)
						application-bigdata.xml : 분석(AI관련)
						==========================================================
						*** application-tiles.xml : include대신 사용
						*** application-validation.xml : 유효성 검사(hibernate)
						*** application-exception.xml : 예외처리(공통), intercept
				View : JSP => Ajax(x), VueJS  (vuex)  JSON
											   Front-MVC
										------------------
						Spring-Boot + ReactJS (Redux:MVC) = Mobx(배민), Saga (framework)
						=> MyBatis , JPA (SQL문장 없이 처리가 가능)
									 --- (MySQL)		
						----------------------------- Restful(JSON)
				Spring-Boot(서버) <=========> Vue(vuex), react(redux)
									JSON(스웨거)
				STS4.0 => spring-boot가능								
			 -->
			 <!-- 119page -->
			 <!-- 
				DispatcherServlet : Controller (Front-Controller)
					= 요청을 받는다 (Model클래스 연결)
						|
				Controller (Model) => 위임을 받아서 요청 처리 => 결과값 전송
					= 프로그래머 코딩 영역
					=> 다른 클래스와 구분 @Controller
						기능별 구분 (메소드) @RequestMapping
			 -->
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
	width:450px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>개인정보</h1>
		<div class="row">
		<form method=post action="../main/output.do">
			<table class="table">
				<tr>
					<td width=20% class="text-right">이름</td>
					<td width=80%>
						<input type="text" name=name size=20 class="input-sm">
					</td>
				</tr>
				<tr>
					<td width=20% class="text-right">성별</td>
					<td width=80%>
						<input type="radio" name=sex value="남자" checked>남자
						<input type="radio" name=sex value="여자" >여자
					</td>
				</tr>
				<tr>
					<td width=20% class="text-right">주소</td>
					<td width=80%>
						<input type="text" name=addr size=35 class="input-sm">
					</td>
				</tr>
				<tr>
					<td width=20% class="text-right">전화</td>
					<td width=80%>
						<input type="text" name=tel size=20 class="input-sm">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<button class="btn btn-sm btn-danger">전송</button>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>