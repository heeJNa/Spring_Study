package com.sist.web;

import java.util.List;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			StudentDAO dao = new StudentDAO();
			List<StudentVO> list = dao.stdListData();
			for(StudentVO vo :list) {
				System.out.println(vo.getHakbun()+" "
				+vo.getName());;
			}
	}	

}
