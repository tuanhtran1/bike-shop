package com.shop.bike.utils;

public class DistanceUtils {
	
	/**
	 * Get distance between 2 points
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return the distance (kilometer).
	 *
	 * @date: May 12, 2023
	 * @author tu.tran (devluong@gmail.com)
	 */
	public static double distanceBetween2Points(double lat1, double lng1, double lat2, double lng2) {
		double R = 6371000;
		double dLat = (lat2 - lat1) * (Math.PI / 180);
		double dLon = (lng2 - lng1) * (Math.PI / 180);
		double la1ToRad = lat1 * (Math.PI / 180);
		double la2ToRad = lat2 * (Math.PI / 180);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad)
				* Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return Math.round(d/1000);
	}
}
