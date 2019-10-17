package com.blockchain.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TransactionQueueDAO {
	
	private Connection conn=null;
	private PreparedStatement stmt=null;
	private ResultSet rs = null;
	
	private final String TXQUEUE_INSERT="insert into "
			+ "TXQUEUE(SEQ, DATE, LOCATION, FUND, PARTICIPANTS, CONTENT) "
			+ "values((select nvl(max(seq), 0)+1 from TXQUEUE), ?,?,?,?,?)";
	private final String TXQUEUE_GET_COUNT="select count(*) from TXQUEUE";
	private final String TXQUEUE_DELETE="delete from TXQUEUE";
	private final String TXQUEUE_GET_ALL="select * from TXQUEUE";
	
	public void insertTransaction(PromiseVO vo) {
		System.out.println("===> Process insertTransaction() using JDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(TXQUEUE_INSERT);
			stmt.setString(1,  vo.getDate());
			stmt.setString(2,  vo.getLocation());
			stmt.setDouble(3,  vo.getFund());
			stmt.setString(4,  vo.getParticipants());
			stmt.setString(5,  vo.getContent());
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public int getTransactionCnt() {
		// TODO Auto-generated method stub
		System.out.println("===> Process getTransactionCnt() using JDBC");

		int transactionCnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			stmt=conn.prepareStatement(TXQUEUE_GET_COUNT);
			rs = stmt.executeQuery();
			if(rs.next()) {
				transactionCnt =  rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return transactionCnt;
	}

	public void deleteTransactions() {
		System.out.println("===> Process deleteTransactions() using JDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(TXQUEUE_DELETE);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public List<PromiseVO> getTransactionList() {
		System.out.println("===> Process getAllPromises() using JDBC");
		List<PromiseVO> transactionList = new ArrayList<PromiseVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(TXQUEUE_GET_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				PromiseVO promiseVO = new PromiseVO();
				promiseVO.setDate(rs.getString("date"));
				promiseVO.setLocation(rs.getString("location"));
				promiseVO.setFund(rs.getDouble("fund"));
				promiseVO.setParticipants(rs.getString("participants"));
				promiseVO.setContent(rs.getString("content"));
				transactionList.add(promiseVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return transactionList;
	}

}
