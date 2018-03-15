/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hoang
 */
public class JDBCTest {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection conn = null;
        String sql = "select * from skills";
        String user = "root";
        String pwd = "root";
        String url = "jdbc:mysql://localhost:3306/mysqljdbc";
        String sqlInsert = "insert into skills values(6,'C++')";
        try{
            conn = DriverManager.getConnection(url, user, pwd);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        Statement stm = conn.createStatement();
        //PreparedStatement pstm = conn.prepareStatement(sqlInsert);
        //pstm.executeUpdate();
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString("id") +" "+ rs.getString("name"));
        }
        rs.close();
        stm.close();
        conn.close();
    }
    
}
