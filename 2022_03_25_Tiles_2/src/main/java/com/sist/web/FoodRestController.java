package com.sist.web;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/find_vue.do",produces = "text/plain;charset=utf-8")
	public String food_find(String page,String addr) {
		String result="";
		try {
			if(page==null) 
				page="1";
			if(addr==null)
				addr="강남";
			
			int curpage=Integer.parseInt(page);
			int rowSize=12;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			Map map=new HashMap();
			map.put("start",start);
			map.put("end",end);
			map.put("address",addr);
			
			List<FoodVO> list=dao.foodFindData(map);
			int totalpage=dao.foodFindTotalPage(addr);
			int i=0;
			JSONArray arr=new JSONArray(); //List 매칭
			for(FoodVO vo:list) {
				JSONObject obj=new JSONObject(); // vo에 값을 담아준다
				obj.put("no",vo.getNo());
				obj.put("name",vo.getName());
				obj.put("score",vo.getScore());
				String poster=vo.getPoster();
				poster=poster.substring(0,poster.indexOf("^"));
				obj.put("poster",poster);
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
	// 상세보기
	@GetMapping(value="food/detail_vue.do",produces = "text/plain;charset=utf-8")
	public String food_detail(int no) {
		String result="";
		try {
			FoodVO vo = dao.foodDetailData(no);
			JSONObject obj = new JSONObject();
			obj.put("no",vo.getNo());
			obj.put("name",vo.getName());
			obj.put("address",vo.getAddress());
			obj.put("tel",vo.getTel());
			obj.put("score",vo.getScore());
			obj.put("type",vo.getType());
			obj.put("poster",vo.getPoster());
			obj.put("time",vo.getTime());
			obj.put("parking",vo.getParking());
			obj.put("menu",vo.getMenu());
			
			result=obj.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
