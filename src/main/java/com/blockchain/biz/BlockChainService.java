package com.blockchain.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class BlockChainService {

	@Autowired
	private BlockChainDAO blockChainDAO;
	@Autowired
	private TransactionQueueDAO promiseDAO;

	public void makeBlock() {
		BlockHeaderVO blockHeaderVO = new BlockHeaderVO();
		BlockVO blockVO = new BlockVO();

		List<PromiseVO> promiseList = promiseDAO.getTransactionList();
		for(PromiseVO promiseVO : promiseList) {
			blockChainDAO.insertPromise(promiseVO);
		}
		promiseDAO.deleteTransactions();


		//block 저장
		//약속내역 블록에 저장
		blockVO.setTransactions(promiseList);
		blockChainDAO.insertBlock(blockVO);

		//block header 저장
		//머클 루트 세팅
		blockHeaderVO.setMerkleRoot(Util.getMerkleRoot(promiseList));
		// 블록 헤더에 이전 블록 해시 저장
		BlockHeaderVO prevBlockHeaderVO = blockChainDAO.getPrevBlockHeader();
		if(prevBlockHeaderVO != null) {
			blockHeaderVO.setPrevBlockHash(Util.getObjectHash(prevBlockHeaderVO));
		}
		//블록 생성 시간 저장
		blockHeaderVO.setTimeStamp(System.currentTimeMillis());
		//블록헤더와 블록을 DB에 저장
		blockChainDAO.insertBlockHeader(blockHeaderVO);

			
	}

	public List<BlockVO> getBlockList() {
		return blockChainDAO.getBlockList();
	}

	public List<BlockHeaderVO> getBlockHeaderList() {
		return blockChainDAO.getBlockHeaderList();
	}

	public List<BlockChainVO> getBlockChainList() {
		return blockChainDAO.getBlockChainList();
	}

}
