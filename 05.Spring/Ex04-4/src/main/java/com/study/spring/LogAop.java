package com.study.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
	//@Pointcut("execution(public void get*(..))")		//public void인 모든 get 메서드
	//@Pointcut("execution(* com.study.spring.*.*())")	//com.study.spring 패키지에 파라미터가 없는 모든 메서드
	//@Pointcut("execution(* com.study.spring..*.*())")	//com.study.spring 패키지 & com.study.spring하위
														//패키지에 파라미터가 없는 모든 메서드
	//@Pointcut("execution(*com.study.spring.Worker.*))")	//com.study.spring.Woker 안의 모든 메서드
	
	//@Pointcut(within(com.study.spring.*)")			//com.study.spring 패키지 안에 잇는 모든 메서드
	//@Pointcut(within(com.study.spring..*)")			//com.study.spring 패키지 및 하위 패키지 안에
														//있는 모든 메서드
	//@Pointcut(within(com.study.spring.Worker)")		//com.study.spring.Worker안의 모든 메서드
	
	//@Pointcut("bean(student)")						student 빈에만 적용
	
	
	
	//@Pointcut("within(com.study.spring.*)")
	@Pointcut("bean(*ker)")	//~ker 로 끝나는 빈에만 적용
	private void pointcutMethod() {
		
	}

	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			
			System.out.println(signatureStr + " is finished");
			System.out.println(signatureStr + " 경과시간 : " + (et-st));
		}
	}
	
	@Before("within(com.study.spring.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
}
