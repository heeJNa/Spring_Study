package com.sist.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ~VO, ~BEAN, ~DTO => 데이터를 모아서 관리할 목적 => 한번에 전송
// 단위는 항상 Recode단위 (컬럼 여러개 모아서 관리) => ROW
// 사용자 정의 데이터형 => 구조체 (자바에서는 존재하지 않는다)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private int no,hit;
	private Date regdate;
	private String name,subject,content,pwd,dbday;
}
