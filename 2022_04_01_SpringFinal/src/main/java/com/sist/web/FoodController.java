package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
// HandlerMapping이 찾기가 가능 => @Controller, @RestController
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import java.util.*;
@Controller
public class FoodController {
	// 필요한 객체를 스프링으로부터 주입 요청
	@Autowired
	private FoodDAO dao;
	
	// 기능별 처리=> if역할 (if문 한개)
	// 어노테이션을 조건문이라고 한다.
	// ==> URI주소
	@GetMapping("food/list.do") // 사용자가 food/list => 맛집 목록을 요청 했다면
	public String foodListData(String page, Model model) {
		// 웹에서 나오는 모든 데이터는 String
		if(page==null)
			page="1"; // 매개변수 => 모든 데이터는 String으로 받을 수 있다.
		int curpage = Integer.parseInt(page);
		int rowSize= 12;
		int start = (curpage*rowSize)-(rowSize-1);
		int end = curpage*rowSize;
		Map<String,Integer> map = new HashMap<>();
		map.put("start",start);
		map.put("end", end);
		List<FoodVO> fList = dao.foodListData(map);
		for(FoodVO vo:fList) {
			String poster=vo.getPoster();
			poster = poster.substring(0,poster.indexOf("^"));
			vo.setPoster(poster);
		}
		int totalpage= dao.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("fList", fList);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("startPage", startPage);
		return "food/list";
	}
	@GetMapping("food/detail.do")
	public String foodDetailData(int no, Model model) {
		FoodVO vo = dao.foodDetailData(no);
		String addr=vo.getAddress();
		String addr1=addr.substring(0, addr.lastIndexOf("지"));
		String addr2=addr.substring(addr.lastIndexOf("지"));
		model.addAttribute("addr1", addr1.trim());
		model.addAttribute("addr2", addr2.trim());
		model.addAttribute("vo", vo);
		return "food/detail";
	}
	@GetMapping("food/food_find.do")
	public String food_find(String gu,Model model) {
		return "food/food_find";
	}
	
	@GetMapping("food/food_find_result.do")
	public String find_result(int gu,Model model) {
		String[] guList_1 = { "전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
			    "은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
			    "성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
			    "강동구" };
		List<FoodVO> list =dao.foodFindData(guList_1[gu]);
		model.addAttribute("list", list);
		model.addAttribute("gu", guList_1[gu]);
		return "food/food_find_result/ajax";
	}
	@GetMapping("food/food_recommand.do")
	public String food_recommand() {
		return "food/food_recommand";
	}
}
