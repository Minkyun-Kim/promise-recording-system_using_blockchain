package com.blockchain.view;

import java.text.DateFormat;
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
		promiseService.insertTransaction(vo);
		return "home";
//		return "redirect:/showBlockChain";
		
	}
	@RequestMapping(value="/showBlockChain", method=RequestMethod.GET)
	public String showBlockChain(Model model) {
		
		model.addAttribute("blockList", blockChainService.getBlockList());
		model.addAttribute("blockHeaderList", blockChainService.getBlockHeaderList());
		model.addAttribute("txQueueList", promiseService.getTransactionList());

		List<BlockVO> blockList = blockChainService.getBlockList();
		List<BlockHeaderVO> blockHeaderList = blockChainService.getBlockHeaderList();
		List<PromiseVO> promiseList = promiseService.getTransactionList();
		for(BlockVO blockVO: blockList) {
			System.out.println(blockVO);
		}
		for(BlockHeaderVO blockHeaderVO: blockHeaderList) {
			System.out.println(blockHeaderVO);
		}
		for(PromiseVO promiseVO : promiseList) {
			System.out.println(promiseVO);
		}

		return "showBlockChain";
		
	}
	
}
