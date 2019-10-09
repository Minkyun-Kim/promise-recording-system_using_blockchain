package com.blockchain.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromiseService {
	
	@Autowired
	private PromiseDAO promiseDAO;

	public void insertPromise(PromiseVO vo) {
		promiseDAO.insertPromise(vo);
	}

}
