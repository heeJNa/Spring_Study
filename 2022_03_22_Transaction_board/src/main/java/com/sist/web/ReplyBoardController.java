package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.ReplyBoardDAO;
import com.sist.vo.ReplyBoardVO;
import com.sist.vo.ReplyFindVO;
@Controller // 사용자 요청을 받아서 처리 => 결과값을 전송
public class ReplyBoardController {
	@Autowired
	private ReplyBoardDAO dao;
	
	@GetMapping("board/list.do")
	public String board_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=10;
		int start = (rowSize*curpage)-(rowSize-1);
		int end = rowSize*curpage;
		Map map = new HashMap();
		map.put("start",start);
		map.put("end",end);
		List<ReplyBoardVO> list = dao.replyBoardListData(map);
		int totalpage=dao.replyBoardTotalPage();
		int count = dao.replyBoardCount();
		count=count-((rowSize*curpage)-rowSize);
		String today= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("count",count);
		model.addAttribute("today",today);
		
		return "board/list";
	}
	@GetMapping("board/insert.do")
	public String board_insert() {
		return "board/insert"; // 화면만 보여준다
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(ReplyBoardVO vo) {
		// vo단위로 값을 받는다 => 커맨드 객체(vo, [], list)
		dao.replyBoardInsert(vo);
		return "redirect:list.do";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(Model model,int no) {
		// 처리
		ReplyBoardVO vo = dao.replyboardDetailData(no);
		// 결과값 전송
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	@GetMapping("board/update.do")
	public String board_update(int no,Model model) {
		
		ReplyBoardVO vo = dao.replyboardUpadteData(no);
		
		model.addAttribute("vo", vo);
		return "board/update";
	}
	@GetMapping("board/reply.do")
	public String board_reply(int no,Model model) {
		
		model.addAttribute("no", no);
		return "board/reply";
	}
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int pno,ReplyBoardVO vo) {
		// DAO연동
		dao.replyBoardReplyInsert(pno, vo);
		return "redirect:list.do";
	}
	@GetMapping("board/delete.do")
	public String board_delete(int no,Model model) {
		
		model.addAttribute("no", no);
		return "board/delete";
	}
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(int no,String pwd,Model model) {
		// DataBase연결
		boolean bCheck=dao.replyBoardDelete(no, pwd);
		model.addAttribute("bCheck", bCheck);
		return "board/delete_ok";
	}
	@PostMapping("board/find.do")
	public String board_find(String[] fs,String ss,Model model) {
		// DAO연동 데이터 읽기
		Map map=new HashMap();
		map.put("fsArr",fs);
		map.put("ss",ss);
		List<ReplyBoardVO> list = dao.replyBoardFindData(map);
		model.addAttribute("list", list);
		return "board/find";
	}
}
