package com.sist.react.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.react.service.ReactService;
import com.sist.react.vo.SeoulVO;

@RestController
@CrossOrigin("http://localhost:3000")
public class SeoulController {
		@Autowired
		private ReactService service;
		
		@GetMapping("/seoul/location")
		public List<SeoulVO> seoulLocationList(String page) {
			if(page==null)
				page="1";
			 int curpage=Integer.parseInt(page);
			  Map map=new HashMap();
			  int rowSize=12;
			  int start=(rowSize*curpage)-(rowSize-1);
			  int end=rowSize*curpage;
			  
			  map.put("start", start);
			  map.put("end", end);
			List<SeoulVO> list = service.seoulLocationData(map);
			int totalpage=service.seoulLocationTotalPage();
			list.get(0).setCurpage(curpage);
			list.get(0).setTotalpage(totalpage);
			return list;
		}
}
