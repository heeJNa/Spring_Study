package com.sist.di3;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class HotelDAO extends SqlSessionDaoSupport{
	public List<HotelVO> hotelListData(){
		return getSqlSession().selectList("hotelListData");
	}
}
