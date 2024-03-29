package com.blockchain.biz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

    public static Connection getConnection(){
        try{
            Class.forName("org.h2.Driver");
            Connection conn =  DriverManager.getConnection("jdbc:h2:~/test","sa", "");
            return conn;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void close(PreparedStatement stmt, Connection conn){
        if(stmt != null){
            try{
                if(!stmt.isClosed()){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                stmt = null;
            }
        }
        if(conn != null){
            try{
                if(!conn.isClosed())
                    conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn){
        if(rs != null){
            try{
                if(rs.isClosed())
                    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                rs = null;
            }
        }
        if(stmt != null){
            try{
                if(!stmt.isClosed()){
                    stmt.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                stmt = null;
            }
        }
        if(conn != null){
            try{
                if(!conn.isClosed())
                    conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }
    }
}
