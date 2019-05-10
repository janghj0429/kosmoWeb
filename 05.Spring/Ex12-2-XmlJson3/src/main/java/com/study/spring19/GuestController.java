package com.study.spring19;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuestController {
	
	@RequestMapping(value = "/list.json")
	@ResponseBody
	public GuestMessageJSONList listJson() {
		return getMessageJsonList();
	}
	
	private GuestMessageJSONList getMessageJsonList() {
		List<GuestMessage> messages = Arrays.asList(
				new GuestMessage(1, "메시지", new Date()),
				new GuestMessage(2, "메시지2", new Date())
				);
		
		return new GuestMessageJSONList(messages);
	}
}
