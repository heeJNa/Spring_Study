package com.sist.react.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.react.vo.CategoryVO;
import com.sist.react.vo.FoodVO;
@Repository
@Mapper
public interface FoodMapper {
	public List<CategoryVO> foodCategoryData(Map map);
	public List<FoodVO> foodCategoryListData(int cno);
	public CategoryVO foodCategoryInfoData(int cno);
	public FoodVO foodDetailData(int no);
}
