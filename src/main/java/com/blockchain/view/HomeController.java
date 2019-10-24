package com.blockchain.view;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.blockchain.biz.BlockChainDAO;
import com.blockchain.biz.BlockChainService;
import com.blockchain.biz.BlockChainVO;
import com.blockchain.biz.BlockHeaderVO;
import com.blockchain.biz.BlockVO;
import com.blockchain.biz.TransactionQueueDAO;
import com.blockchain.biz.PromiseService;
import com.blockchain.biz.PromiseVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController{

	//TODO: Change to Command Object
	@Autowired
	private PromiseService promiseService;
	@Autowired
	private BlockChainService blockChainService;
	
	@RequestMapping(value="/")
	public String showHome() {
		System.out.println("showHome() called");
		return "home";
	}
	
	@RequestMapping(value="/makePromise", method=RequestMethod.POST)
	public String makePromise(PromiseVO vo) {
		if(!vo.isInsufficientValues()) {
			promiseService.insertTransaction(vo);
			return "redirect:showBlockChain";
		}
		return "home";
		
	}
	@RequestMapping(value="/showBlockChain", method=RequestMethod.GET)
	public String showBlockChain(Model model) {

		List<BlockChainVO> blockChainList = blockChainService.getBlockChainList();
		Collections.reverse(blockChainList);
		model.addAttribute("blockChainList", blockChainList);

		model.addAttribute("txQueueList", promiseService.getTransactionList());

		return "showBlockChain";
	}
}
