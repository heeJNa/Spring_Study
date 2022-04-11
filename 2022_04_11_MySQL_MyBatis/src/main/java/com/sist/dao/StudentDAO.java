package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class StudentDAO {
	@Autowired
	private StudentMapper mapper;
	
	public List<StudentVO> stdListData(Map map){
		return mapper.stdListData(map);
	}
	public int studentTotalPage() {
		return mapper.studentTotalPage();
	}
	
	public void stdInsert(StudentVO vo) {
		mapper.stdInsert(vo);
	}
	public void stdDelete(int hakbun) {
		mapper.stdDelete(hakbun);
	}
	
	public StudentVO stdUpdateData(int hakbunt) {
		return mapper.stdUpdateData(hakbunt);
	}
	public void stdUpdate(StudentVO vo) {
		mapper.stdUpdate(vo);
	}
}
