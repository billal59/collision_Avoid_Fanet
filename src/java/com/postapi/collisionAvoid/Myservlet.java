/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.postapi.collisionAvoid;

import com.dbConnectivity.DetectCollision;
import com.dbConnectivity.InsertData;
import general_package.Information;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author billal
 */
public class Myservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("ok");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String latStr = request.getParameter("latitude");
        String langStr = request.getParameter("langitude");
        String IPAdress = request.getParameter("ipaddress");
        String instanceNo =  request.getParameter("instanceNo");
        
        int instanceNumber = Integer.valueOf(instanceNo);
        double lat = Double.valueOf(latStr);
        double lang = Double.valueOf(langStr);

        InsertData insertData = new InsertData();
        boolean insertFlag = false;
        PrintWriter out = response.getWriter();

        DetectCollision detectCollision = new DetectCollision();
        Information information = detectCollision.detectCollision(lat, lang, IPAdress,instanceNumber);
        String command = information.getCommand();
        double distance = information.getDistance();
        
        try {
            if(command.equals("run"))
                insertFlag = insertData.insertDataToDatabase(lat, lang, IPAdress,instanceNumber);
        } catch (SQLException ex) {
            Logger.getLogger(Myservlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONObject json = new JSONObject();

        try { 
            json.put("errorcode", "N");
            json.put("errorMessage", "");
            json.put("command", command); 
            json.put("distance", distance);
            json.put("instanceNo",instanceNumber);
        } catch (JSONException ex) {
            Logger.getLogger(Myservlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(json.toString());
        out.println(json.toString());

    }

}
 