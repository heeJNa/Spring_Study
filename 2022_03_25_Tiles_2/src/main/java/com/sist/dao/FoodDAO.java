package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

@Repository
// 스프링에서 OOP의 규칙이 없는 곳은 어디인가? @Autowired 부분
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	public int foodFindTotalPage(String address) {
		return mapper.foodFindTotalPage(address);
	}
	public FoodVO foodDetailData(int no) {
		return mapper.foodDetailData(no);
	}
}
