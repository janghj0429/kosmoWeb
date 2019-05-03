package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanTest {

	public static void main(String[] args) {
		
		
		String configLocation = "classpath:beans.xml";
		
		//1.Ioc 컨테이너 생성
		ApplicationContext context = new GenericXmlApplicationContext(configLocation);
		
		//2.Hello Bean 가져오기
		Hello hello = (Hello)context.getBean("hello");
		hello.print();
		
		//context.close();
		//클로즈 하려면 Generic 본인 타입으로 생성해야됨
		//부모쪽에는 클로즈가 없다.
	}

}
