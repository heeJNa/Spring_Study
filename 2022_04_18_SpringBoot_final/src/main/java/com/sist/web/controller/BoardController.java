package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.BoardEntity;

/*
 	 데이터베이스만 관리 ==> Repository
 */
@Controller
public class BoardController {
	@Autowired
	// 자동주입 => 특정 객체로 변환 @Qualifier("id명")
	private BoardDAO dao;
	
	@GetMapping("/board/list")
	public String board_list(String page, Model model) {
		
		if(page ==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start = rowSize*curpage-rowSize;  // rownum=1 , limit = 0
		List<BoardEntity> list = dao.boardListData(start);
		int totalpage= dao.boardTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_content", "board/list");
		return "main";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model) {
		
		model.addAttribute("main_content", "board/insert");
		return "main";
	}
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(BoardEntity vo) {
		
		dao.save(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String board_detail(int no,Model model) {
		BoardEntity vo = dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_content", "board/detail");
		return "main";
	}
	@GetMapping("/board/update")
	public String board_update(int no,Model model) {
		BoardEntity vo = dao.findByNo(no);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_content", "board/update");
		return "main";
	}
	@PostMapping("/board/update_ok")
	public String board_update_ok(BoardEntity vo) {
		dao.save(vo);
		// regdate가 안들어감 추후 수정해야할 부분
		return "redirect:/board/detail?no="+vo.getNo();
	}
	@GetMapping("/board/delete")
	public String board_delete(int no) {
		BoardEntity vo = dao.findByNo(no);
		dao.delete(vo);
		return "redirect:/board/list";
	}
}
