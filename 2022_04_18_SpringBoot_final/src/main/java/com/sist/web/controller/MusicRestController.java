package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MusicDAO;
import com.sist.web.entity.MusicEntity;

@RestController
public class MusicRestController {
	@Autowired
	private MusicDAO dao;
	
	 @PostMapping("/music/detail")
	   public MusicEntity music_detail(int no){
		   MusicEntity vo=dao.musicDetailData(no);
		   return vo;
	   }
}
