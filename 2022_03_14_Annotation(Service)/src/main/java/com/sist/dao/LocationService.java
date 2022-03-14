package com.sist.dao;

import java.util.List;

import com.sist.vo.LocationVO;
// 게시판 / 댓글, location/nature/hotel
public interface LocationService {
	public List<LocationVO> locationListData();
}
