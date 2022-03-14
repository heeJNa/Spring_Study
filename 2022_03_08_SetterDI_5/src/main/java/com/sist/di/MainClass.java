package com.sist.di;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			AnnotationConfigApplicationContext app=
					new AnnotationConfigApplicationContext(EmpConfig.class);
			EmpDAO dao = app.getBean("dao",EmpDAO.class);
			
			List<EmpVO> list=dao.empListData();
			for(EmpVO vo:list) {
				System.out.println(vo.getEmpno());
				
			}
	}

}
