package com.sist.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.dao.RecipeDAO;
import com.sist.vo.ChefVO;
import com.sist.vo.RecipeDetailVO;
import com.sist.vo.RecipeVO;
@Controller
public class RecipeController {
	@Autowired
	private RecipeDAO dao;
	@GetMapping("recipe/list.do")
	public String recipe_list(String page, Model model,HttpServletRequest req) {
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
		Cookie[] cookies=req.getCookies();
		List<RecipeDetailVO> cList=new ArrayList<>();
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("r")) {
					cookies[i].setPath("/");
					String no=cookies[i].getValue();
					RecipeDetailVO vo=dao.recipeDetailDAta(Integer.parseInt(no));
					cList.add(vo);
				}
			}
			model.addAttribute("cList", cList);
		}
		return "recipe/list";
	}
	
	// 조건 ==> 라이브러리 => 안에 코딩이 불가능 (컴파일된 파일만 보내준다) 
	// 읽어 갈 수 있는 소스 코딩 => 형식 => String
	@GetMapping("recipe/detail_before.do")
	public String recipe_detail_before(int no,HttpServletResponse response,RedirectAttributes ra) {
		Cookie cookie = new Cookie("r"+no,String.valueOf(no));
		// 단점 => 문자열만 저장 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24); // 기간
		// 클라이언트로 전송
		response.addCookie(cookie);
		ra.addAttribute("no", no);
		return "redirect:../recipe/detail.do";
	}
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
	@GetMapping("recipe/recipe_find.do")
	public String recipe_find(String fd,Model model) {
		
		return "recipe/recipe_find";
	}
	@GetMapping("recipe/recipe_recommand.do")
	public String recipe_recommand() {
		
		return"recipe/recipe_recommand";
	}
	@RequestMapping("recipe/priceCompare.do")
	public String recipe_priceCompare(String[] recipe, String fd,Model model) {
		if(recipe!=null) {
			Map map=new HashMap();
			map.put("fsArr",recipe);
			map.put("ss",fd);
			List<RecipeVO> list=dao.recipeSearchData(map);
			model.addAttribute("rList", list);
		}
		model.addAttribute("fd", fd);
		model.addAttribute("option", recipe);
		return "recipe/priceCompare";
	}
	
}
