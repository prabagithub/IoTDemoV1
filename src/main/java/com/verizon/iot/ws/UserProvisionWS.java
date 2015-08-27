package com.verizon.iot.ws;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.verizon.iot.mongo.MongoDBClient;

@Path("userProvision")
public class UserProvisionWS  {
	@GET
	// @Consumes("text/plain")
	@Produces(MediaType.TEXT_HTML)
	public String persistGadgetRequestG(@QueryParam("userId") int userId, @QueryParam("planId") String planId) {

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("userId", userId);
		job.add("planId", planId);
		
		MongoDBClient.provisionUser(userId, planId); 
		
		return "Congratulations "+userId+"! You have been provisioned: For Plan Id = " + planId;
	}
}