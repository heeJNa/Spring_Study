package com.sist.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.music.dao.MovieDAO;
import com.sist.music.entity.MovieEntity;
// JPA => CURD 
@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;
	
	
	@GetMapping("/movie_list")
	public String movie_main(String page,String cno,Model model){
		if(page==null)
			page="1";
		if(cno==null)
			cno="1";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MovieEntity> list = dao.movieListData(Integer.parseInt(cno),start);
		
		int totalpage=dao.movieTotalPage(Integer.parseInt(cno));
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("main_jsp", "../movie/list.jsp");
		return "main/main";
	}
	
	@GetMapping("/movie_detail")
	public String movie_detail(int mno,Model model){
		MovieEntity vo = dao.findByMno(mno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../movie/detail.jsp");
		return "main/main";
	}
	
	@PostMapping("/movie_find")
	public String movie_find(String page,String title,Model model){
		if(page==null)
			page="1";
		if(title==null)
			title="여름";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MovieEntity> list = dao.movieFindData(title,start);
		
		int totalpage=dao.movieFindTotalPage(title);
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("main_jsp", "../movie/find.jsp");
		return "main/main";
	}
}
