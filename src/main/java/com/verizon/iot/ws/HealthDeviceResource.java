package com.verizon.iot.ws;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.verizon.iot.bill.BillingEngine;
import com.verizon.iot.mongo.MongoDBClient;

@Path("health")
public class HealthDeviceResource {
	@GET
	// @Consumes("text/plain")
	@Produces(MediaType.TEXT_HTML)
	public String persistGadgetRequestG(@QueryParam("userId") long userId, @QueryParam("location") String location,
			@QueryParam("deviceCategory") String deviceCategory, @QueryParam("dataVolume") double dataVolume) {

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("userId", userId);
		job.add("location", location);
		job.add("deviceCategory", deviceCategory);
		job.add("dataVolume", dataVolume);
		
		MongoDBClient.insertIntoDataDumpTable(userId, location, deviceCategory, dataVolume);
		double currCharges = BillingEngine.calculateBill(userId, deviceCategory, dataVolume);    
		
		MongoDBClient.updateBillData(userId, deviceCategory, dataVolume, currCharges);
		String currentBill = MongoDBClient.fetchCurrentBillData(userId);
		return currentBill;
	}
}