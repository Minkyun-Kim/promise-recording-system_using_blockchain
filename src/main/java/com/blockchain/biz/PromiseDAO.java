package com.blockchain.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PromiseDAO {
	
	private Connection conn=null;
	private PreparedStatement stmt=null;
	private ResultSet rs = null;
	
	private final String PROMISE_INSERT="insert into "
			+ "PROMISE(SEQ, DATE, LOCATION, FUND, PARTICIPANTS, CONTENT) "
			+ "values((select nvl(max(seq), 0)+1 from PROMISE), ?,?,?,?,?)";
	private final String PROMISE_GET_COUNT="select count(*) from PROMISE";
	private final String PROMISE_DELETE="delete from PROMISE";
	private final String PROMISE_GET_ALL="select * from PROMISE";
	
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
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public int getPromiseCnt() {
		// TODO Auto-generated method stub
		System.out.println("===> Process getPromiseCnt() using JDBC");

		int promiseCnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			stmt=conn.prepareStatement(PROMISE_GET_COUNT);
			rs = stmt.executeQuery();
			if(rs.next()) {
				promiseCnt =  rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
		return promiseCnt;
	}

	public void deletePromises() {
		System.out.println("===> Process deletePromises() using JDBC");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PROMISE_DELETE);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public List<PromiseVO> getAllPromises() {
		System.out.println("===> Process getAllPromises() using JDBC");
		List<PromiseVO> promiseCollection = new ArrayList<PromiseVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(PROMISE_GET_ALL);
			rs = stmt.executeQuery();
			while(rs.next()) {
				PromiseVO promiseVO = new PromiseVO();
				promiseVO.setDate(rs.getString("date"));
				promiseVO.setLocation(rs.getString("location"));
				promiseVO.setFund(rs.getDouble("fund"));
				promiseVO.setParticipants(rs.getString("participants"));
				promiseVO.setContent(rs.getString("content"));
				promiseCollection.add(promiseVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return promiseCollection;
	}

}
