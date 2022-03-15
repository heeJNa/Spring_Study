package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;;
@RestController
// 화면 이동 요청 => 일반 데이터 (자바스크립트) => VueJS
public class BoardRestController {
	@Autowired
	// spring에 요청해서 => dao객체 주소를 대입해달라 => 객체 비교 (instanceof)
	private BoardDAO dao;
	
	@PostMapping("board/update_ok.do")	// jsp한개를 생략
	public String board_update_ok(BoardVO vo) {
		String result="";
		boolean bCheck=dao.board_update(vo);
		if(bCheck==true) {
			result="<script>"
					+"location.href=\"detail.do?no="+vo.getNo()+"\""
					+"</script>";
		}else {
			result="<script>"
					+"alert(\"Password Fail!!\");"
					+"history.back();"
					+"</script>";
		}
		return result;
	}
	@PostMapping("board/delete_ok.do")
	public String board_delete_ok(String pwd, int no) {
		String result="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck==true) {
			result="1";
		}else {
			result="0";
		}
		return result;
	}
	
}
