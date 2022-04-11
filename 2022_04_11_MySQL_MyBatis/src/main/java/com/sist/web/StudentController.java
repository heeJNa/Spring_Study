package com.sist.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;
/*
 	 Spring - Boot
 	 1. 설정 (체크박스)
 	 2. application.properties
 	 3. th(html, xml)
 	 4. jpa사용법
 */
@Controller
public class StudentController {
	@Autowired
	private StudentDAO dao;
	
	@GetMapping("std/list.do")
	public String std_list(String page,Model model) {
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		Map map = new HashMap();
		int size=10;
		int start=(curpage*size)-size; // 0번부터 시작한다 (MySql)
		map.put("size",size);
		map.put("start",start);
		List<StudentVO> list =dao.stdListData(map);
		int totalpage=dao.studentTotalPage();
		
		model.addAttribute("list", list);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("curpage", curpage);
		return "std/list";
	}
	@GetMapping("std/insert.do")
	public String std_insert() {
		return "std/insert";
	}
	@PostMapping("std/insert_ok.do")
	   public String std_insert_ok(@ModelAttribute @Valid StudentVO studentVO,Errors result)
	   {
		   if(result.hasErrors())
			   return "std/insert";
		   
		   dao.stdInsert(studentVO);
		   return "redirect:list.do";
	   }
	@GetMapping("std/delete.do")
	public String std_delete(int hakbun) {
		dao.stdDelete(hakbun);
		return "redirect:list.do";
	}
	@GetMapping("std/update.do")
	public String std_update(int hakbun, Model model) {
		StudentVO vo=dao.stdUpdateData(hakbun);
		model.addAttribute("vo", vo);
		return "std/update";
	}
	@PostMapping("std/update_ok.do")
	public String std_upadate_ok(@ModelAttribute  StudentVO studentVO, Errors result) {
		if(result.hasErrors())
			return "sdt/update";
		dao.stdUpdate(studentVO);
		return "redirect:list.do";
	}
}
