package com.blockchain.biz;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockVO {
	private int height;
	final static int transactionCount = 4;
	private List<PromiseVO> transactions;

	@Override
	public String toString() {
		return "BlockVO [height: " + height
						+ ", transactionCount: " + transactionCount
						+ ", transactions: {" + transactions + "}]";
	}

}
