package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value = "main/{id}/{pwd}")
	public String main_main(@PathVariable("id") String id, @PathVariable("pwd") String pwd,Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("pwd", pwd);
		return "main/main";
	}
}
