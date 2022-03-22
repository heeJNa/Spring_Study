package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class ReplyBoardDAO {
	@Autowired
	private ReplyBoardMapper mapper;
	
	public List<ReplyBoardVO> replyBoardListData(Map map){
		return mapper.replyBoardListData(map);
	}
	public int replyBoardTotalPage() {
		return mapper.replyBoardTotalPage();
	}
	
	public int replyBoardCount() {
		return mapper.replyBoardCount();
	}
	
	public void replyBoardInsert(ReplyBoardVO vo) {
		mapper.replyBoardInsert(vo);
	}
	
	public ReplyBoardVO replyboardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.replyboardDetailData(no);
	}
	public ReplyBoardVO replyboardUpadteData(int no) {
		return mapper.replyboardDetailData(no);
	}
	
	
	public boolean replyBoardUpdate(ReplyBoardVO vo) {
		boolean bCheck=false;
		String db_pwd = mapper.replyBoardGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			mapper.replyBoardUpdate(vo);
			bCheck=true;
		}else {
			bCheck=false;
		}
		return bCheck;
	}
}
