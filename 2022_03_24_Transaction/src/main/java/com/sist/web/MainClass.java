package com.sist.web;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;

import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		dao.txInsert();
		List<StudentVO> list = dao.studentListData();
		for(StudentVO vo:list) {
			System.out.println("=======================================");
			System.out.println("no:"+ vo.getNo() );
			System.out.println("name: "+vo.getName());
			System.out.println("kor: "+vo.getKor());
			System.out.println("eng: "+vo.getEng());
			System.out.println("math: "+vo.getMath());
		}
	}

}
