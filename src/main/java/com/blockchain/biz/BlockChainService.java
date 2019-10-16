package com.blockchain.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockChainService {

	@Autowired
	private BlockChainDAO blockChainDAO;
	@Autowired
	private PromiseDAO promiseDAO;

	public void makeBlock() {
		BlockHeaderVO blockHeaderVO = new BlockHeaderVO();
		BlockVO blockVO = new BlockVO();

		List<PromiseVO> promiseCollection = promiseDAO.getAllPromises();
		promiseDAO.deletePromises();

		//머클 루트 세팅
		blockHeaderVO.setMerkleRoot(Util.getMerkleRoot(promiseCollection));

		//약속내역 블록에 저장
		blockVO.setTransactions(promiseCollection);

		// 블록 헤더에 이전 블록 해시 저장
		BlockHeaderVO prevBlockHeaderVO = blockChainDAO.getPrevBlockHeader();
		if(prevBlockHeaderVO != null) {
			blockHeaderVO.setPrevBlockHash(Util.getObjectHash(prevBlockHeaderVO));
		}
		
		//블록 생성 시간 저장
		blockHeaderVO.setTimeStamp(System.currentTimeMillis());

		//블록헤더와 블록을 DB에 저장
		blockChainDAO.insertBlock(blockVO);
		blockChainDAO.insertBlockHeader(blockHeaderVO);

			
	}

}
