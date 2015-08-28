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
	
	static Document smallPlanUser=null;
	static Document smallPlanUserUsage=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		smallPlanUser=new Document("SMALL_PLAN_DOC",SMALL_PLAN_DOC);
		smallPlanUserUsage = new Document("SMALL_USER_USAGE_DOC", SMALL_USER_USAGE_DOC);
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

}
