/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbConnectivity;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author billal
 */
public class DBConnectivity {
    public Connection getConnection(){
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "biotpl", "biotpl");
            return con;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
    
    public void releaseConnection(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DBConnectivity dbConnectivity = new DBConnectivity();
        Connection conn = dbConnectivity.getConnection();
        System.out.println(conn);
        dbConnectivity.releaseConnection(conn);
    }
}
