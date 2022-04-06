package com.sist.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ReplyVO {
	private int no, hit,group_id,group_step,group_tab;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
	
}
