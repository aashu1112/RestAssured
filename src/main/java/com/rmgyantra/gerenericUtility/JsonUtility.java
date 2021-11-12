package com.rmgyantra.gerenericUtility;

import io.restassured.response.Response;

public class JsonUtility {

	
	public String getJsonData(Response resp , String jsonXpath) {
		String jsonData = resp.jsonPath().get(jsonXpath);
		return jsonData;
	}
}
