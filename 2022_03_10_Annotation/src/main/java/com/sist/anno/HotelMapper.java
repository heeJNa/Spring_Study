package com.sist.anno;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface HotelMapper {
	@Select("SELECT name,address FROM seoul_hotel")
	public List<HotelVO> hotelListData();
	
	/*
	 * 	<select id="hotelListData" resultType="HotelVO">
	 * 		SELECT name,address FROM seoul_hotel
	 * </select>
	 * 
	 * 
	 */
}
