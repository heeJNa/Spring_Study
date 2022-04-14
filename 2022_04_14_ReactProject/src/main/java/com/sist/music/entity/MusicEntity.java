package com.sist.music.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/*
 	보유기술
 	=======
 	프로그램 언어 : Java, HTML5, JavaScript, ...
 	데이터베이스 : Oracle 18c, (MySQL 8.0.28)
 	운영체제 : 리눅스(우분투),윈도우
 	사용기술 : JSP, Ajax, (React),(Redux), (Vue), OpenApi, (AWS), MyBatis, (JPA)
 	프레임워크 : spring5, (spring-boot) => Bold체
 	
 	희망부서
 		SI/SM	==> Java 웹개발 , Java 웹개발(Front-End)
 		솔루션	==> SW개발
 					database관련 개발
 					Java 웹개발 / SW 개발
 	목표 = 사이트 목적 (요구사항
 		 = 개인 목적
 	2. 성과
 	-------------------- 증거 (캡처화면)
 	3. 사이트 주요 기능
 	4. 개인 담당
 	--------------------
 */
@Entity(name="music")
@Setter
@Getter
public class MusicEntity {
	@Id
	private int no;
	private int cno,idcrement;
	private String title,singer,album,state,poster,mkey;
}
