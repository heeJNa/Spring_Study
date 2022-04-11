package com.sist.dao;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentVO {
	private int hakbun,kor,eng,math;
	private String name,dbday;
	private Date regdate;
}
