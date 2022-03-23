package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board/list.do")
	public String boardListData(Model model){
		
		List<BoardVO> list = dao.boardListData();
		
		model.addAttribute("list", list);
		return "board/list";
	}
	@RequestMapping("board/find.do")
	public String board_find(String[] fs,String ss, Model model) {
		Map map=new HashMap();
		map.put("fsArr",fs);
		map.put("ss",ss);
		List<BoardVO> list = dao.boardFindData(map);
		model.addAttribute("list", list);
		return "board/list";
	}
}
