package com.blockchain.web.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Handles requests for the application home page.
 */
public class HomeController implements Controller{
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		return mav;
	}
	
}
