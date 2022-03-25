package com.sist.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
// 디폴트 생성자
public class FoodVO {
	private int no;
	private double score;
	private String poster,name,address,tel,type,price,parking,time,menu;
}
