package com.sist.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;
@Controller
public class EmpController {
	@Autowired
	private EmpDAO dao;
	
	@RequestMapping("emp/list.do")
	public String emp_list(Model model) {
		
		List<String> list = dao.empGetNameData();
		model.addAttribute("list", list);
		return "emp/list";
	}
	@RequestMapping("emp/find.do")
	public String emp_find(String[] names,Model model) {
		System.out.println(Arrays.deepToString(names));
		Map map=new HashMap();
		map.put("names",names);
		System.out.println(Arrays.deepToString((Object[]) map.get("names")));
		List<EmpVO> list = dao.empFindData(map);
		
		model.addAttribute("list", list);
		return "emp/find";
	}
	
}
