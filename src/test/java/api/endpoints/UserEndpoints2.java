package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	//method created for getting urls from properties file
	static ResourceBundle getURLs() {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load properties file
		return routes;
	}

public static Response createUser(User payload) {
		
	    String post_url = getURLs().getString("create_user_url");
	    System.out.println(post_url);
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url);
		return response;
		
	}
	
public static Response getUser(String userName) {
		
	String get_url = getURLs().getString("get_user_url");
	System.out.println(get_url);
		Response response = given()
				            .pathParam("username", userName)
				            .when()
				            .get(get_url);
		return response;
	}

public static Response updateUser(String userName, User payload) {
	
	String put_url = getURLs().getString("update_user_url");
	System.out.println(put_url);
	Response response = given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .pathParam("username", userName)
	 .body(payload)
	 .when()
	 .put(put_url);
	return response;
}

public static Response deleteUser(String userName) {
	String delete_url = getURLs().getString("delete_user_url");
	System.out.println(delete_url);
	Response response = given()
			            .pathParam("username", userName)
			            .when()
			            .delete(delete_url);
	return response;
}

}
