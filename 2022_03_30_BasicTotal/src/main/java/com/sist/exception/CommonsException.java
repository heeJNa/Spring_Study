package com.sist.exception;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 		자바
 		1. instance vs statice
 		2. 캡슐화 vs 은닉화
 		3. 객체지향 프로그램의 객체란?
 		4. 오버로딩 vs 오버라이딩
 		5. 추상클래스와 인터페이스
 		6. 오버라이딩 방법 (상속, 익명의 클래스)
 		7. 상속 (is-a) VS 포함 (has-a)
 		8. => 객체지향프로그램 (OOP)
 		9. 예외처리 목적, 방법
 		10. 컬렉션 클래스 (List,Set,Map)
 		11. 제네릭스 타입 <>
 		12. IO
 		13. Thread => 동기화/비동기화
 		기타 
 		1. 
 		
 		
 */
@ControllerAdvice("com.sist.web") // 공통으로 예외처리 적용
// Controller에서 에러(예외)발생 => 모아서 한번에 처리 => 비장성 종료가 없이 정상수행이 가능하게 된다.
// 사전에 에러 방지 프로그램을 작성
// 예외처리의 목적, 처리방법 (예외복구:try~catch,예외회피(throws))
// 클래스는 기능별로 나눠서 처리 (한개의 기능 => 컴포넌트(부품))
// 컴포넌트를 관리 => 컨테이너 (스프링)
/*
 		부품 => 부품여러개를 묶어서(조립) 기능이 수행 => @Controller
 		
 		부품 
 			=> DAO (Mapper,VO)
 			=> Controller
 				요청 => @GetMapping, @PostMapping, RequestMapping
 				DAO 데이터 처리 dao.recipeData()
 				결과값 전송 model.addAttribute()
 			
 			JSP(요청) Model (@Controller)
 			---------
 			<a>
 			<form>
 			location.href
 			ajax
 			axios
 */
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeHandler(RuntimeException ex) {
		System.out.println("===== RuntimeException 에러 발생 ======");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(SQLException.class)
	public void sqlHandler(SQLException ex) {
		System.out.println("===== SQLException 에러 발생 ======");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(IOException.class)
	public void ioHandler(IOException ex) {
		System.out.println("===== IOException 에러 발생 ======");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(ClassNotFoundException.class)
	public void classNotFoundHandler(ClassNotFoundException ex) {
		System.out.println("===== ClassNotFoundException 에러 발생 ======");
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception ex) {
		System.out.println("===== Exception 에러 발생 ======");
		System.out.println(ex.getMessage());
	}
	/*
	 * 	1. DispatcherServlet => 등록 (web.xml)
	 * 		=> HandlerMapping, HandlerAdapter
	 *  2. ViewResolver => application-context.xml
	 *  ------------------------------------------
	 *  3. @Controller, @RestController
	 *  4. MyBatis => CRUD, 동적쿼리
	 *  5. JSP출력
	 *  ------------------------------------------
	 *  기타 : Tiles
	 *  ------------------------------------------ Basic
	 *  Front: JavaScript => function
	 *  		Jquery(Ajax), VueJS, ReactJS
	 *  ------------------------------------------ 파이썬 /AI
	 *  											자기개발
	 */
}
