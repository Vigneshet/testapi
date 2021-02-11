package com.poc.testpoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.poc.testpoc.dao.ApiTestDao;
import com.poc.testpoc.model.ApiDetails;

@Service
public class ApiTestService {

	@Autowired
	ApiTestDao dao;

	@Autowired
	RestTemplate template;

	public List<ApiDetails> getApiDetails() {
		return dao.getApiDetails();
	}

	public String callApi(String id) {
		ApiDetails apiDetails = dao.getApiDetail(id);
		System.out.println("apiDetails ::: " + apiDetails);
		if (apiDetails == null)
			return "Failure";
		ResponseEntity<String> responseEntity = template.getForEntity(apiDetails.getWebServiceUrl(), String.class);
		System.out.println(responseEntity.getBody());
		String response = responseEntity.getBody();
		String urlArr[] = apiDetails.getWebServiceUrl().split("/");
		String tableName = urlArr[urlArr.length - 1];
		System.out.println(tableName);
		if (!dao.isTableThere(tableName))
			dao.createTable(createTable(tableName, response));
		dao.insertRecord(buildInsertQuery(tableName, response));
		return "Success";
	}

	public int addApi(ApiDetails apiDetails) {
		return dao.insertApi(apiDetails);
	}

	public String createTable(String tableName, String response) {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ");
		sb.append(tableName);
		sb.append(" ( ");
		List<String> columnNames = fieldProcessor(response);
		if (columnNames == null || columnNames.size() == 0)
			return "Error in processing response from api";
		columnNames.forEach(column -> {
			sb.append(column);
			sb.append(" ");
			sb.append("varchar(100)");
			sb.append(",");
		});
		StringBuilder sb1 = new StringBuilder(sb.substring(0, sb.length() - 1));
		sb1.append(" )");
		return sb1.toString();
	}

	public String buildInsertQuery(String tableName, String response) {

		return null;
	}

	public List<String> fieldProcessor(String response) {
		List<String> columnNames = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(response);
		if (element.isJsonArray()) {
			JsonArray jsonArr = element.getAsJsonArray();
			for (int i = 0; i < jsonArr.size();) {
				JsonObject obj = (JsonObject) jsonArr.get(i);
				getColumnNames(columnNames, obj, null);
				break;
			}
		} else if (element.isJsonObject()) {
			getColumnNames(columnNames, element.getAsJsonObject(), null);
		}
		System.out.println(columnNames);
		return columnNames;
	}

	public void getColumnNames(List<String> columnNames, JsonObject obj, String parent) {
		Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
		for (Map.Entry<String, JsonElement> entry : entrySet) {
			if (obj.get(entry.getKey()).isJsonArray()) {
				JsonArray jsonArr = obj.get(entry.getKey()).getAsJsonArray();
				if (parent == null)
					parent = entry.getKey();
				else
					parent = parent + "_" + entry.getKey();
				getColumnNames(columnNames, jsonArr.get(0).getAsJsonObject(), parent);
				parent = null;
			} else if (obj.get(entry.getKey()).isJsonObject()) {
				if (parent == null)
					parent = entry.getKey();
				else
					parent = parent + "_" + entry.getKey();
				getColumnNames(columnNames, obj.get(entry.getKey()).getAsJsonObject(), parent);
				parent = null;
			} else {
				if (parent == null)
					columnNames.add(entry.getKey());
				else
					columnNames.add(parent + "_" + entry.getKey());
			}
		}
	}
}
