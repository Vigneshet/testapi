package com.poc.testpoc.constants;

public class ApiTestConstants {

	public static String GET_API_DETAILS = "Select id as id, web_service_name as webservicename, web_service_url as webserviceurl, user_name as username, password as password from webservice_details";
	public static String GET_API_DETAILS_SPECIFIC = "Select id as id, web_service_name as webservicename, web_service_url as webserviceurl, user_name as username, password as password from webservice_details where id = ?";

}
