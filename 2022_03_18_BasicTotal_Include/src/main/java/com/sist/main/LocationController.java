package com.sist.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocationController {

	@GetMapping("seoul/location/list.do")
	public String seoul_location_list(String page,Model model) {
		if(page==null)
			page="1";
		model.addAttribute("main_jsp", "../seoul/location/list.jsp");
		return "main/main";
	}
}
