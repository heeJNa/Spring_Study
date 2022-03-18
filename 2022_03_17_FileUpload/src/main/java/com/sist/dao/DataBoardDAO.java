package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
 	메모리 할당을 하는 요청 어노테이션
 	-----------
 	1. 분류 (기능)
 		일반 클래스 : Component
 		DAO 클래스 : Repository
 		Model 클래스 : Controller, RestController
 		BI 클래스 : Service
 		자바 환경 설정 : Configuration
 		예외 처리 : ControllerAdvice
 */
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class DataBoardDAO {
	// 1. 스프링에서 구현된 Mapper의 주소값을 얻어 온다
	@Autowired // 자동 주입 (스프링에서 찾아서 주소값을 주입)
	private DataBoardMapper mapper;
	
	// 1. 게시물 목록
	public List<DataBoardVO> databoardListData(Map map){
		return mapper.databoardListData(map);
	}
	
	// 2. 게시물 총 페이지
	public int databoardTotalPage() {
		return mapper.databoardTotalPage();
	}
	// 3. 글쓰기
	public void databoardInsert(DataBoardVO vo) {
		mapper.databoardInsert(vo);
	}
	// 4. 상세보기
	public DataBoardVO databoardDetailData(int no) {
		// 조회수 증가
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	// 5. 번호 출력 (일괄적으로)
	public int databoardRowCount() {
		return mapper.databoardRowCount();
	}
	// 6. 수정 읽기
	public DataBoardVO databoardUpdateData(int no) {
		return mapper.databoardDetailData(no); // 필요하면 재사용이 가능
	}
	// 6-1. 수정하기
	public String databoardGetPassword(int no) {
		return mapper.databoardGetPassword(no);
	}
	public void databoardUpdate(DataBoardVO vo) {
		mapper.databoardUpdate(vo);
	}
	// 7. 삭제하기
	public void databoardDelete(int no) {
		mapper.databoardDelete(no);
	}
	// 8. 찾기 => 동적query
	// ${}, #{} 차이점
	// Procedure 처리 방법
	public List<DataBoardVO> databoardFindData(Map map){
		return mapper.databoardFindData(map);
	}
	
	
	
}
