package com.sist.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.HotelDAO;
import com.sist.vo.HotelVO;
/*
 * 	 @Autowired
 	 @Target(value={ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
 	 ANNOTATION_TYPE,
 	 CONSTRUCTOR,
 	 FIELD,
 	 METHOD,
 	 PARAMETER
 	 
 	 public class ClsName{
 	 	@Autowired : FIELD
 	 	private BoardDAO dao;
 	 	
 	 	@Autowired : CONSTRUCTOR
 	 	public ClsName(BoardDAO dao){
 	 		// 어노테이션은 지역변수에서 사용 불가능
 	 		this.dao = dao;
 	 	}
 	 	@Autowired : METHOD
 	 	public void setBoardDAO(BoardDAO dao){
 	 		
 	 	}
 	 	
 	 	public void disply(@Autowired BoardDAO dao){
 	 					   ----------  
 	 					   PARAMETER
 	 	}
 	 	
 	 }
 	 
 	 @Controller
 	 Target(value={TYPE})
 	 TYPE : 클래스 위에만 사용이 가능(클래스 구분)
 	 
 	 @GetMapping
 	 Target(value={METHOD})
 	 METHOD : 메소드 위에만 사용이 가능(메소드 구분)
 */
@Controller
public class HotelController {
	@Autowired
	private HotelDAO dao; // 멤버 => 모든 메소드에서 사용
	
	@GetMapping("seoul/hotel/list.do")
	public String seoul_hotel_list(HttpServletRequest request,String page,Model model) {
		// git에서 복사하기
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize = 20;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=rowSize*curpage;
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		// DB연동
		List<HotelVO> list = dao.hotelListData(map);
		for(HotelVO vo:list) {
			String name=vo.getName();
			if(name.length()>12) {
				name=name.substring(0, 13)+"...";
			}
			vo.setName(name);
		}
		int totalpage= dao.hotelTotalPage();
		// 블록처리
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("main_jsp", "../seoul/hotel/list.jsp");
		//					출력
		// 쿠키 출력 
		  Cookie[] cookies=request.getCookies();
		  List<HotelVO> cList=new ArrayList<HotelVO>();
		  if(cookies!=null) // 쿠키가 존재할때 
		  {
			  for(int i=cookies.length-1;i>=0;i--)//최신 등록된 데이터부터 읽기 시작
			  {
				  // 키 => getName() 
				  // 값 => getValue()
				  if(cookies[i].getName().startsWith("h"))
				  {
					  String no=cookies[i].getValue();
					  HotelVO vo=dao.hotelDetailData(Integer.parseInt(no));
					  cList.add(vo);
				  }
			  }
			  model.addAttribute("cList", cList);
		  }
		return "main/main"; // include
	}
	
	@GetMapping("seoul/hotel/detail_before.do")
	  public String seoul_hotel_detail_before(int no,RedirectAttributes ra,
			  HttpServletResponse response)
	  {
		  // Cookie이용 => 내장 객체가 아니다 , response로 클라이언트 로 전송 
		  // sendRedirect를 이용해서 화면 변경시 데이터 전송 => RedirectAttributes
		  // 매개변수는 순서가 존재하지 않는다 (원하는 데이터형으로 DispatcherServlet으로 받을 수 있다)
		  /*
		   *   매개변수를 이용해서 객체단위 
		   *   =====================
		   *   HttpSevletRequest : Cookie읽기  
		   *   HttpServletResponse : Cookie전송 , 다운로드시에 주로 사용 
		   *   HttpSession : 로그인 , 로그아웃 ..
		   *   커맨드 객체 : ~VO : insert,update시에 단위 VO단위로 받을 수 있다
		   *   일반 데이터형 => int , double , boolean , String 
		   *                <a => ?> , axios , ajax
		   *   RedirectAttributes : 전송객체 (sendRedirect())
		   *                        GET방식 => detail.do?no=1
		   *                                           ------ 별도로 생성이 가능 
		   *   Model : 전송 객체 (forward()) : 데이터를 전송 (request.setAttribute()대신 사용)
		   *   
		   *   response를 이용해서 브라우저에 값을 전송(한개의 기능에서 메소드에서 
		   *            HTML/Cookie를 동시에 전송 할 수 없다) 
		   *   -------- 각 JSP에서 한번만 사용이 가능 
		   *            = HTML (JSP를 번역한 HTML)
		   *            = Cookie 
		   *   Cookie사용 
		   *   1) Cookie 생성 
		   *      Cookie cookie=new Cookie(키 , 값) => map방식 
		   *                          키는 중복이 불가능(덮어쓴다)
		   *                          값 : String만 저장이 가능 
		   *   2) 저장위치 설정 
		   *      setPath("/") 
		   *   3) 저장기간 설정 
		   *      setMaxAge(초) => 60*60*24 ==> 0이면 삭제
		   *   4) 클라이언트로 전송 
		   *      쿠키는 클라이언트에 저장 
		   *      => 방문기록 / 자동 로그인 (보안)
		   */
		  // Cookie(String name, String value)
		  Cookie cookie=new Cookie("h"+no, String.valueOf(no));
		  //  키는 중복이 있으면 안된다    -------- no(PK)
		  //  path설정 
		  cookie.setPath("/");
		  //  기간설정
		  cookie.setMaxAge(60*60*24);
		  //  클라이언트로 전송 
		  response.addCookie(cookie);
		  ra.addAttribute("no", no);
		  // redirect:/main/seoul/hotel/detail.do?no=1
		  return "redirect:http://localhost:8080/main/seoul/hotel/detail.do";
	  }
	  @GetMapping("seoul/hotel/detail.do")
	  public String seoul_hotel_detail(int no,Model model)
	  {
		  // 오라클로부터 데이터 읽기 
		  HotelVO vo=dao.hotelDetailData(no);
		  model.addAttribute("vo", vo);
		  // vo를 받아서 출력하는 JSP가 존재 
		  model.addAttribute("main_jsp", "../seoul/hotel/detail.jsp");
		  // main에서 incude를 사용해서 화면을 조립 
		  return "main/main";
	  }
	
}
