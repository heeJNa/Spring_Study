package com.sist.container1;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/*
 * 		컨테이너 / 컴포넌트(사용자 정의) => 한개 Class
 * 		--------
 * 			관리자(spring에서 제공) => 요청시 찾기(DL) (객체를 찾아서 넘겨주는 역할)
 * 			----------------------
 * 				 BeanFactory
 * 					  |
 * 			------------------------------
 * 			|							 |
 *    ApplicationContext     AnnotationConfigApplicationContext
 * 			|  XML로 등록		자바에서 등록 (자바버전)
 * 	WebApplicationContext
				웹 MVC
 
 *
 */				
public class ApplicationContext {
	private Map map=new HashMap(); // 클래스 객체 주소를 저장한다
	public ApplicationContext(String path) {
		try {
			// XML 파싱
			DocumentBuilderFactory dbf =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc = db.parse(new File(path));
			Element beans=doc.getDocumentElement();
			NodeList list = beans.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++) {
				Element bean=(Element)list.item(i);
				String cls=bean.getAttribute("class");
				String id=bean.getAttribute("id");
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				map.put(id,obj);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Object getBean(String id) {
		return map.get(id);
	}
}
