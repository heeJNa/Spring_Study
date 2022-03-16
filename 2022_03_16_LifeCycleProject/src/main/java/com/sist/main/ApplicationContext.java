package com.sist.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ApplicationContext {
	private Map map=new HashMap();
	public static void main(String[] args) {
		ApplicationContext app =
				new ApplicationContext("/Users/kimheejun/springDev/springStudy/2022_03_16_LifeCycleProject/src/main/java/com/sist/main/app.xml");
		Sawon sa=(Sawon)app.getBean("sa");
		System.out.println(sa.getName());
		System.out.println(sa.getSex());
	}
	public ApplicationContext(String path) {
		try {
			SAXParserFactory spf=SAXParserFactory.newDefaultInstance();
			SAXParser sp=spf.newSAXParser();
			HandlerMapping hm=new HandlerMapping();
			sp.parse(new File(path),hm);
			map=hm.getClsMap();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Object getBean(String id) {
		
		return map.get(id);
	}
}
