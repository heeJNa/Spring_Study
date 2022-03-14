package com.sist.di2;
import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.di.EmpDAO;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			AnnotationConfigApplicationContext app=
					new AnnotationConfigApplicationContext(LocationConfig.class); // 컴파일된 파일을 읽어온다
			LocationDAO dao=(LocationDAO)app.getBean("dao1");
			List<LocationVO> list = dao.locationListData();
			for(LocationVO vo : list) {
				System.out.println(vo.getTitle());
			}
	}

}
