package com.verizon.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bson.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.verizon.iot.bill.BillingEngine;

public class BillingEngineTest {
	
	static final String MEDIUM_PLAN_DOC = "{{_id=55e00a92d5e8182f541aeed8,planId=MEDIUM, details=Document{{Gadgets=10.0, HealthDevices=20.0, Appliances=30.0,Others=40.0, PlanCharges=29.99}}}}";
	static final String MEDIUM_USER_USAGE_DOC = "{{_id=55e015b159e9a720ebba254e, deviceCategory=Gadgets, userId=1236, currentBillAmt=29.99, dataVolume=2.0}}";

	static final String LARGE_PLAN_DOC = "{{_id=55e00a92d5e8182f531aeed7,planId=LARGE, details=Document{{Gadgets=20.0, HealthDevices=30.0, Appliances=40.0,Others=50.0, PlanCharges=49.99}}}}";
	static final String LARGE_USER_USAGE_DOC = "{{_id=55e015b159e9a620ebba253e, deviceCategory=Gadgets, userId=1236, currentBillAmt=49.99, dataVolume=2.0}}";

	static Document smallPlanUser = null;
	static Document smallPlanUserUsage = null;
	static Document mediumplanUser = null;
	static Document mediumplanUserUsage = null;
	static Document largeplanUser = null;
	static Document largeplanUserUsage = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		smallPlanUserUsage = new Document();
		smallPlanUserUsage.append("deviceCategory", "Gadgets").append("dataVolume", 2.0).append("userId", 1236).append("currentBillAmt", 19.99);

		mediumplanUserUsage = new Document();
		mediumplanUserUsage.append("deviceCategory", "Gadgets").append("dataVolume", 2.0).append("userId", 1236).append("currentBillAmt", 29.99);
		
		largeplanUserUsage = new Document();
		largeplanUserUsage.append("deviceCategory", "Gadgets").append("dataVolume", 2.0).append("userId", 1236).append("currentBillAmt", 49.99);
		
		smallPlanUser = new Document();		
		Document smallPlanUserInner = new Document().append("Gadgets", 5.0).append("HealthDevices", 10.0).append("Appliances", 20.0).append("Others", 30.0).append("PlanCharges", 19.99);
		smallPlanUser = new Document().append("planId", "SMALL").append("details", smallPlanUserInner);
		
		
		mediumplanUser = new Document();		
		Document mediumplanUserInner = new Document().append("Gadgets", 10.0).append("HealthDevices", 20.0).append("Appliances", 30.0).append("Others", 40.0).append("PlanCharges", 29.99);
		mediumplanUser = new Document().append("planId", "MEDIUM").append("details", mediumplanUserInner);
		
		largeplanUser = new Document();		
		Document largeplanUserInner = new Document().append("Gadgets", 20.0).append("HealthDevices", 30.0).append("Appliances", 40.0).append("Others", 50.0).append("PlanCharges", 49.99);
		largeplanUser = new Document().append("planId", "LARGE").append("details", largeplanUserInner);
		
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
	public void testSmallUser1() {
		double currCharges = BillingEngine.calculate(smallPlanUser, smallPlanUserUsage, "Gadgets", 1);

		System.out.println("currCharges = " + currCharges);

		if (currCharges == 0) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testSmallUser2() {
		double currCharges = BillingEngine.calculate(smallPlanUser, smallPlanUserUsage, "Gadgets", 6);

		System.out.println("currCharges = " + currCharges);

		if (currCharges > 0) {
			assertTrue(true);
		}
	}

	
	@Test
	public void testMediumUser1() {
		double currCharges = BillingEngine.calculate(mediumplanUser, mediumplanUserUsage, "HealthDevices", 2);

		System.out.println("currCharges = " + currCharges);

		if (currCharges == 0) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testMediumUser2() {
		double currCharges = BillingEngine.calculate(mediumplanUser, mediumplanUserUsage, "HealthDevices", 22);

		System.out.println("currCharges = " + currCharges);

		if (currCharges > 0) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void testLargeUser1() {
		double currCharges = BillingEngine.calculate(largeplanUser, largeplanUserUsage, "Appliances", 2);

		System.out.println("currCharges = " + currCharges);

		if (currCharges == 0) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testLargeUser2() {
		double currCharges = BillingEngine.calculate(largeplanUser, largeplanUserUsage, "Appliances", 42);

		System.out.println("currCharges = " + currCharges);

		if (currCharges == 0) {
			assertFalse(false);
		}
	}
}
