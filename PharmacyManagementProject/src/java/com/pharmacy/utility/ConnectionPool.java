
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LENOVO
 */
public class ConnectionPool {
    static Connection conn;
    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy","root","E3/343arera");
             System.out.println("success");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
        
    }
    public static void main(String[] args){
        connectDB();
    }

    public static Connection connectDb() {
        return connectDB();
    }
    
public static void close(Connection conn, java.sql.PreparedStatement ps, java.sql.ResultSet rs) {
    try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (conn != null) conn.close();
    } catch (SQLException ex) {
        Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    

}
