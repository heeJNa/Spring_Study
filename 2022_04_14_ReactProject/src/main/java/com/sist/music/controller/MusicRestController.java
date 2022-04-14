package com.sist.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.music.dao.MusicDAO;
import com.sist.music.entity.MusicEntity;
import com.sist.music.entity.PageVO;

@RestController
@CrossOrigin("http://localhost:3000")
// React => port = 3000, Spring 8080
public class MusicRestController {
	@Autowired
	private MusicDAO dao;
	
	@GetMapping("/music/list")
	public List<MusicEntity> music_list(String page,String cno,Model model) {
		if(page==null)
			page="1";
		
		int curpage = Integer.parseInt(page);
		int rowSize=20;
		int start = (rowSize*curpage)-rowSize; // oracle (1번부터 시작), mysql(0번부터 시작)
		List<MusicEntity> list = dao.musicListData(Integer.parseInt(cno),start);
		for(MusicEntity m:list) {
			String title = m.getTitle();
			if(title.length()>25) {
				title= title.substring(0, 20)+"...";
			}
			m.setTitle(title);
		}
		
		// 자동으로 JSON으로 변환해서 return 된다.
		return list;
	}
	@GetMapping("/music/totalpage")
	public PageVO music_totalPage(String page,String cno) {
		PageVO vo = new PageVO();
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int totalpage = dao.musicTop200TotalPage(Integer.parseInt(cno));
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		vo.setCurpage(curpage);
		vo.setEndPage(endPage);
		vo.setStartPage(startPage);
		vo.setTotalpage(totalpage);
		
		return vo;
	}
}


