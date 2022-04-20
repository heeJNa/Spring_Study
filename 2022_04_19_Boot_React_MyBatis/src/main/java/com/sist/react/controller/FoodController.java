package com.sist.react.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.react.service.ReactService;
import com.sist.react.vo.CategoryVO;
import com.sist.react.vo.FoodVO;
@RestController
@CrossOrigin("http://localhost:3000")
public class FoodController {
	@Autowired
	private ReactService service;
	
	@GetMapping("/food/category")
	public List<CategoryVO> foodCategoryData(int no){
		Map map = new HashMap();
		if(no==1) {
			map.put("start",1);
			map.put("end",12);
		}else if(no==2) {
			map.put("start",13);
			map.put("end",18);
		}else {
			map.put("start",19);
			map.put("end",30);
		}
		return service.foodCategoryData(map); // List를 JSON으로 변경해서 전송
		// jackson
	}
	@GetMapping("/food/food_list")
	public List<FoodVO> foodListData(int cno){
		List<FoodVO> list = new ArrayList<FoodVO>();
		list= service.foodCategoryListData(cno);
		for(FoodVO vo: list) {
			String poster = vo.getPoster();
			poster= poster.substring(0,poster.indexOf("^"));
			vo.setPoster(poster);
			
			String address=vo.getAddress();
			address=address.substring(0,address.lastIndexOf("지"));
			vo.setAddress(address.trim());
		}
		return list;
	}
	@GetMapping("/food/cate_info")
	public CategoryVO categoryInfo(int cno) {
		CategoryVO vo = new CategoryVO();
		vo = service.foodCategoryInfoData(cno);
		
		return vo;
	}
	@GetMapping("/food/detail")
	public FoodVO foodDetailData(int no) {
		FoodVO vo = service.foodDetailData(no);
		vo.setAddress(vo.getAddress().substring(0,vo.getAddress().lastIndexOf("지")).trim());
		return vo;
	}
	
}
