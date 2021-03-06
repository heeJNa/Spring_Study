package com.sist.di;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//1. XML파싱
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("dao");
		List<EmpVO> list=dao.empListData();
		// 출력
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+ vo.getEname()+" "
					+ vo.getJob()+ " "
					+ vo.getHiredate()+" "
					+ vo.getSal()+ " ");
		}
	}
}
