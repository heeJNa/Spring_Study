package com.sist.dao;

import org.springframework.stereotype.Component;

@Component
public class MyDAO3 {
	public void display() {
		for(int i=1; i<100000000;i++) {
			System.out.println(i);
		}
	}
}
