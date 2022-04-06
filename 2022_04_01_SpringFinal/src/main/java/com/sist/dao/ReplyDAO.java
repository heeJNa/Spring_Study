package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class ReplyDAO {
	@Autowired ReplyMapper mapper;
	
	public List<ReplyVO> replyListData(Map map){
		return mapper.replyListData(map);
	}
	public int replyRowCount() {
		return mapper.replyRowCount();
	}
	public int replyTotalPage() {
		return (int)(Math.ceil(mapper.replyRowCount()/10.0));
	}
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	public ReplyVO replyDetailData(int no) {
		mapper.replyHitIncrement(no);
		return mapper.replyDetailData(no);
	}
	public int replyCount(int group_id) {
		return mapper.replyCount(group_id);
	}
	public ReplyVO replyParentInfoData(int no) {
		return mapper.replyParentInfoData(no);
	}
	public void replyReplyInsert(ReplyVO vo) {
		mapper.replyReplyInsert(vo);
	}
	public ReplyVO replyUpdateData(int no) {
		return mapper.replyDetailData(no);
	}
	public boolean replyUpdate(ReplyVO vo) {
		boolean bCheck=false;
		String db_pwd=mapper.replyGetPassword(vo.getNo());
		if(db_pwd.equals(vo.getPwd())) {
			bCheck=true;
			mapper.replyUpdate(vo);
		}
		return bCheck;
	}
	public boolean replyDelete1(int no,int group_id,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.replyGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.replyDelete1(group_id);
		}
		return bCheck;
	}
	public boolean replyDelete2(int no,String pwd) {
		boolean bCheck=false;
		String db_pwd=mapper.replyGetPassword(no);
		if(db_pwd.equals(pwd)) {
			bCheck=true;
			mapper.replyDelete2(no);
		}
		return bCheck;
	}
}
