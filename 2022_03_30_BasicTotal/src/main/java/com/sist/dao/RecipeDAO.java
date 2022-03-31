package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class RecipeDAO {
	// 구현된 매퍼의 주소값을 얻어온다
	@Autowired
	private RecipeMapper mapper; // 객체를 지정할 수 없다.
	
	public List<RecipeVO> recipeListData(Map map){
		return mapper.recipeListData(map);
	}
	
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	
	public int recipeCount() {
		return mapper.recipeCount();
	}
	
	public List<ChefVO> chefListData(Map map){
		return mapper.chefListData(map);
	}
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	
	public List<RecipeVO> chefRecipeListDataAll(Map map){
		return mapper.chefRecipeListDataAll(map);
	}
	public List<RecipeVO> chefRecipeListData(Map map){
		return mapper.chefRecipeListData(map);
	}
	public int chefRecipeCountAll(String chef) {
		return mapper.chefRecipeCountAll(chef);
	}
	public int chefRecipeCount(String chef) {
		return mapper.chefRecipeCount(chef);
	}
	public RecipeDetailVO recipeDetailData(int no) {
		return mapper.recipeDetailData(no);
	}
	public List<GoodsVO> goodsTopData(String product_name){
		return mapper.goodsTopData(product_name);
	}
}
