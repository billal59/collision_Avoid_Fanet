/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbConnectivity;

import general_package.CalculationOfCollision;
import general_package.Information;
import general_package.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Finger
 */
public class DetectCollision {

    public Information  detectCollision(double lat, double lang, String IPAdress,int instanceNumber) {

        List<Location> listLocation = getAllData(instanceNumber);
        CalculationOfCollision  calculationOfCollision = new CalculationOfCollision();
        return calculationOfCollision.getCommand(lat,lang,listLocation);
    }

    public List<Location> getAllData(int instanceNumber) {
        DBConnectivity dbConnectivity = new DBConnectivity();
        Connection conn = dbConnectivity.getConnection();

        String selectSQL = "select LAT,LON from BIOTPL.COLLISION "
                + " where TIME_OF_PLANE > sysdate - (1/24*60*60)"
                + " and INSTANCENO <> "+instanceNumber;
        
        List<Location> locationList = new ArrayList<Location>();
        

        try {
            
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            while (rs.next()) {
                double latitude = rs.getDouble("LAT");
                double longitude = rs.getDouble("LON");
                Location location = new Location();
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                locationList.add(location);                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetectCollision.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbConnectivity.releaseConnection(conn);
        return locationList;
    }

}
