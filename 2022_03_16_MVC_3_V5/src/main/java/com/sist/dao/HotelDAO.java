package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.HotelMapper;
import com.sist.vo.HotelVO;

import java.util.*;
@Repository
public class HotelDAO {
	@Autowired
	private HotelMapper mapper;
	
	public List<HotelVO> hotelListData(Map map){
		return mapper.hotelListData(map);
	}
	
	public int hotelTotalPage() {
		return mapper.hotelTotalPage();
	}
	
	public HotelVO hotelDetailData(int no) {
		return mapper.hotelDetailData(no);
	}
}
