package com.blockchain.biz;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockVO {
	private int height;
	private final int transactionCount = 4;
	private String[] transactions;

	@Override
	public String toString() {
		return "BlockVO [height: " + height
						+ ", transactionCount: " + transactionCount
						+ ", transactions: {" + transactions + "}]";
	}
}
