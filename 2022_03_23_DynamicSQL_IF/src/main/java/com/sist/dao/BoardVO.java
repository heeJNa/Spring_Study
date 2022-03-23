package com.sist.dao;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd;
	private Date regdate;
}
