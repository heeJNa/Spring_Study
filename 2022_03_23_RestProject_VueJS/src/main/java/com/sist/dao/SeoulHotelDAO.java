package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulHotelMapper;

@Repository
public class SeoulHotelDAO {
	@Autowired
	private SeoulHotelMapper mapper;
	
	public List<SeoulHotelVO> hotelListData(Map map){
		return mapper.hotelListData(map);
	}
	
	public int hotelTotalPage() {
		return mapper.hotelTotalPage();
	}
	
	public SeoulHotelVO hotelDetailData(int no) {
		return mapper.hotelDetailData(no);
	}
}
