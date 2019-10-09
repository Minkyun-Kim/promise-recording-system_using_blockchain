package com.blockchain.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class PromiseDAO {
	
	private Connection conn=null;
	private PreparedStatement stmt=null;
	private ResultSet rs = null;
	
	private final String PROMISE_INSERT="insert into "
			+ "PROMISE(SEQ, DATE, LOCATION, FUND, PARTICIPANTS, CONTENT) "
			+ "values((select nvl(max(seq), 0)+1 from PROMISE), ?,?,?,?,?)";
	
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
	

}
