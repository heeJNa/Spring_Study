package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	// vue => CURD
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=utf-8")
	public String food_list_vue(String page) {
		String result="";
		try {
			// 1. page => null (default)
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page); // 현재 페이지
			Map map=new HashMap();
			int rowSize=10;
			int start=(rowSize*curpage)-(rowSize-1);
			int end = rowSize*curpage;
			map.put("start",start);
			map.put("end",end);
			// 게시판 목록 읽기
			List<BoardVO> list= dao.boardListData(map);
			// 총 페이지
			int totalpage=dao.boardTotalPage();
			JSONArray arr = new JSONArray();
			int i =0;
			for(BoardVO vo :list) {
				JSONObject obj=new JSONObject();
				obj.put("no",vo.getNo());
				obj.put("subject",vo.getSubject());
				obj.put("name",vo.getName());
				obj.put("regdate",vo.getDbday());
				obj.put("hit",vo.getHit());
				if(i==0) {
					obj.put("curpage",curpage);
					obj.put("totalpage",totalpage);
				}
				arr.add(obj);
				i++;
			}
			result=arr.toJSONString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping(value="food/board_detail_vue.do",produces = "text/plain;charset=utf-8")
	public String food_detail_vue(int no){
		String result="";
		try {
			BoardVO vo = dao.boardDetailData(no);
			JSONObject obj = new JSONObject();
			obj.put("no",vo.getNo());
			obj.put("subject",vo.getSubject());
			obj.put("name",vo.getName());
			obj.put("content",vo.getContent());
			obj.put("regdate",vo.getDbday());
			obj.put("hit",vo.getHit());
			result=obj.toJSONString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
