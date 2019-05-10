package com.study.spring18;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message-list")
public class GuestMessageXMLList {
	
	@XmlElement(name = "message")
	private List<GuestMessage> messages;
	
	public GuestMessageXMLList() {
		
	}
	
	public GuestMessageXMLList(List<GuestMessage> messages) {
		this.messages = messages;
	}
	
	public List<GuestMessage> getMessage(){
		return messages;
	}

}
