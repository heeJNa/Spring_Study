package com.sist.mybatis;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 클래스를 등록
		ApplicationContext app =
				new ClassPathXmlApplicationContext("app.xml");
		// 필요한 클래스를 app에서 찾아서 사용
		EmpDAO dao =(EmpDAO) app.getBean("dao");
		// 사원 목록
		List<EmpVO> list = dao.empListData();
		for(EmpVO vo:list) {
			System.out.println(vo.getEmpno()+" "
					+ vo.getEname()+" "
					+vo.getJob()+ " "
					+ vo.getHiredate().toString()+" "
					+ vo.getSal()+" "
					+ vo.getDeptno()+" ");
		}
		System.out.println();
		// 상세보기
		EmpVO vo=dao.empDetailData(7788);
		System.out.println("7788사원 정보 = "+vo.getEmpno()+" "
					+ vo.getEname()+" "
					+vo.getJob()+ " "
					+ vo.getHiredate().toString()+" "
					+ vo.getSal()+" "
					+ vo.getDeptno()+" ");
		System.out.println();
		System.out.println("=============== Emp Join Dept ===============");
		List<EmpVO> jList = dao.empDeptJoinData();
		for(EmpVO jvo:jList) {
			System.out.println(jvo.getEmpno()+" "
					+ jvo.getEname()+" "
					+jvo.getJob()+ " "
					+ jvo.getHiredate().toString()+" "
					+ jvo.getSal()+" "
					+ jvo.getDeptno()+" "
					+jvo.getDvo().getDname()+" "
					+jvo.getDvo().getLoc()+" ");
		}
		System.out.println();
		System.out.println("=============== Emp Join Dept (Detail) ===============");
		EmpVO jvo=dao.empDeptDetailData(7788);
		System.out.println("7788사원 정보 = "+jvo.getEmpno()+" "
					+ jvo.getEname()+" "
					+jvo.getJob()+ " "
					+ jvo.getHiredate().toString()+" "
					+ jvo.getSal()+" "
					+ jvo.getDeptno()+" "
					+jvo.getDvo().getDname() +" "
					+jvo.getDvo().getLoc()+ " ");
	}
}
