package com.sist.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.music.service.MusicService;
import com.sist.music.vo.MusicVO;
@Controller
public class MusicController {
	@Autowired
	private MusicService service;
	
	@GetMapping("/")
	public String musicListData(String page,Model model) {
		if(page==null)
			page="1";
		Map map = new HashMap();
		int curpage=Integer.parseInt(page);
		map.put("cno",1);
		int size=20;
		int start= (size*curpage)-size;
		map.put("start", start);
		map.put("size",size);
		
		List<MusicVO> list = service.musicListData(map);
		int totalpage=service.musicTotalPage(1);
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		return "main";
	}
}
