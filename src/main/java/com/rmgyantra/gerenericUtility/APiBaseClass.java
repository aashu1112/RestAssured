package com.rmgyantra.gerenericUtility;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;

public class APiBaseClass {
	public DataBaseUtilities dbLib = new DataBaseUtilities();
	
	@BeforeSuite
	public void configBeforeSuite() {
		baseURI = "http://localhost:8084";
		dbLib.connectDB();
	}

	
	@AfterSuite
	public void configAfterSuite() throws SQLException {
		dbLib.closeDB();
	}
}
