package com.sist.web;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.RecipeDAO;
import com.sist.vo.*;
@Controller
@RequestMapping("food/")
public class RecipeController {
	// 필요한 객체 => 스프링에서 생성 객체 => 자동주입 요청
	@Autowired
	@Qualifier("recipeDAO") // 프로그래머가 특정객체를 지정 (속도가 더 빠르다)
	// @Autowired + @Qualifier => @Resource (JDK 1.8)
	// @Resouce(name="recipeDAO")
	private RecipeDAO dao;
	 /*
	   *   ========================
	   *   1. 사용자 요청 => .do 
	   *   2. DispatcherServlet
	   *   ======================== web.xml
	   *   3. DispatcherServlet => 명령 => 요청내용을 찾아라 (HandlerMapping)
	   *                       @Controller
	   *                       @RestController
	   *   4. DispatcherServlet => 찾으면 => 메소드호출 (HandlerAdapter)
	   *   5. DispatcherServlet => return을 받아서 JSP찾아라 (ViewResolver)
	   *                       경로명 / 확장자 
	   *   6. DispatcherServlet => JSP를 화면에 출력 한다 
	   *      ================= CPU
	   *      스프링 : 메인보드 
	   */
	@GetMapping("recipe.do")
	public String food_recipe(String page,Model model) {
		// 메소드 호출 => HandlerAdapter
		// 매개변수 => DispatcherServlet => invoke(page,model)
		
		if(page==null)
			page="1"; // default page
		int curpage= Integer.parseInt(page);
		int rowsize=20;
		Map map=new HashMap();
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		
		List<RecipeVO> list = dao.recipeListData(map);
		for(RecipeVO vo:list) {
			String title=vo.getTitle();
			if(title.length()>18) {
				title=title.substring(0,18)+"...";
			}
			vo.setTitle(title);
		}
		int totalpage=dao.recipeTotalPage();
		int count=dao.recipeCount();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("count",count);
		model.addAttribute("list", list);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage", endPage);
		
		return "food/recipe";
	}
	
	@GetMapping("chef.do")
	public String food_chef(String page, Model model) {
		if(page==null)
			page="1"; // default page
		int curpage= Integer.parseInt(page);
		int rowsize=20;
		Map map=new HashMap();
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		
		List<ChefVO> list = dao.chefListData(map);
		int totalpage=dao.chefTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage", endPage);
		
		return"food/chef";
	}
	
	@RequestMapping("chef_recipe_list.do")
	public String chef_recipe_list(String ss,String page,String chef,Model model) {
		if(ss==null)
			ss="all";
		if(page==null)
			page="1"; // default page
		int curpage= Integer.parseInt(page);
		int rowsize=20;
		Map map=new HashMap();
		int start=(rowsize*curpage)-(rowsize-1);
		int end=rowsize*curpage;
		map.put("start",start);
		map.put("end",end);
		map.put("chef",chef);
		map.put("ss",ss);
		
		List<RecipeVO> list = new ArrayList<>();
		if(ss.equals("all")){
			list=dao.chefRecipeListDataAll(map);
		}else {
			list=dao.chefRecipeListData(map);
		}
		int totalpage=0;
		if(ss.equals("all")){
			totalpage=dao.chefRecipeCountAll(chef);
		}else {
			totalpage=dao.chefRecipeCount(chef);
		}
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage", endPage);
		
		return "food/chef_recipe_list";
	}
	@GetMapping("recipe_detail.do")
	public String recipe_detail(int no,Model model) {
		RecipeDetailVO vo = dao.recipeDetailData(no);
		String[] make=vo.getFoodmake().split("\n");
		for(String s:make) {
			StringTokenizer st= new StringTokenizer(s,"^");
			vo.getFList().add(st.nextToken());
			vo.getIList().add(st.nextToken());
		}
		model.addAttribute("vo", vo);
		model.addAttribute("fList", vo.getFList());
		model.addAttribute("iList",vo.getIList());
		return "food/recipe_detail";
	}
	
	@GetMapping("goods_list.do")
	public String recipe_goods_list(String data, Model model) {
		data=data.substring(0,data.indexOf(" "));
		List<GoodsVO> list=dao.goodsTopData(data);
		model.addAttribute("list", list);
		return "food/goods_list";
	}
}
