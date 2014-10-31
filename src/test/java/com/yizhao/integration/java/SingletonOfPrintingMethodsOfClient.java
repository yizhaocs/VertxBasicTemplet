package com.yizhao.integration.java;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClientRequest;



public class SingletonOfPrintingMethodsOfClient {
	static SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	SingletonOfSwitchesOfClient soc = SingletonOfSwitchesOfClient.getInstance();
	/* Setup for Singleton pattern */
	private static SingletonOfPrintingMethodsOfClient instance = null;

	private SingletonOfPrintingMethodsOfClient() {

	}

	public static SingletonOfPrintingMethodsOfClient getInstance() {
		if (instance == null) {
			instance = new SingletonOfPrintingMethodsOfClient();
		}
		return instance;
	}

	protected void printWhichStateIsTesting() {
		if (soc.isTesting_Print_Switch()) {
			System.out.println("{-----------------Client State:" + SingletonOfSuperClient.getState() + "-------------------");
			System.out.println(SingletonOfSuperClient.getState() + " " + "is start testing");
			System.out.println(ct.END_SMALL);
		}
	}

	protected void printMessageFromServer(Buffer body) {
		if (soc.isTesting_Print_Switch()) {
			System.out.println("{-----------------Client State:" + SingletonOfSuperClient.getState() + "-------------------");
			System.out.println(SingletonOfSuperClient.getState() + " " + "Response Recieved:");
			System.out.println(body.toString());
			System.out.println(ct.END_SMALL);
		}
	}

	protected void printCurrentRequestAndPathInCurlCommand() {
		if (soc.isTesting_Print_Switch()) {
			System.out.println("{-----------------Client State:" + SingletonOfSuperClient.getState() + "-------------------");
			System.out.println(SingletonOfSuperClient.getState() + " " + "Response Send:");
			System.out.println("HTTP method:" + BehaviorOfCurlCommandsSetter.currentRequest);
			System.out.println("Path:" + BehaviorOfCurlCommandsSetter.currentPath);
			if (!SingletonOfSuperClient.getState().equals(StatesOfClient.STATE_POST)) {
				System.out.println("Json Body:" + BehaviorOfCurlCommandsSetter.currentDataSendToServer);
			}
			System.out.println(ct.END_SMALL);
		}
	}

	protected void printEnd() {
		if (soc.isTesting_Print_Switch()) {
			System.out.println(ct.END_BIG);
		}
	}

	protected void printCurrentStateInfo() {
		if (soc.isTesting_Print_Switch()) {
			System.out.println("start testing for:" + SingletonOfSuperClient.getState());
		}
	}
}
