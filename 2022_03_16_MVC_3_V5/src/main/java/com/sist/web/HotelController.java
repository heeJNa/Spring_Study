package com.sist.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.HotelDAO;
import com.sist.vo.HotelVO;

@Controller
public class HotelController {
	@Autowired
	private HotelDAO dao;
	
	@GetMapping("hotel/list.do")
	public String hotel_list(String page,Model model) {
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
