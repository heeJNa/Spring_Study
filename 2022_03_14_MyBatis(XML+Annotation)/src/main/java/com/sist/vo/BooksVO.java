package com.sist.vo;

import lombok.Getter;
import lombok.Setter;
// jar을 싱행 ==> jar이 있는 위치로 이동
// java -jar 파일명.jar
@Getter // 프로그램 데이터 출력
@Setter // MyBatis에 사용
public class BooksVO {
	private String title;
	private String price;
}
