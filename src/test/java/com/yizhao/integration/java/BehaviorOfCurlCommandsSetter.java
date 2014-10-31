package com.yizhao.integration.java;

import java.io.File;

import org.vertx.java.core.buffer.Buffer;



public abstract class BehaviorOfCurlCommandsSetter {
	protected SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	protected SingletonOfPrintingMethodsOfClient pmfc = SingletonOfPrintingMethodsOfClient.getInstance();
	protected static String currentRequest = null;
	protected static String currentPath = null;
	protected static String currentDataSendToServer = null;
	protected static File imageFile = new File("src/test/resources/testing.png");
	protected static Buffer imageFileBuffer = null;

	public abstract void execute(StatesOfClient state);
}