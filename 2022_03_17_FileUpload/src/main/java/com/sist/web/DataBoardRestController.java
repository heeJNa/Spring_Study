package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// 자바스크립트에 값을 전송시에 사용 (Ajax, VueJS, ReactJS)
// 데이터가 여러개 => List=>[] => JSONArray , VO=>{} =>JSONObject
// JSP가 아닐 수 있다 (HTML)
import com.sist.dao.DataBoardDAO;
@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@PostMapping("databoard/passwordCheck.do")
	public String databoard_check(int no, String pwd) {
		String result="0";
		// db에 있는 비밀번호 읽기
		String db_pwd=dao.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			result="1";
		}else {
			result="0";
		}
		return result;
	}
	@PostMapping("databoard/delete_ok.do")
	// javaScript, Json 등 데이터 전송 
	public String databoard_delete_ok(String pwd, int no) {
		String result =""; // javascript
		String db_pwd=dao.databoardGetPassword(no);
		if(db_pwd.equals(pwd)) {
			dao.databoardDelete(no);
			result="<script>"
					+ "location.href=\"list.do\""
					+ "</script>";
		}else {
			result="<script>"
					+ "alert(\"비밀번호가 틀립니다!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	// @ResponseBody => 승격 => @RestController
}
