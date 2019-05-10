package com.study.spring21;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//파일 업로드 폼
	@RequestMapping("/fileForm")
	public String uploadForm() {
		
		return "FileUpload/fileForm";
	}
	
	//파일 업로드 처리
	@RequestMapping("/fileFormOk")
	public String uploadAction() {
		
		return "FileUpload/fileFormOk";
	}
	
}
