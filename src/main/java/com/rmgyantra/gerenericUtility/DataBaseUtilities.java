package com.rmgyantra.gerenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
/**
 * 
 * @author asd
 *
 */
public class DataBaseUtilities {
	static Driver driver;
	static Connection connection;
	static ResultSet result;
    
	/**
	 * This method will perform the mysql database connection
	 */
	public void connectDB() {
		try {
			driver=new Driver();
			DriverManager.registerDriver(driver);
			connection=DriverManager.getConnection(IConstants.dbURL,IConstants.username,IConstants.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * This method will close the mysql database
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException {
		connection.close();


	}
	/**
	 * This method will execute the querry
	 * @param query
	 * @return
	 * @throws Throwable
	 */
	public ResultSet execyteQuery(String query) throws Throwable {
		try {
			result = connection.createStatement().executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
	} 
		return result;

	}
	/**
	 * This method will execute querry based on querry and it will verify the data. 
	 * @param querry
	 * @param columnName
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public String executeQuerryAnfVerifyAndGetData(String querry,int columnName,String expectedData) throws Throwable {
		boolean flag=false;
		result=connection.createStatement().executeQuery(querry);
		while(result.next()) {
			if(result.getString(columnName).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			System.out.println(expectedData+"==>Data is verified in the data base table");
			return expectedData;
		}else {
			System.out.println(expectedData+"==>data is not verified in the database");
			return expectedData;
		}

	}




}
