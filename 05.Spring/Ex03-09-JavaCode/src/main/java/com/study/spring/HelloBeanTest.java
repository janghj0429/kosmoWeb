package com.study.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
	
		//1.Ioc 컨테이너 생성
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(Config.class);
		
		//2.Hello Bean 가져오기
		Hello helloA = (Hello)context.getBean("hello");
		helloA.print();
		
		//3.Hello Bean 가져오기
		Hello helloB = (Hello)context.getBean("hello1");
		helloB.print();
		
		//3.PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB", Printer.class);
		helloA.setPrinter(printer);
		helloA.print();
		
		//4.싱글톤인지 확인
		System.out.println(helloA == helloB);
		
		context.close();
	}

}
