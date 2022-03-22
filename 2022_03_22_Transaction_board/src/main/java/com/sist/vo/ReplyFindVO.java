package com.sist.vo;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

public class ReplyFindVO {
	@Setter
	@Getter
	private String ss;
	@Setter
	@Getter
	private String fs;
	public String[] getFsArr() {
		return fs==null?new String[] {}: fs.split(""); 
	}
	
	public static void main(String[] args) {
		ReplyFindVO rf=new ReplyFindVO();
		rf.setFs("NCS");
		System.out.println(Arrays.deepToString(rf.getFsArr()));
	}
}
