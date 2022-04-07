package com.sist.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.RecipeDAO;
import com.sist.vo.RecipeVO;

@Controller
public class MainController {
	@Autowired
	private RecipeDAO dao;
	@GetMapping("main/main.do")
	public String main_main(Model model) {
		int[] com= new int[5];
		List<RecipeVO> list= new ArrayList();
		getRand(com);
		for(int i=0;i<5;i++) {
			RecipeVO vo =dao.recipeMainData(com[i]);
			list.add(vo);
		}
		model.addAttribute("list", list);
		return "main";
	}
	
	public void getRand(int[] com) {
		int su=0;
		boolean bCheck=false;
		for(int i=0;i<5;i++) {
			bCheck=true;
			while(bCheck) {
				su=(int)(Math.random()*163953)+1;
				bCheck=false;
				for(int j=0;j<i;j++) {
					if(com[j]==su) {
						bCheck=true;
						break;
					}
				}
			}
			com[i]=su;
		}
	}
}
