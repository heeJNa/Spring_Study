package com.sist.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		model.addAttribute("main_jsp","../main/home.jsp");
		return "main/main";
	}
	
	@GetMapping("etc/news/list.do")
	public String news_list(Model model) {
		
		model.addAttribute("main_jsp", "../etc/news/list.jsp");
		return "main/main";
	}
}
