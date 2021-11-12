package com.rmgyantra.projecttest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.rmgyantra.gerenericUtility.APiBaseClass;
import com.rmgyantra.gerenericUtility.DataBaseUtilities;
import com.rmgyantra.gerenericUtility.IEndPoints;
import com.rmgyantra.gerenericUtility.JavaUtility;
import com.rmgynatra.pojo.utility.Project;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddProjectWithOnGoingStatus extends APiBaseClass{
	
	@Test
	public void addProjectWithCompletedStatusTest() throws Throwable {
		String status = "On Going";
		String projectNAme = "SDET_22_"+JavaUtility.getRandomNumber();
		Project pojoObj = new Project("deepak", projectNAme, status, 10);
		
		//execute API and get the data & verify
          Response resp = given()
						     .contentType(ContentType.JSON)
						     .body(pojoObj)
						    .when()
						     .post(IEndPoints.AddProjectWithOnGoingStatus_EndPoint);
		           resp.then()
						    .assertThat().statusCode(201)
						    .log().all();
		          String apiResponseProjectNAme =  resp.jsonPath().get("projectName");
		          System.out.println(apiResponseProjectNAme);
		         
		 //connect to db & get the data         
		 String dbPrjectNAme = dbLib.executeQuerryAnfVerifyAndGetData("select * from project", 4, apiResponseProjectNAme);
		 String dbStatus = dbLib.executeQuerryAnfVerifyAndGetData("select * from project", 5, status);
		 Assert.assertEquals(dbPrjectNAme, apiResponseProjectNAme);
				
	  //connect web GUI get the projectNAme 
				
	}

}
