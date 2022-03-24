package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
// 공통 모듈
@Aspect // 메모리할당 x
@Component
public class ReplyBoardAspect {
	@Around("execution(* com.sist.web.ReplyBoardController.*(..))")
	public Object around(ProceedingJoinPoint jp) {
		Object obj=null;
		try {
			// 로그 파일
			System.out.println("-------------------------------------");
			System.out.println(jp.getSignature()+"수행전...");
			long start=System.currentTimeMillis();
			obj=jp.proceed(); // 메소드가 실제 수행
			long end=System.currentTimeMillis();
			System.out.println(jp.getSignature()+"수행 완료...");
			System.out.println("수행 시간: "+(end-start)+"ms");
			System.out.println("-------------------------------------");
			
		}catch (Throwable t) {
			t.printStackTrace();
		}
		return obj;
	}
	@AfterReturning(value="execution(* com.sist.web.ReplyBoardController.*(..))"
			,returning="obj")
	public void afterReturning(Object obj) {
		System.out.println("사용자가 요청한 사이트 이동: "+obj);
	}
}
