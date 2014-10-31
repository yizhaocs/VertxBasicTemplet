package com.yizhao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;

public class SingletonOfPrintingMethodsOfServer {
	/* Setup for Singleton pattern */
	private static SingletonOfPrintingMethodsOfServer instance = null;
	
	private SingletonOfPrintingMethodsOfServer() {

	}

	public static SingletonOfPrintingMethodsOfServer getInstance() {
		if (instance == null) {
			instance = new SingletonOfPrintingMethodsOfServer();
		}
		return instance;
	}
	
	/* Variables*/
	public static Logger logger;
	private SingletonOfSwitchesOfServer sos = SingletonOfSwitchesOfServer.getInstance();
	private SingletonOfConstantsS scs = SingletonOfConstantsS.getInstance();
	// Logger logger = container.logger();

	protected void printServerTime(StatesOfServer state) {
		if (sos.isTesting_Print_Switch()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("{-----------------Server Time:" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + scs.HEADER_END);
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printHeaders(StatesOfServer state, List<Entry<String, String>> header) {
		printServerTime(state);
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printHeaders:" + header.toString());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printCurlBody(StatesOfServer state,Buffer body) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("curlBodyChecker:" + body.toString());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printRawCommandJson(StatesOfServer state, JsonObject raw) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printRawCommandJson:" + raw.encodePrettily());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printTransactionJson(StatesOfServer state, JsonObject raw) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printTransactionJson:" + raw.encodePrettily());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printWhichStateIsTesting(StatesOfServer state) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("HTTP Server is performing " + state + " .");
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printDatabaseMessage(StatesOfServer state, JsonObject databaseMessageBody) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("HTTP Server on state: " + state + ", it just " + "recieved response from database.");
			logger.info("printDatabaseMessage:");
			logger.info(databaseMessageBody.encodePrettily());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printQuery(StatesOfServer state, String query) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printQuery:" + query);
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printCurlData(StatesOfServer state, String body) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printCurlData:" + body);
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printDBresponseFromOSGSessionVerificationAPI(StatesOfServer state, JsonObject j) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printDBresponseFromOSGSessionVerificationAPI:" + j.encodePrettily());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printResponseToClient(StatesOfServer state, JsonObject response) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printResponseToClient: " + response.encodePrettily());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printOSGsessionCheckHeaders(StatesOfServer state, HttpClientRequest requestSendFromClienttoServer) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printOSGsessionCheckHeaders: " + requestSendFromClienttoServer.headers().entries().toString());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printOSGsessionCheckResponse(StatesOfServer state, Buffer body) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			if (body != null) {
				logger.info("printOSGsessionCheckResponse: " + body.toString());
			} else {
				logger.info("printOSGsessionCheckResponse: " + "body is null");
			}

			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printMultiPartInfo(StatesOfServer state, HttpServerFileUpload upload, Buffer mainBuffer) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("got here too. upload.size() = " + upload.size());
			logger.info("endHandler() called");
			logger.info("mainBuffer = " + mainBuffer.length());
			logger.info("upload.contentType() = " + upload.contentType());
			logger.info("upload.name() = " + upload.name());
			logger.info("upload.filename() = " + upload.filename());
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printExceptionHandlerTraceStack(StatesOfServer state, Throwable t) {
		if (sos.isTesting_Print_Switch()) {
			logger.info(scs.HEADER_BEGIN + state + scs.HEADER_END);
			logger.info("printExceptionHandlerTraceStack: ");
			t.printStackTrace();
			logger.info(scs.END_SMALL);
			logger.info(" ");
		}
	}

	protected void printOSGconfigInfo(JsonObject osgConfig) {
		if (sos.isTesting_Print_Switch()) {
			System.out.println("Print OSG Config Info:");
			System.out.println(osgConfig.encode());
		}
	}

	protected void printDBconfigInfo(JsonObject dbConfig) {
		if (sos.isTesting_Print_Switch()) {
			System.out.println("Print DB Config Info:");
			System.out.println(dbConfig.encode());
		}
	}
}
