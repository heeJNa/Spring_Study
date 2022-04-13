package com.sist.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.music.dao.MusicDAO;
import com.sist.music.entity.MusicEntity;
// SpringFramework => jsp > MyBatis (JSTL,EL)
// Spring-Boot 	   => html => JPA
//					  thymeleaf (th, EL)
// Spring-Boot(서버) , Front ==> 가장 최근
// ---------------- AI
@Controller
public class MusicController {
	// DAO
	@Autowired
	private MusicDAO dao;
	
	@GetMapping("/top200")
	public String musicListData(String page,String cno,Model model) {
		if(page==null)
			page="1";
		if(cno==null)
			cno="1";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MusicEntity> list = dao.musicTop200ListData(Integer.parseInt(cno),start);
		for(MusicEntity m:list) {
			String title = m.getTitle();
			if(title.length()>25) {
				title= title.substring(0, 20)+"...";
			}
			m.setTitle(title);
		}
		int totalpage=dao.musicTotalPage(Integer.parseInt(cno));
		
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
		// include 파일에 첨부되는 파일 지정
		model.addAttribute("main_jsp", "../music/list.jsp");
		return "main/main";
	}
	@GetMapping("/genre_music")
	public String genre_music(String page, String cno,Model model) {
		if(page==null)
			page="1";
		if(cno==null)
			cno="2";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MusicEntity> list = dao.musicTop200ListData(Integer.parseInt(cno),start);
		for(MusicEntity m:list) {
			String title = m.getTitle();
			if(title.length()>25) {
				title= title.substring(0, 20)+"...";
			}
			m.setTitle(title);
		}
		int totalpage=dao.musicTotalPage(Integer.parseInt(cno));
		
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
		model.addAttribute("cno", cno);
		// include 파일에 첨부되는 파일 지정
		model.addAttribute("main_jsp", "../music/genre.jsp");
		return "main/main";
	}
	@RequestMapping("/music_find")
	public String music_find(String title,String page,Model model) {
		if(title==null)
			title="비";
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MusicEntity> list = dao.musicFindData(title,start);
		for(MusicEntity m:list) {
			String title1 = m.getTitle();
			if(title1.length()>25) {
				title1= title1.substring(0, 20)+"...";
			}
			m.setTitle(title1);
		}
		int totalpage=dao.musicFindTotalPage(title);
		
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
		model.addAttribute("title", title);
		model.addAttribute("main_jsp", "../music/find.jsp");
		return "main/main";
	}
}
