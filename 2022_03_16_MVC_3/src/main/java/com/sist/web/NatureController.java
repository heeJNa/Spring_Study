package com.sist.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

import com.sist.dao.NatureDAO;
import com.sist.vo.*;
@Controller
public class NatureController {
	@Autowired
	private NatureDAO dao;
	
	@GetMapping("nature/list.do")
	public String natureList(String page,Model model) {
		if(page==null)
			page="1";
		Map map= new HashMap();
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		map.put("start", start);
		map.put("end", end);
		List<NatureVO> list = dao.natureListData(map);
		int totalpage = dao.natureTotalPage();
		
		final int BLOCK=5;
		int startpage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage",startpage);
		model.addAttribute("endPage",endpage);
		model.addAttribute("list",list);
		model.addAttribute("BLOCK",BLOCK);
		model.addAttribute("totalpage",totalpage);
		return "nature/list";
	}
	
	@GetMapping("nature/detail.do")
	public String location_detail(int no, Model model) {
		NatureVO vo = dao.natureDetailData(no);
		model.addAttribute("vo", vo);
		return "location/detail";
	}
}
