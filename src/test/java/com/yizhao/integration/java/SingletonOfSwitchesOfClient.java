package com.yizhao.integration.java;

public class SingletonOfSwitchesOfClient {
	/* Setup for Singleton pattern */
	private static SingletonOfSwitchesOfClient instance = null;

	private SingletonOfSwitchesOfClient() {

	}

	public static SingletonOfSwitchesOfClient getInstance() {
		if (instance == null) {
			instance = new SingletonOfSwitchesOfClient();
		}
		return instance;
	}

	protected final boolean Testing_Print_SWITCH = true;

	protected boolean isTesting_Print_Switch() {
		return Testing_Print_SWITCH;
	}
}
