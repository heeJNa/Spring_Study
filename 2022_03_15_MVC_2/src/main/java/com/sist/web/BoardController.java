package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
@Controller
@RequestMapping("board/")
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("list.do")
	// 모든 데이터느 필요에따라 String으로 받을 수 있다.
	public String board_list(String page, Model model) {
		// page처럼 처음에 null이 들어오는 경우는 String으로 받아야한다
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		Map  map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list = dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		
		model.addAttribute("totalpage", totalpage);
		// request.setAttribute()
		model.addAttribute("list", list);
		model.addAttribute("curpage",curpage);
		return "board/list"; //forward() => request를 전송
	}
	@GetMapping("insert.do")
	public String board_insert() {
		return "board/insert";
	}
	@PostMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		// redirect => request전송이 가능 => RedirectAttributes
		// PathValiable
		dao.boardInsert(vo);
		return "redirect:list.do"; // sendRedirect() => request전송이 없는 경우
	}
	
	//detail.do?no=${vo.no}
	@GetMapping("detail.do")
	// 데이터형을 맞게 설정
	public String borard_detail(int no,Model model) {
		BoardVO vo =dao.boardDetailData(no);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	@GetMapping("update.do")
	public String board_update(int no,Model model) {
		// model => request대신 데이터 전송하는 객체
		BoardVO vo = dao.bvoardUpdateData(no);
		model.addAttribute("vo", vo);
		// jsp.forward(request,response)
		return "board/update";
	}
	@GetMapping("delete.do")
	public String board_delete(int no, Model model) {
		// delete.do를 요청 => delete.jsp (no값을 포함)
		model.addAttribute("no",no);
		return "board/delete";
	}
}
