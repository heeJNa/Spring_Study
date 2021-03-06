package com.sist.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.NatureMapper;
import com.sist.vo.NatureVO;

// Mapper => DAO => MainClass
/*
 	스프링에서 패키지 단위로 클래스 등록
 	=> 메모리 할당 요청
 		클래스 위에 Annotation을 올려서 구분
 			1) Component : 일반 클래스(웹 크롤링, XML파싱, 데이터 분석)
 							데이터 분석 : AI, data.go.kr (데이터 수집)
 			2) Repository :  저장소(DAO=데이터베이스 관련)
 			3) Service : BI(DAO 여러개를 통합해서 사용)
 			------------------------------------------------------------------------------------ 웹
 			4) Controller : Model,DTO (요청을 받아서 요청처리 후에 결과값을 전송)
 							=> 화면이동 (파일명을 전송)
 			5) RestController :  Model,DTO (요청을 받아서 요청처리 후에 결과값을 전송)
 								=> 데이터 전송 (Ajax, VueJS, ReactJS) => JSON
 			6) ControllerAdvice : 통합 예외처리
 			-------------------
 	=> 메모리 할당이 없는 것
 		
 */
@Repository
public class NatureDAO {
	@Autowired
	private NatureMapper mapper;
	public List<NatureVO> natureListData(){
		return mapper.natureListData();
	}
}
