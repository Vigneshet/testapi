package com.poc.testpoc.constants;

public class ApiTestConstants {

	public static String GET_API_DETAILS = "Select id as id, web_service_name as webservicename, web_service_url as webserviceurl, user_name as username, password as password from webservice_details";
	public static String GET_API_DETAILS_SPECIFIC = "Select id as id, web_service_name as webservicename, web_service_url as webserviceurl, user_name as username, password as password from webservice_details where id = ?";
	public static String INSERT_API_DETAILS = "INSERT INTO webservice_details(WEB_SERVICE_NAME, WEB_SERVICE_URL, USER_NAME, PASSWORD) VALUES (?,?,?,?)";
	public static String SELECT_TABLE = "SELECT count(1) from ";
}
