package com.yizhao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.json.JsonObject;

public class SingletonOfServerConfigSetup {
	/* Setup for Singleton pattern */
	private static SingletonOfServerConfigSetup instance = null;
	private JsonObject config;

	private SingletonOfServerConfigSetup() {
		String sConfig = null;
		try {
			sConfig = IOUtils.toString(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/ServerConfig.json"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config = new JsonObject(sConfig);
	}

	public static SingletonOfServerConfigSetup getInstance() {
		if (instance == null) {
			instance = new SingletonOfServerConfigSetup();
		}
		return instance;
	}

	protected JsonObject getDBconfig(){
		return config.getObject("DbConnectionParam");
	}
}
