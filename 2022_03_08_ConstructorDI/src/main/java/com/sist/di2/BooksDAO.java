package com.sist.di2;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BooksDAO extends SqlSessionDaoSupport{
	public List<BooksVO> bookListData() {
		
		return getSqlSession().selectList("booksListData");
	}
}
