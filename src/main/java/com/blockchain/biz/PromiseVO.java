package com.blockchain.biz;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromiseVO {
	String date;
	String location;
	Double fund;
	String participants;
	String content;
	
	@Override
	public String toString() {
		String print = "";
		print =  "PromiseVO [date: " + date + ", location: " + location + ", fund: " + fund + ", name: {" + participants + "}, content: " + content + "]";
		/*
		for(String name : participants) {
			print += name + " ";
		}
		print += "}, content: " + content + "]";
		*/
		return print;
	}
	
	public boolean isInsufficientValues() {
		if(date == null || location == null || fund == null || participants== null || content == null) {
			return true;
		}
		return false;
	}
	
	

}
