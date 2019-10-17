package com.blockchain.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlockChainDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt=null;
	private PreparedStatement stmt2=null;
	private ResultSet rs = null;
	private ResultSet rs2 = null;
	
	@Autowired
	private TransactionQueueDAO promiseDAO;
	
	private final String BLOCK_INSERT="insert into BLOCK(height, txCount, tx1, tx2, tx3, tx4)"
										+ "values((select nvl(max(height), 0)+1 from BLOCK), ?,?,?,?,?) ";
	private final String BLOCK_GET_ALL = "select * from block";
	private final String BLOCKHEADER_GET_PREV_BLOCKHEADER = "select * from BLOCKHEADER where height=(select max(height) from BLOCKHEADER)";
	private final String BLOCKHEADER_INSERT="insert into BLOCKHEADER(height, prevBlockHash, merkleRoot, timestamp, nonce)"
											+ "values((select nvl(max(height), 0)+1 from BLOCKHEADER), ?,?,?,?)";
	private final String BLOCKHEADER_GET_ALL = "select * from blockheader";
	private final String PROMISE_INSERT="insert into "
										+ "promise(seq, date, location, fund, participants, content) "
										+ "values((select nvl(max(seq), 0)+1 from promise), ?,?,?,?,?)";
	private final String PROMISE_GET_SEQ="select seq from promise where date=? and location = ? and fund = ? and participants = ? and content = ?";
	private final String PROMISE_GET_BY_SEQ="select * from promise where seq = ?";

	public void insertBlock(BlockVO vo) {
		System.out.println("===> Process insertBlock() using JDBC");
		int[] seqList = new int[4];
		try {
			conn = JDBCUtil.getConnection();
			for(int i = 0; i < BlockVO.transactionCount; i++) {
				PromiseVO promiseVO = vo.getTransactions().get(i);
				stmt=conn.prepareStatement(PROMISE_GET_SEQ);
				stmt.setString(1, promiseVO.getDate());
				stmt.setString(2, promiseVO.getLocation());
				stmt.setDouble(3, promiseVO.getFund());
				stmt.setString(4, promiseVO.getParticipants());
				stmt.setString(5, promiseVO.getContent());
				rs = stmt.executeQuery();
				if(rs.next()) {
					seqList[i] = rs.getInt("seq");
				}
				else {
					System.err.println("promise not found");
				}
			}
			stmt = conn.prepareStatement(BLOCK_INSERT);
			stmt.setLong(1,  BlockVO.transactionCount);
			for(int i = 0; i < BlockVO.transactionCount; i++) {
				stmt.setInt(i+2, seqList[i]);
			}
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

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
	
	public void insertPromise(PromiseVO vo) {
		System.out.println("===> Process insertPromise() using JDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PROMISE_INSERT);
			stmt.setString(1,  vo.getDate());
			stmt.setString(2,  vo.getLocation());
			stmt.setDouble(3,  vo.getFund());
			stmt.setString(4,  vo.getParticipants());
			stmt.setString(5,  vo.getContent());
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public List<BlockVO> getBlockList() {
		System.out.println("===> Process getBlockList() using JDBC");
		List<BlockVO> blockList = new ArrayList<BlockVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCK_GET_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlockVO blockVO = new BlockVO();
				blockVO.setHeight(rs.getInt("height"));

				int[] txSeqList = new int[BlockVO.transactionCount];
				txSeqList[0] = rs.getInt("tx1");
				txSeqList[1] = rs.getInt("tx2");
				txSeqList[2] = rs.getInt("tx3");
				txSeqList[3] = rs.getInt("tx4");
				List<PromiseVO> txList = this.getPromiseBySeq(txSeqList);
				blockVO.setTransactions(txList);

				blockList.add(blockVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.close(rs,  stmt, conn);
		}
		return blockList;
	}

	public List<BlockHeaderVO> getBlockHeaderList() {
		System.out.println("===> Process getBlockHeaderList() using JDBC");
		List<BlockHeaderVO> blockHeaderList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOCKHEADER_GET_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlockHeaderVO blockHeaderVO = new BlockHeaderVO();
				blockHeaderVO.setHeight(rs.getInt("height"));
				blockHeaderVO.setPrevBlockHash(rs.getString("prevBlockHash"));
				blockHeaderVO.setMerkleRoot(rs.getString("merkleRoot"));
				blockHeaderVO.setTimeStamp(rs.getLong("timestamp"));
				blockHeaderVO.setNonce(rs.getInt("nonce"));
				blockHeaderList.add(blockHeaderVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blockHeaderList;
	}

	private List<PromiseVO> getPromiseBySeq(int[] txSeqList) {
		List<PromiseVO> promiseList = new ArrayList<>();
		try{
			stmt2 = conn.prepareStatement(PROMISE_GET_BY_SEQ);
			for(int seq : txSeqList) {
				stmt2.setInt(1,  seq);
				rs2 = stmt2.executeQuery();
				if(rs2.next()) {
					PromiseVO promiseVO = new PromiseVO();
					promiseVO.setDate(rs2.getString("date"));
					promiseVO.setLocation(rs2.getString("location"));
					promiseVO.setFund(rs2.getDouble("fund"));
					promiseVO.setParticipants(rs2.getString("participants"));
					promiseVO.setContent(rs2.getString("content"));
					promiseList.add(promiseVO);
				}
				else {
					System.err.println("Can not find promise by seq(" + seq + ")");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs2,  stmt2, null);
		}
		return promiseList;
	}

}
