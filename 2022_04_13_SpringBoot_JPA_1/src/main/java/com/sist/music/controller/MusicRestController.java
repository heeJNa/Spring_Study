package com.sist.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.music.dao.*;
import com.sist.music.entity.*;
@RestController
public class MusicRestController {
	@Autowired
	private MusicDAO dao;
	
	@PostMapping("/music_ajax")
	// jackson-bind => List,객체(VO) => 자동으로 JSON매핑
	// boot에서는 vo나 entity 를 리턴하면 자동으로 json으로 변경해줌
	public MusicEntity music_ajax(int no) {
		MusicEntity me = dao.findByNo(no);
		return me;
	}
}
