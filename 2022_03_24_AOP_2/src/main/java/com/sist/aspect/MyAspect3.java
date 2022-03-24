package com.sist.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect3 {
	// log
	@Around("execution(* com.sist.dao.MyDAO3.*(..))")
	public Object around(ProceedingJoinPoint jp) {
		Object obj = null;
		try {
		long start=System.currentTimeMillis(); //시작 시간
		// 메소드 호출
		obj=jp.proceed();
		long end=System.currentTimeMillis(); // 종료 시간
		System.out.println("사용자가 호출한 메소드: "+jp.getSignature());
		System.out.println("수행 시간: "+(end-start));
		}catch(Throwable t) {
			t.printStackTrace();
		}
		return obj;
	}
}
