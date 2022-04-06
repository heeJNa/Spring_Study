package com.sist.web;

import java.util.*;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.ReplyDAO;
import com.sist.vo.*;
@Controller
@RequestMapping("reply/")
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@GetMapping("list.do")
	public String replyListData(String page,Model model) {
		if(page==null)
			page="1"; // 매개변수 => 모든 데이터는 String으로 받을 수 있다.
		int curpage = Integer.parseInt(page);
		int rowSize= 10;
		int start = (curpage*rowSize)-(rowSize-1);
		int end = curpage*rowSize;
		Map<String,Integer> map = new HashMap<>();
		map.put("start",start);
		map.put("end", end);
		List<ReplyVO> list = dao.replyListData(map);
		int totalpage= dao.replyTotalPage();
		int count= dao.replyRowCount();
		
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
		return "reply/list"	;
	}
	@GetMapping("insert.do")
	public String replyInsert() {
		return "reply/insert";
	}
	
	@PostMapping("insert_ok.do")
	public String replyInsertOk(ReplyVO vo) {
		dao.replyInsert(vo);
		return "redirect:list.do";
	}
	@GetMapping("detail.do")
	public String replyDetailData(int no,Model model) {
		ReplyVO vo = dao.replyDetailData(no);
		System.out.println(vo.getGroup_id());
		int count=dao.replyCount(vo.getGroup_id());
		
		model.addAttribute("vo", vo);
		model.addAttribute("count", count);
		
		// 단어 추출(형태소 분석) => 꼬꼬마 (카이스트) => korean-text
		KeywordExtractor ke=new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(vo.getContent(), true);
		List<KeywordVO> list = new ArrayList<KeywordVO>();
		for(int i=0;i<kl.size();i++) {
			Keyword kw=kl.get(i);
//			System.out.println(kw.getKey()+","+kw.getCnt());
			String s = kw.getKey().replaceAll("[0-9]", "");
			s= s.substring(0,s.indexOf(":"));
			// 서울:NNG
			if(s.length()>1 && kw.getCnt()>1) {
				System.out.println(s+","+kw.getCnt());
				KeywordVO kvo=new KeywordVO();
				kvo.setWord(s);
				kvo.setCount(kw.getCnt());
				list.add(kvo);
			}
		}
		model.addAttribute("list", list);
		return "reply/detail";
	}
	@GetMapping("reply.do")
	public String replyReply(int no, Model model) {
		model.addAttribute("no", no);
		return "reply/reply";
	}
	@PostMapping("reply_ok.do")
	public String replyReplyInsert(int pno,ReplyVO vo) {
		ReplyVO pvo = dao.replyParentInfoData(pno);
		vo.setGroup_id(pvo.getGroup_id());
		vo.setGroup_step(pvo.getGroup_step()+1);
		vo.setGroup_tab(pvo.getGroup_tab()+1);
		dao.replyReplyInsert(vo);
		return "redirect:list.do";
	}
	@GetMapping("update.do")
	public String replyUpdate(int no,Model model) {
		ReplyVO vo = dao.replyUpdateData(no);
		model.addAttribute("vo",vo);
		return "reply/update";
	}
	@GetMapping("delete.do")
	public String replyDelete(int no,Model model) {
		model.addAttribute("no", no);
		return "reply/delete";
	}
}
