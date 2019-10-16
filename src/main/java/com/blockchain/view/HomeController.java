package com.blockchain.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blockchain.biz.PromiseService;
import com.blockchain.biz.PromiseVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{
	
	@Autowired
	private PromiseService promiseService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/")
	public String showHome() {
		System.out.println("showHome() called");
		return "home";
	}
	
	@RequestMapping(value="/makePromise", method=RequestMethod.POST)
	public String makePromise(PromiseVO vo) {
		promiseService.insertPromise(vo);
		return "home";
		
	}
	
}
