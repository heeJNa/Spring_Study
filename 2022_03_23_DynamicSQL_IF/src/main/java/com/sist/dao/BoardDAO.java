package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO extends SqlSessionDaoSupport{
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public List<BoardVO> boardListData(){
		return getSqlSession().selectList("boardListData");
	}
	
	public List<BoardVO> boardFindData(Map map){
		return getSqlSession().selectList("boardFindData",map);
	}
}
