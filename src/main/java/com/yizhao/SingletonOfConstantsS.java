package com.yizhao;

public class SingletonOfConstantsS {
	/* Setup for Singleton pattern */
	private static SingletonOfConstantsS instance = null;

	private SingletonOfConstantsS() {

	}

	public static SingletonOfConstantsS getInstance() {
		if (instance == null) {
			instance = new SingletonOfConstantsS();
		}
		return instance;
	}

	/* Constants for API path */
	protected final String PATH_OF_POST = "/cloud/:package-name/stream/:stream-key";
	protected final String PATH_OF_GET = "/cloud/user/:user-key/:package-name/stream/:stream-key";
	protected final String PATH_OF_DELETE = "/cloud/:package-name/stream/:stream-key";
	/* Constants for SingletonOfPrintingMethodsOfServer */
	protected final String HEADER_BEGIN = "{-----------------Server State:";
	protected final String HEADER_END = "-------------------";
	protected final String END_SMALL = "----------------------------------------------------------------------------------}";
}
