package com.blockchain.biz;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockChainVO {
	String blockHeaderHash;
	BlockHeaderVO blockHeader;
	BlockVO block;
}
