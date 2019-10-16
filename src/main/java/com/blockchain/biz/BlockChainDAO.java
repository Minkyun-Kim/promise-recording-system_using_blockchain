package com.blockchain.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class BlockChainDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt=null;
	private ResultSet rs = null;
	
	private final String BLOCK_INSERT="insert into BLOCK(height, transactionCount, tx1, tx2, tx3, tx4)"
										+ "values((select nvl(max(height), 0)+1 from BLOCK), ?,?,?,?,?) ";
	//private final String BLOCK_GET_PREV_BLOCK = "select * from BLOCK where height = (select max(height) from BLOCK)";
	private final String BLOCKHEADER_GET_PREV_BLOCKHEADER = "select * from BLOCKHEADER where height=(select max(height) from BLOCKHEADER)";
	private final String BLOCKHEADER_INSERT="insert into BLOCKHEADER(height, prevBlockHash, merkleRoot, timestamp, nonce)"
											+ "values((select nvl(max(height), 0)+1 from BLOCKHEADER), ?,?,?,?)";
	
	public void insertBlock(BlockVO vo) {
		System.out.println("===> Process insertBlock() using JDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCK_INSERT);
			stmt.setLong(1,  BlockVO.transactionCount);
			for(int i = 0; i < BlockVO.transactionCount; i++) {
				stmt.setString(i+2, vo.getTransactions().get(i).toString());
			}
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	/*
	public BlockVO getPrevBlock() {
		System.out.println("===> Process getPrevBlock() using JDBC");
		BlockVO block = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCK_GET_PREV_BLOCK);
			rs = stmt.executeQuery();
			if(rs.next()) {
				block = new BlockVO();
				block.setHeight(rs.getInt("height"));
				
				for(int i = 0; i < 4; i++)){
					
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return block;
		
		
	}
	*/
	public BlockHeaderVO getPrevBlockHeader() {
		System.out.println("===> Process getPrevBlockHeader() using JDBC");
		BlockHeaderVO blockHeaderVO = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCKHEADER_GET_PREV_BLOCKHEADER);
			rs = stmt.executeQuery();
			if(rs.next()) {
				blockHeaderVO = new BlockHeaderVO();
				blockHeaderVO.setHeight(rs.getInt("height"));
				blockHeaderVO.setPrevBlockHash(rs.getString("prevBlockHash"));
				blockHeaderVO.setMerkleRoot(rs.getString("merkleRoot"));
				blockHeaderVO.setTimeStamp(rs.getLong("timeStamp"));
				blockHeaderVO.setNonce(rs.getInt("nonce"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,  stmt, conn);
		}
		return blockHeaderVO;
	}
	
	public void insertBlockHeader(BlockHeaderVO vo) {
		System.out.println("===> Process insertBlockHeader() using jDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCKHEADER_INSERT);
			stmt.setString(1,  vo.getPrevBlockHash());
			stmt.setString(2,  vo.getMerkleRoot());
			stmt.setLong(3,  vo.getTimeStamp());
			stmt.setInt(4,  vo.getNonce());
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}
