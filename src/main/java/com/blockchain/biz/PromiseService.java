package com.blockchain.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromiseService {
	
	@Autowired
	private PromiseDAO promiseDAO;
	@Autowired
	private BlockChainService blockChainService;

	public void insertPromise(PromiseVO vo) {
		promiseDAO.insertPromise(vo);
		
		//promise 큐에 일정 갯수 이상(현재 4개) 쌓이면 블록에 저장
		if(promiseDAO.getPromiseCnt() == BlockVO.transactionCount) {
			blockChainService.makeBlock();
			
		}
	}

}
