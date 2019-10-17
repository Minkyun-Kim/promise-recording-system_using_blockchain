package com.blockchain.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class PromiseService {
	
	@Autowired
	private TransactionQueueDAO promiseDAO;
	@Autowired
	private BlockChainService blockChainService;

	public void insertTransaction(PromiseVO vo) {
		promiseDAO.insertTransaction(vo);
		
		//promise 큐에 일정 갯수 이상(현재 4개) 쌓이면 블록에 저장
		if(promiseDAO.getTransactionCnt() == BlockVO.transactionCount) {
			blockChainService.makeBlock();
			
		}
	}
	
	public List<PromiseVO> getTransactionList(){
		return promiseDAO.getTransactionList();
	}

}
