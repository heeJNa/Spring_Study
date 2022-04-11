package com.sist.dao;

import java.util.Date;


import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentVO {
	private int hakbun;
	private int kor;
	private int eng;
	private int math;
	@NotEmpty
	private String name;
	private String dbday;
	private Date regdate;
}
