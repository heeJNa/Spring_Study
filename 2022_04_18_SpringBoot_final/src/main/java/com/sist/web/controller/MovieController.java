package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.MovieDAO;
import com.sist.web.entity.MovieEntity;
@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;
	
	@GetMapping("/movie/list")
	public String movie_list(String page,String cno,Model model,HttpServletRequest req) {
		if(page ==null)
			page="1";
		if(cno==null)
			cno="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start = rowSize*curpage-rowSize;  // rownum=1 , limit = 0
		List<MovieEntity> list = dao.movieListData(Integer.parseInt(cno), start);
		int totalpage= dao.movieTotalPage(Integer.parseInt(cno));
		
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		Cookie[] cookies=req.getCookies();
		List<MovieEntity> cList=new ArrayList<>();
		for(int i =cookies.length-1;i>=0;i--) {
			if(cookies[i].getName().startsWith("m")) {
				cookies[i].setPath("/");
				String no=cookies[i].getValue();
				MovieEntity me=dao.findByMno(Integer.parseInt(no));
				cList.add(me);
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("cList", cList);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("title", Integer.parseInt(cno)==1?"현재상영영화":"개봉예정영화");
		model.addAttribute("cno", cno);
		model.addAttribute("main_content", "movie/list");
		return "main";
	}
	@GetMapping("/movie/detail_before")
	public String movie_detail(int mno,HttpServletResponse res,RedirectAttributes ra) {
		Cookie cookie= new Cookie("m"+mno,String.valueOf(mno));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		res.addCookie(cookie);
		
		ra.addAttribute("mno", mno);
		return "redirect:/movie/detail";
	}
	@GetMapping("/movie/detail")
	public String movie_detail(int mno,Model model) {
		MovieEntity vo = dao.findByMno(mno);
		
		model.addAttribute("vo", vo);
		model.addAttribute("main_content", "movie/detail");
		return "main";
	}
}
