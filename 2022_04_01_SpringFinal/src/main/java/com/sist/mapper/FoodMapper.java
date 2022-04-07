package com.sist.mapper;
//SQL문장 저장
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.FoodVO;
public interface FoodMapper {
	@Select("SELECT no,poster,name,num "
			+ "FROM (SELECT no,poster,name,rownum as num "
			+ "FROM (SELECT/*+ INDEX_ASC(food_location fl_no_pk)*/no,poster,name "
			+ "FROM food_location)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM food_location")
	public int foodTotalPage();
	
	@Select("SELECT * FROM food_location "
			+ "WHERE no=#{no}")
	// *를 사용하기 위해서는 반드시 테이블의 모든 컬럼이 선언되어 있어야 한다.
	public FoodVO foodDetailData(int no);
	
	@Select("SELECT no,poster,name,rownum "
			+ "FROM (SELECT no,poster,name "
			+ "FROM food_location "
			+ "WHERE address LIKE '%'||#{address}||'%') "
			+ "WHERE rownum<=20")
	public List<FoodVO> foodFindData(String address);
	// 추천
	@Select("SELECT DISTINCT name FROM food_location")
	public List<String> foodGetNameData();
	@Select("SELECT no,poster,name "
			+ "FROM food_location "
			+ "WHERE name=#{name}")
	public List<FoodVO> foodNameFind(String name);
}
