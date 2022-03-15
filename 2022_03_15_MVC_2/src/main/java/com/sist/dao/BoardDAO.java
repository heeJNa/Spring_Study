package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	
	public BoardVO boardDetailData(int no){
		mapper.hitIncrement(no);
		return mapper.boardDetailData(no);
	}
	
	public int boardTotalPage() {
		return mapper.boardTotalPage();
	}
	
	public BoardVO bvoardUpdateData(int no) {
		return mapper.boardDetailData(no); // 재사용
	}
	
	public boolean board_update(BoardVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			mapper.boardUpdate(vo);
			bCheck=true;
		}else {
			bCheck=false;
		}
		return bCheck;
	}
	
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.boardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			mapper.boardDelete(no);
			bCheck=true;
		}else {
			bCheck=false;
		}
		return bCheck;
	}
}
