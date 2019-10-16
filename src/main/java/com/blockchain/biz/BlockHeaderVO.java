package com.blockchain.biz;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockHeaderVO {
	
	private int height;
	private String prevBlockHash;
	private String merkleRoot;
	private long timeStamp;
	private int nonce;
	
	@Override
	public String toString() {
		return "BlockHeaderVO [height: " + height 
						+ ", prevBlockHash: " + prevBlockHash 
						+ ", merkleRoot: " + merkleRoot 
						+ ", timeStamp: " + timeStamp + ", nonce: " + nonce + "]";
		
	}

}
