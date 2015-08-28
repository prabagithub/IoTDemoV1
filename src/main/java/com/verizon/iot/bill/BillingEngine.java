package com.verizon.iot.bill;

import org.bson.Document;

import com.verizon.iot.mongo.MongoDBClient;;

public class BillingEngine {

	public static final double calculateBill(long userId, String deviceCategory, double incomingDataVolume) {
		
		String planId = getPlanId(userId);
		Document planDoc = getPlanDetails(planId);
		Document userUsageDoc = getCurrentUsageDetails(userId, deviceCategory);

		return calculate(planDoc, userUsageDoc, deviceCategory, incomingDataVolume);
	}

	private static String getPlanId(long userId) {

		String planId = MongoDBClient.fetchUserPlanId(userId);
		return planId;

	}

	private static Document getPlanDetails(String planId) {
		Document planDoc = MongoDBClient.fetchPlanDetails(planId);
		return planDoc;

	}

	private static Document getCurrentUsageDetails(long userId, String deviceCategory) {
		Document userUsageDoc = MongoDBClient.fetchCurrentUsageDetails(userId, deviceCategory);
		return userUsageDoc;

	}

	protected static double calculate(Document planDoc, Document userUsageDoc, String deviceCategory, double incomingDataVolume) {
		double currCharges = 0.00;
		Double currentlyUsedDataVolume = 0.00;
		if (userUsageDoc != null) {
			currentlyUsedDataVolume = userUsageDoc.getDouble("dataVolume");
		}
		double dvtemp = currentlyUsedDataVolume + incomingDataVolume;
		Double dvToCharge = 0.00;

		Double rateForTheDevice = MongoDBClient.getRateMap().get(deviceCategory);

		double planThreshold = ((Document) planDoc.get("details")).getDouble(deviceCategory);

		if (currentlyUsedDataVolume >= planThreshold) {
			dvToCharge = incomingDataVolume;
		} else if (dvtemp >= planThreshold) {
			dvToCharge = dvtemp - planThreshold;
		}

		if (rateForTheDevice != null) {
			currCharges = dvToCharge * rateForTheDevice;
		} else {
			currCharges = dvToCharge * MongoDBClient.getRateMap().get("Others");
		}
		
		
		return currCharges;

	}
}
