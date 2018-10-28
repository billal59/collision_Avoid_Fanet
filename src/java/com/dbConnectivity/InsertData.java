/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbConnectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException; 

/**
 *
 * @author billal
 */
public class InsertData {

    public boolean insertDataToDatabase(double lat, double lang, String IPAddress,int instanceNumber) throws SQLException {
        DBConnectivity dbConnectivity = new DBConnectivity();
        Connection connection = dbConnectivity.getConnection();
        String insertTableSQL = "insert into collision (col_id,lat,lon,IPAddress,"
                + "time_of_plane,instanceno)  values("
                + " col_sequence.nextval ,?, ?,? , sysdate,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
        preparedStatement.setDouble(1, lat);
        preparedStatement.setDouble(2, lang);
        preparedStatement.setString(3, IPAddress);
        preparedStatement.setInt(4, instanceNumber);
        
        int value = preparedStatement.executeUpdate();
        if (value > 0) {
            dbConnectivity.releaseConnection(connection);
            return true;
        } else {
            dbConnectivity.releaseConnection(connection);
            return false;
        }

    }

    public static void main(String[] args) throws SQLException {
        InsertData insertData = new InsertData();
        insertData.insertDataToDatabase(22, 67, "10.11.201.170",1);
    }

}
