package com.sist.dao;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmpVO {
	private int empno,sal;
	private String ename,job;
	private Date hiredate;
}
