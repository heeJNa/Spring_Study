package com.sist.web;

public class MainClass {
	
	public static void main(String[] args) {
		MyDAO dao = new MyDAO(); // 스프링에서는 이 메모리할당을 Container가 해준다. (필요시마다 객체 주소를 얻어서 처리)
		System.out.println("=== select() call ===");
		dao.select();
		System.out.println("=== insert() call ===");
		dao.insert();
		System.out.println("=== update() call ===");
		dao.update();
		System.out.println("=== delete() call ===");
		dao.delete();
		System.out.println("=== find() call ===");
		dao.find();
	}
	
	
}
