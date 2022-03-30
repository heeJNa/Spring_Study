package com.sist.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RecipeDetailVO {
	private int no;
	private String poster,title,chef,chef_poster,chef_profile, info1,info2,info3,content,foodmake,etc;
	private List<String> fList = new ArrayList<>();
	private List<String> iList=new ArrayList<>();
}
