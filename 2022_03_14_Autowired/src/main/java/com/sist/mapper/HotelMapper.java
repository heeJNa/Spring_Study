package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.HotelVO;

public interface HotelMapper {
	@Select("SELECT name,address,score FROM seoul_hotel")
	public List<HotelVO> hotelListData();
}
