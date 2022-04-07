package com.sist.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sist.dao.FoodDAO;
import com.sist.manager.RecommandManager;
import com.sist.vo.FoodVO;
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@Autowired
	private RecommandManager rm;
	@GetMapping(value="food/food_recommande_vue.do",produces="text/plain;charset=utf-8")
	public String food_recommand (int no) {
		String result="";
		if(no==1) {
			String[] data= {"외로움","기분전환","슬픔","이별","지침","힘듦","설렘","오후","위로","밤","새벽","사랑","스트레스","짜증","그리움","우울","행복","불안","분노","기쁨","축하"};
			JSONArray arr= new JSONArray();
			for(String d:data) {
				arr.add(d);
			}
			result=arr.toJSONString();
		}else if(no==2) {
			String[] data= {"봄","여름","가을","겨울","맑은날","추운날","흐린날","비오는날","더운날","눈오는날"};
			JSONArray arr= new JSONArray();
			for(String d:data) {
				arr.add(d);
			}
			result=arr.toJSONString();
		}
		return result;
	}
	
	@GetMapping(value="food/food_find_vue.do",produces="text/plain;charset=utf-8")
	public String food_find(String fd) {
		String result="";
		List<String> nList=dao.foodGetNameData();
		List<String> rList=rm.recommandData(fd);
		List<String> fList=new ArrayList<String>();
		
		Pattern[] p=new Pattern[nList.size()];
		// * (0이상), + (1이상), ? (0, 1이상) , | (선택)
		// []
		// {}
		// [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}
		for(int i=0;i<p.length;i++) {
			p[i]=Pattern.compile(nList.get(i));
		}
		Matcher[] m = new Matcher[nList.size()];
		int[] count=new int[nList.size()];
		for(String s:rList) {
			for(int i=0;i<m.length;i++) {
				m[i]=p[i].matcher(s);
				while(m[i].find()){
					String ss=m[i].group();
					if(ss.length() > 1) {
						System.out.println("ss의 문자 길이= "+ss.length());
						System.out.println("ss= "+ss);
						fList.add(ss);
						count[i]++;
					}
				}
			}
		}
		// 출력
		Set<String> set = new HashSet<>();
		for(int i=0;i<fList.size();i++) {
			set.add(fList.get(i));
		}
		List<FoodVO> pList=new ArrayList<>();
		Iterator<String> iter=set.iterator();
		while(iter.hasNext()) {
			String ss=iter.next().toString();
			List<FoodVO> vo=dao.foodNameFind(ss);
			pList.add(vo.get(0));
		}

		Gson gson = new Gson();
		result=gson.toJson(pList);
		
		return result;
	}
}
