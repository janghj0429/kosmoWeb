package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		//1.Ioc 컨테이너 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
		
		//2.Hello Bean 가져오기
		Hello hello = (Hello)context.getBean("hello");
		hello.print();
		
		//3.PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB", Printer.class);
		hello.setPrinter(printer);
		hello.print();
		
		//4.싱글톤인지 확인
		Hello hello2 = context.getBean("hello", Hello.class);
		System.out.println(hello == hello2);
		
		//context.close();
		//클로즈 하려면 Generic 본인 타입으로 생성해야됨
		//부모쪽에는 클로즈가 없다.
	}

}
