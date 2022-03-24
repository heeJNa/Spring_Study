package com.sist.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app = new ClassPathXmlApplicationContext("app.xml");
		MyDAO dao=(MyDAO)app.getBean("myDAO");
		dao.select();
		System.out.println("----------------------------");
		dao.insert();
		System.out.println("----------------------------");
		dao.update();
		System.out.println("----------------------------");
		dao.delete();
	}

}
