package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// HandlerMapping이 찾아주는 클래스
/*
 		DispatcherServlet => web.xml에 등록 (HandlertMapping, HandlerAdapter는 자동으로 설정 됨)
 		------------------------
 			= HandlerMappin : Model을 찾아주는 역할
 				=> @Controller, @RestController가 설정되어 있는 클래스만 찾는다
 				=> 오류는 없고 처리가 않된다 (@Controller가 설정되어 있는지 의심)
 			= HandlerAdapter : Model을 찾은 경우에 메소드 호출
 			= ViewResolver : JSP를 찾아서 결과값을 전송하는 역할
 		------------------------ WebApplicationContext
 		
 		DispatcherServlet <====>  WebApplicationContext
 			요청 받기				요청 처리
 			결과값 전송(JSP)		결과값을 DispatcherServlet으로 전송
 */
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class HotelController {
	// 1. 요청을 처리하기 위한 객체 선언 => 스프링으로부터 값을 얻어 온다(객체 주소)
	@Autowired
	private HotelDAO dao;
	// 2. 요청 처리 => 구분=> @RequestMapping (=> 개발자의 요구에 따라 나눠져 있다)
	// @RequestMapping = GET/POST : 동시에 처리가 가능
	// GET => @GetMapping , POST => @PostMapping
	// GET : URL뒤에 데이터를 첨부해서 전송 => ? => <a> , (<form>) , location.href, (ajax), sendRedirect
	// POST : <form>, ajax, axios(자바스크립트에서 처리)
	// 설정할 수 있는 리턴형 => String, void (파일 다운로드)
	@GetMapping("hotel/list.do")
	public String hotel_list(String page,Model model) {
		/*
		 	125page
		 	 1. request, response 거의 사용하지 않는다 (IP, PORT => 보안상의 문제)
		 	 	요청값을 받는 경우에 request.getParameter()
		 	 						 ====================== DispatcherServlet에서 제공
		 	 						 ==> Model의 메소드에 매개변수를 통해서 받는다
		 	 	=> JSP로 전송 : request.setAttribute()
		 	 	   ----------------------------------- Model
		 	 	   					 model.addAttribute() (Model: 결과값 전송 객체)
		 	 	=> 데이터를 받는 경우 => 매개변수 (String, 각 데이터형으로 받을 수 있다)
		 	 2. GET/POST => 어노테이션이 변경될 수 있다	   		
		 	 3. request는 Cookie생성
		 	    response는 파일 다운로드
		 	    session은 dispatcherServlet으로부터 받을 수 있다
		 	    		  ---------------- 내장 객체 9개를 받을 수 있다
		 	    		  HttpServletRequest
		 	    		  HttpServletResponse
		 	    		  HttpSession
		 	    		  ==> Controller클래스의 메소드의 매개변수를 통해서 전송 받을 수 있다.
		 	 4. 받는 데이터형이 틀릴 경우 => 400에러 발생 (매개변수가 틀릴 경우)    
		 	 		  					bad request
		 	 		  					예) ?data=true
		 	 		  						(int data) => 400
		 	 		  						(boolean data)
		 	 		  						(String data)
		 	 		  						===== 잘 모른 경우에는 String(변환)
		 	 		  							=> Wrapper를 이용해서 변환하면 된
		 */
		// 처리
		if(page==null)
			page="1"; //사용자가 페이지를 보내지 않는다 (default 설정)
		int curpage=Integer.parseInt(page);
		Map map = new HashedMap();
		int rowSize=20;
		int start=(rowSize*curpage)-(rowSize-1); //rownum은 1번부터 시작
		int end=rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		//DAO로 부터 데이터를 얻어온다.
		List<HotelVO> list = dao.hotelListData(map);
		// 제목 글자수 조절
		for(HotelVO vo:list) {
			String name= vo.getName();
			if(name.length()>10) {
				name=name.substring(0,10)+"...";
				vo.setName(name);
			}
		}
		// 총페이지
		int totalpage=dao.hotelTotalPage();
		
		// 블록별 처리
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		//list.jsp로 출력을 위한 데이터값 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "hotel/list"; // 확장자를 주면 안된다 (이미 포함되어 있다. JSP명만 설정) 
	}
	@GetMapping("hotel/detail.do")
	public String hotel_detail(int no,Model model) {
		HotelVO vo = dao.hotelDetailData(no);
		model.addAttribute("vo", vo);
		return "hotel/detail";
	}
	
}
