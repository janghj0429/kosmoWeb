package com.study.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		
		//String configLocation = "classpath:beans.xml";
		
		//1.스프링 설정이 클래스패스 루트가 아닌 다른곳에 위치한다
		// 루트를 기준으로 경로 형식을 입력
		//String configLocation = "classpath:config/beans2.xml";
		//= String configLocation = "classpath:/config/beans2.xml";
		
		//2.클래스패스가아닌 파일 시스템에서 설정 파일을 읽어오기
		//String configLocation = "file:src/main/resources/config/beans2.xml";
		
		//3.특정 경로에 있는 모든xml 파일을 설정파일로 사용하고 싶은 경우
		//String configLocation = "classpath:config/beans*.xml";
		
		//4. 1개 이상의 설정파일 경로를 값으로 전달 가능 : 가변인자 형태임
		AbstractApplicationContext context = new GenericXmlApplicationContext(
				"classpath:beans.xml",
				"classpath:config/beans2.xml");
		
		
		//1.Ioc 컨테이너 생성
//		GenericXmlApplicationContext context = 
//				new GenericXmlApplicationContext(configLocation);
		
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
		
		context.close();
	}

}
