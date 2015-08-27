package com.verizon.iot.ws;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.verizon.iot.mongo.MongoDBClient;

@Path("planDetails")
public class PlanDetailsWS  {
	@GET
	// @Consumes("text/plain")
	@Produces(MediaType.TEXT_HTML)
	public String fetchCurrentBillAmount() {
		
		JsonArray jarry = MongoDBClient.fetchPlanDetails();
		
		return jarry.toString();
		
	}
}
