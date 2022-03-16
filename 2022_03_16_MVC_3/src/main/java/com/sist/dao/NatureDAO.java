package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.NatureMapper;
import com.sist.vo.NatureVO;
@Repository
public class NatureDAO {
	
	@Autowired
	private NatureMapper mapper;
	
	public List<NatureVO> natureListData(Map map){
		return mapper.natureListData(map);
	}
	
	public int natureTotalPage() {
		return mapper.natureTotalPage();
	}
	
	public NatureVO natureDetailData(int no) {
		return mapper.natureDetailData(no);
	}
}
