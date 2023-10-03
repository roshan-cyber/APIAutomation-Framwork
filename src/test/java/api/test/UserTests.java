package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker fakeData;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		fakeData = new Faker();
		userPayload = new User();
		userPayload.setId(fakeData.idNumber().hashCode());
		userPayload.setUsername(fakeData.name().username());
		userPayload.setFirstName(fakeData.name().firstName());
		userPayload.setLastName(fakeData.name().lastName());
		userPayload.setEmail(fakeData.internet().safeEmailAddress());
		userPayload.setPassword(fakeData.internet().password(5, 10));
		userPayload.setPhone(fakeData.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("*********Creating User**********************");
		Response response =UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User is Created**********************");
		
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("*********getting User by user name**********************");
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********user found**********************");
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("*********updating user**********************");
		//update first name and last name
		userPayload.setFirstName(fakeData.name().firstName());
		userPayload.setLastName(fakeData.name().lastName());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//response after update
		
		Response resaferUpdate = UserEndpoints.getUser(this.userPayload.getUsername());
		resaferUpdate.then().log().all();
		String testUserName = resaferUpdate.jsonPath().getString("username");
				System.out.println("Created UserName====" + testUserName);
		
		Assert.assertEquals(resaferUpdate.getStatusCode(), 200);
		logger.info("*********user updated**********************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("*********deleting user**********************");
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********user deleted**********************");
	}

}
