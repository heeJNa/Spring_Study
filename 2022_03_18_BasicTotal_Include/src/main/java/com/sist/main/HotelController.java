package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.HotelDAO;
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
	public String seoul_hotel_list(String page,Model model) {
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
		return "main/main"; // include
	}

}
