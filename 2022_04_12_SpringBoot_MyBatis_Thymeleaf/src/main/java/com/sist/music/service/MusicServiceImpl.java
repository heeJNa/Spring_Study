package com.sist.music.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.music.dao.MusicMapper;
import com.sist.music.vo.MusicVO;

@Service
public class MusicServiceImpl implements MusicService{
	@Autowired
	private MusicMapper mapper;

	@Override
	public List<MusicVO> musicListData(Map map) {
		return mapper.musicListData(map);
	}

	@Override
	public int musicTotalPage(int cno) {
		return mapper.musicTotalPage(cno);
	}
	
	
}
