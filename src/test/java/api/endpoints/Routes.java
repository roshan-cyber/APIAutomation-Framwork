package api.endpoints;

public class Routes {
	
	/* *
	 * Swagger url: https://petstore.swagger.io/
	 * Base URL: petstore.swagger.io/v2
	 GetUser(GET) : https://petstore.swagger.io/v2/user/{username}
	Create User(POST): https://petstore.swagger.io/v2/user/{username}
	UpdateUser(PUT): https://petstore.swagger.io/v2/user/{username}
	DeleteUser(DELETE):https://petstore.swagger.io/v2/user/{username} 
	 * */

	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	public static String create_user_url = base_url + "/user";
	public static String get_user_url = base_url + "/user/{username}";
	public static String update_user_url = base_url + "/user/{username}";
	public static String delete_user_url = base_url + "/user/{username}";
	

}
