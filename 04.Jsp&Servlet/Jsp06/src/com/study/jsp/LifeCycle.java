package com.study.jsp;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
        super();
        System.out.println("생성");
    }

	public void init() throws ServletException {
		System.out.println("init");
	}


	public void destroy() {
		System.out.println("destroy");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		System.out.println("doGet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		System.out.println("doPost");
	}
	
	@PostConstruct
	private void initPostConstruct() {
		System.out.println("생성자 호출 직후 불림");
	}
	
	@PreDestroy
	private void PreDestroy() {
		System.out.println("디스트로이 호출시 먼저 불림");
	}


}
