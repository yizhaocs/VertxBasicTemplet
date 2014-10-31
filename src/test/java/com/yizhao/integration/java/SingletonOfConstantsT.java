package com.yizhao.integration.java;


public class SingletonOfConstantsT {
	/* Setup for Singleton pattern */
	private static SingletonOfConstantsT instance = null;

	private SingletonOfConstantsT() {

	}

	public static SingletonOfConstantsT getInstance() {
		if (instance == null) {
			instance = new SingletonOfConstantsT();
		}
		return instance;
	}
	
	/* Constants for print command */
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
	protected final String END_BIG = "EndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEndEnd";
}
