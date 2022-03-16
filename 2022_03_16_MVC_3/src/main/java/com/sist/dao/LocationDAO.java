package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
/*
 	DAO : 오라클 연동
 	Controller : DAO, JSP연결
 	JSP : 화면 출력
  
 */
@Repository
public class LocationDAO {
	// MyBatis에서 구현된 객체 주소값을 주입 요청
	@Autowired
	private LocationMapper mapper;
	
	public List<LocationVO> locationListData(Map map){
		return mapper.locationListData(map);
	}
	
	public int locationTotalPage() {
		return mapper.locationTotalPage();
	}
	
	public LocationVO locationDetailData(int no) {
		return mapper.locationDetailData(no);
	}
}
