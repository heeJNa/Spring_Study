package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(){
		return mapper.boardListData();
	}
	
	public List<BoardVO> boardFindData(Map map){
		return mapper.boardFindData(map);
	}
}
