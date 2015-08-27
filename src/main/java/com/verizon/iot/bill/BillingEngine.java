package com.verizon.iot.bill;

import org.bson.Document;

import com.verizon.iot.mongo.MongoDBClient;;


public class BillingEngine {

	public static final double calculateBill(int userId, String deviceCategory, double incomingDataVolume){
		double currCharges = 0.00;
		String planId = MongoDBClient.fetchUserPlanId(userId);
		Document planDoc = MongoDBClient.fetchPlanDetails(planId);
		double pc =((Document)planDoc.get("details")).getDouble("PlanCharges");
		Document userUsageDoc = MongoDBClient.fetchCurrentUsageDetails(userId, deviceCategory);
		
		if(userUsageDoc == null){
			currCharges = pc;
			return currCharges;
		}
		
		Double currentlyUsedDataVolume = userUsageDoc.getDouble("dataVolume");
		double dvtemp = currentlyUsedDataVolume + incomingDataVolume;
		Double dvToCharge = 0.00;
	
		System.out.println("currentlyUsedDataVolume="+ currentlyUsedDataVolume);
		Double rateForTheDevice = MongoDBClient.getRateMap().get(deviceCategory);
		
		double planThreshold = ((Document)planDoc.get("details")).getDouble(deviceCategory);
		
		if ( currentlyUsedDataVolume >= planThreshold){
			dvToCharge = incomingDataVolume;
		}else if(dvtemp >= planThreshold){
			dvToCharge = dvtemp - planThreshold;
		}
		
		if(rateForTheDevice != null){
			currCharges = dvToCharge * rateForTheDevice;
		}else {
			currCharges = dvToCharge * MongoDBClient.getRateMap().get("Others");
		}
		return currCharges;
	}
}
