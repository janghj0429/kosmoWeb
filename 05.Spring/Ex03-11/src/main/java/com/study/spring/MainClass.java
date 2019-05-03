package com.study.spring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		ctx.load("classpath:beans.xml");
		System.out.println("aaaa");
		ctx.refresh();
		System.out.println("bbbb");
		
		ctx.close();
		System.out.println("cccc");
	}

}
