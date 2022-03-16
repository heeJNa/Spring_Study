package com.sist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.manager.NewManager;
import com.sist.vo.NewsVO;

@Controller
public class MainController {
	@Autowired // 스프링에서 생성된 객체주소를 얻어 온다(자동 처리)
	private NewManager mgr;
	
	@RequestMapping("news/find.do")
	public String news_find(String ss,Model model) {
		if(ss==null)
			ss="영화";
		System.out.println("contorller");
		List<NewsVO> list= mgr.newsFindData(ss);
		model.addAttribute("ss", ss);
		model.addAttribute("list", list);
		return "news/find";
	}
}
