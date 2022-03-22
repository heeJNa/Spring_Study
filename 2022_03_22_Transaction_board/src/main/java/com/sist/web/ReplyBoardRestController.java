package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class ReplyBoardRestController {
	// 필요한 객체를 스프링에서 얻어온다 DAO
	@Autowired
	private ReplyBoardDAO dao;
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(ReplyBoardVO vo) {
		String script="";
		boolean bCheck= dao.replyBoardUpdate(vo);
		if(bCheck==true) {
			script="<script>"
					+"location.href=\"detail.do?no="+vo.getNo()+"\";"
					+"</script>";
		}else {
			script="<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back();"
					+ "</script>";
			// 주의점 => Ajax => 크로베서만 실행 IE=>문자열로 출력
		}
		return script;
	}
}
