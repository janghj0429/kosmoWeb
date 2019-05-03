package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		String configLocation = "classpath:beans.xml";
		//1.Ioc 컨테이너 생성
		GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(configLocation);
		
		//2.Hello Bean 가져오기
		Hello hello = (Hello)context.getBean("hello");
		hello.print();
		
		context.close();
	}

}
