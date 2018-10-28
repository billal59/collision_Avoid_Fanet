/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general_package;

import java.util.List;

/**
 *
 * @author Finger
 */
import java.lang.Math;

public class CalculationOfCollision {

    public Information getCommand(double lat, double lang, List<Location> listLocation) {
        Information information = new Information();
        double minDistance = 10000;

        for (Location location : listLocation) {
            double lat1 = location.getLatitude();
            double lon1 = location.getLongitude();
            double distance = getDistanceTwoPoints(lat, lang, lat1, lon1);
            System.out.println(distance);
            if (distance < 0.03) {
                information.setDistance(distance);
                information.setCommand("stop");
                return information;
            } else if (distance < minDistance) {
                minDistance = distance;
            }
        }
        information.setDistance(minDistance);
        information.setCommand("run");
        return information;
    }

    double getDistanceTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

}
