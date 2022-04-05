package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class BoardDAO {
	// 스프링으로부터 필요한 객체주소를 주입 요청 => @Autowired, @Resounce
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardVO> boardListData(Map map){
		return mapper.boardListData(map);
	}
	
	public int boardRowCount() {
		return mapper.boardRowCount();
	}
	
	public int boardTotalPage() {
		return (int)(Math.ceil(mapper.boardRowCount()/10.0));
	}
	
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}
	public BoardVO boardDetailData(int no) {
		mapper.hitincrement(no);
		return mapper.boardDetailData(no);
	}
	
	public BoardVO boardUpdateData(int no)
    {
    	return mapper.boardDetailData(no);
    }
    
    // 수정 
    public boolean boardUpdate(BoardVO vo)
    {
    	boolean bCheck=false;
    	String db_pwd=mapper.boardGetPassword(vo.getNo());
    	if(db_pwd.equals(vo.getPwd()))
    	{
    		bCheck=true;
    		mapper.boardUpdate(vo);
    	}
    	return bCheck;
    }
    // 삭제 
    public boolean boardDelete(int no,String pwd)
    {
    	boolean bCheck=false;
    	String db_pwd=mapper.boardGetPassword(no);
    	if(db_pwd.equals(pwd))
    	{
    		bCheck=true;
    		mapper.boardDelete(no);
    	}
    	return bCheck;
    }
}
