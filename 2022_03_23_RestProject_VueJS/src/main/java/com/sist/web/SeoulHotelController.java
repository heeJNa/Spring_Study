package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.SeoulHotelDAO;

@Controller
public class SeoulHotelController {
	
	
	@GetMapping("vuejs/vue4.do")
	public String vuejs_vue3() {
		
		return "vuejs/vue4";
	}
}
