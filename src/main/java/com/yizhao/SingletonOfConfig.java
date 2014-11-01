package com.yizhao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.json.JsonObject;


public class SingletonOfConfig {
	protected SingletonOfConstantsS css = SingletonOfConstantsS.getInstance();
	private static SingletonOfConfig instance = null;

	private SingletonOfConfig() {

	}

	public static SingletonOfConfig getInstance() {
		if (instance == null) {
			instance = new SingletonOfConfig();
		}
		return instance;
	}
	
	public JsonObject getDbConnectionParam() throws IOException {
		String internalConfig = IOUtils.toString(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/ServerConfig.json"))));
		JsonObject mysqlConfigJson = new JsonObject(internalConfig);
		JsonObject dbConfigResult = mysqlConfigJson.getObject("DbConnectionParam");
		return dbConfigResult;
	}
}
