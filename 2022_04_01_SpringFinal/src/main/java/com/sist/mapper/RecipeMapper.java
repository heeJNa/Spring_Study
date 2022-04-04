package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface RecipeMapper {
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/ no,title,poster "
			+ "FROM recipe)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe")
	public int recipeTotalPage();
	
	@Select("SELECT * FROM recipe_detail "
			+ "WHERE no=#{no}")
	public RecipeDetailVO recipeDetailDAta(int no); // recipe,chef => mapper => service
	
	@Select("SELECT chef,poster "
			+ "FROM (SELECT chef,poster,rownum as num "
			+ "FROM (SELECT chef,poster "
			+ "FROM chef ORDER BY mem_cont1 DESC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM chef")
	public int chefTotalPage();
	
	// Chef가 만든 레시피 읽기
	@Select("SELECT no,poster,title,num "
			+ "FROM (SELECT no,poster,title,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/no,poster,title "
			+ "FROM recipe WHERE chef=#{chef})) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefMakeRecipeData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM recipe "
			+ "WHERE chef=#{chef}")
	public int chefMakeRecipeTotalpage(String chef);
	
	
}
