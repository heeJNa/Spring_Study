package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.dao.RecipeDAO;
import com.sist.vo.*;
@Controller
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	@GetMapping("recipe/list.do")
	public String recipe_list(String page, Model model) {
		//Model => request,response사용을 권장하지 않는다 => 전송객체 (Model)
		  //사용자가 보내준 값 , 내장객체 => DispatcherServlet을 통해서 받아 온다 
		  //매개변수를 통해서 받는다 (순서는 상관없다)
		  // 리턴값은 2개중에 한개 선택 
		  // model값을 전송 => forward  ==> return "경로/파일명"
		  // 재전송 => sendRedirect()  ==> return "redirect:~~.do"
		  // 대부분은 해당 데이터형으로 받는다 
		  // 처음 실행시에 사용자가 page를 선택할 수 없다
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end",end);
		List<RecipeVO> rList= dao.recipeListData(map);
		int totalpage=dao.recipeTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("rList", rList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		// Cookie값 전송
		return "recipe/list";
	}
	
	// 조건 ==> 라이브러리 => 안에 코딩이 불가능 (컴파일된 파일만 보내준다) 
	// 읽어 갈 수 있는 소스 코딩 => 형식 => String
	@GetMapping("recipe/detail.do")
	public String recipeDetailData(int no,Model model) {
		RecipeDetailVO vo = dao.recipeDetailDAta(no);
		List<String> mList=new ArrayList<>();
		List<String> pList=new ArrayList<String>();
		String[] data=vo.getFoodmake().split("\n");
		for(String s:data) {
			StringTokenizer st = new StringTokenizer(s,"^");
			mList.add(st.nextToken());
			pList.add(st.nextToken());
		}
		model.addAttribute("pList", pList);
		model.addAttribute("mList", mList);
		model.addAttribute("vo", vo);
		return "recipe/detail";
	}
	
	@GetMapping("recipe/chef.do")
	public String recipe_chef(String page,Model model) {
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end",end);
		List<ChefVO> rList= dao.chefListData(map);
		int totalpage=dao.chefTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("rList", rList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "recipe/chef";
	}
	@GetMapping("recipe/chef_detail.do")
	public String recipe_chef_detail(String page,String chef,Model model) {
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start",start);
		map.put("end",end);
		map.put("chef",chef);
		List<RecipeVO> rList= dao.chefMakeRecipeData(map);
		int totalpage=dao.chefMakeRecipeTotalpage(chef);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("rList", rList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("chef", chef);
		return "recipe/chef_detail";
	}
}
