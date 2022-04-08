package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.GoodsVO;

public interface GoodsMapper {
	@Select("SELECT product_name,product_poster,product_price "
			+ "FROM goods "
			+ "WHERE product_name LIKE '%'||#{product_name}||'%'")
	public List<GoodsVO> goodsLikeData(String product_name);
	
	@Select("SELECT COUNT(*) "
			+ "FROM goods "
			+ "WHERE product_name LIKE '%'||#{product_name}||'%'")
	public int goodsCountData(String product_name);
}
