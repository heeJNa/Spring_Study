package com.sist.di2;
import java.util.*	;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class LocationDAO extends SqlSessionDaoSupport{
	public List<LocationVO> locationListData(){
		return getSqlSession().selectList("locationListData");
	}
}
