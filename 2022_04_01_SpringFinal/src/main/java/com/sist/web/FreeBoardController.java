package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.FoodVO;
@Controller
@RequestMapping("freeboard/")
public class FreeBoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("list.do")
	public String freeboard_list(String page,Model model) {
		if(page==null)
			page="1"; // 매개변수 => 모든 데이터는 String으로 받을 수 있다.
		int curpage = Integer.parseInt(page);
		int rowSize= 10;
		int start = (curpage*rowSize)-(rowSize-1);
		int end = curpage*rowSize;
		Map<String,Integer> map = new HashMap<>();
		map.put("start",start);
		map.put("end", end);
		List<BoardVO> list = dao.boardListData(map);
		int totalpage= dao.boardTotalPage();
		int count= dao.boardRowCount();
		
		count=count-((curpage-1)*rowSize);
		
		final int BLOCK=3;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("startPage", startPage);
		return "freeboard/list";
	}
	@GetMapping("insert.do")
	public String freeboard_inset() {
		
		return "freeboard/insert";
	}
	
	@PostMapping("insert_ok.do")
	public String freeboard_insert_ok(BoardVO vo) {
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	@GetMapping("detail.do")
	public String freeboard_detail(int no,Model model) {
		BoardVO vo = dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "freeboard/detail";
	}
}
