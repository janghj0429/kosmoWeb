package com.study.springboot;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class ContentValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0);	//검증할 객체의 클래스타입정보
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ContentDto dto = (ContentDto)obj;
		
//		String sWriter = dto.getWriter();
//		if(sWriter == null || sWriter.trim().isEmpty()) {
//			System.out.println("Writer is null or empty");
//			errors.rejectValue("writer", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty.");
		String sWriter = dto.getWriter();
		if(sWriter.length() < 3) {
			errors.rejectValue("writer", "writer is too short");
		}
		
//		String sContent = dto.getContent();
//		if(sContent == null || sContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty");
//			errors.rejectValue("content", "trouble");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty.");
	}
}
