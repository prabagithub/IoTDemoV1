package com.verizon.test;

import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.verizon.iot.bill.BillingEngine;

public class BillingEngineTest {

	static final String SMALL_PLAN_DOC="{{_id=55e00a92d5e8182f541aeed7,planId=SMALL, details=Document{{Gadgets=5.0, HealthDevices=10.0, Appliances=20.0,Others=30.0, PlanCharges=19.99}}}}";
	static final String SMALL_USER_USAGE_DOC="{{_id=55e015b159e9a720ebba253e, deviceCategory=Gadgets, userId=1236, currentBillAmt=19.99, dataVolume=2.0}}";
	
	static final String MEDIUM_PLAN_DOC="{{_id=55e00a92d5e8182f541aeed8,planId=MEDIUM, details=Document{{Gadgets=10.0, HealthDevices=20.0, Appliances=30.0,Others=40.0, PlanCharges=29.99}}}}";
	static final String MEDIUM_USER_USAGE_DOC="{{_id=55e015b159e9a720ebba254e, deviceCategory=Gadgets, userId=1237, currentBillAmt=29.99, dataVolume=2.0}}";

	static final String LARGE_PLAN_DOC="{{_id=55e00a92d5e8182f531aeed7,planId=LARGE, details=Document{{Gadgets=20.0, HealthDevices=30.0, Appliances=40.0,Others=50.0, PlanCharges=49.99}}}}";
	static final String LARGE_USER_USAGE_DOC="{{_id=55e015b159e9a620ebba253e, deviceCategory=Gadgets, userId=1238, currentBillAmt=49.99, dataVolume=2.0}}";

	
	static Document smallPlanUser=null;
	static Document smallPlanUserUsage=null;
	static Document mediumplanUser=null;
	static Document mediumplanUserUsage=null;
	static Document largeplanUser=null;
	static Document largeplanUserUsage=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		smallPlanUser=new Document("SMALL_PLAN_DOC",SMALL_PLAN_DOC);
		smallPlanUserUsage = new Document("SMALL_USER_USAGE_DOC", SMALL_USER_USAGE_DOC);
		
		mediumplanUser=new Document("MEDIUM_PLAN_DOC",MEDIUM_PLAN_DOC);
		mediumplanUserUsage=new Document("MEDIUM_USER_USAGE_DOC",MEDIUM_USER_USAGE_DOC);
						
		largeplanUser=new Document("LARGE_PLAN_DOC",LARGE_PLAN_DOC);
		largeplanUserUsage=new Document("LARGE_USER_USAGE_DOC",LARGE_USER_USAGE_DOC);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSmallUser() {
		double currCharges = BillingEngine.calculateBill(1236, "Gadgets", 1);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(true);			
		}
	}
	
	@Test
	public void testSmallUser1() {
		double currCharges = BillingEngine.calculateBill(1236, "Gadgets", 123);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(false);			
		}
	}
	
	@Test
	public void testmediumUser() {
		double currCharges = BillingEngine.calculateBill(1237, "HealthDevices", 8);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(false);			
		}
	}
	@Test
	public void testmediumUser1() {
		double currCharges = BillingEngine.calculateBill(1237, "HealthDevices", 80);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(false);			
		}
	}
	@Test
	public void testbigUser() {
		double currCharges = BillingEngine.calculateBill(1237, "Appliances", 30);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(false);			
		}
	}
	@Test
	public void testbigUser1() {
		double currCharges = BillingEngine.calculateBill(1237, "Appliances", 90);
		
		System.out.println("currCharges = " + currCharges);
		
		if(currCharges == 0){
			assertTrue(false);			
		}
	}
	
	

}
