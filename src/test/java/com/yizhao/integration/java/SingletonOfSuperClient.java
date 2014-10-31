package com.yizhao.integration.java;

import static org.vertx.testtools.VertxAssert.assertNotNull;
import static org.vertx.testtools.VertxAssert.assertTrue;
import static org.vertx.testtools.VertxAssert.fail;
import static org.vertx.testtools.VertxAssert.testComplete;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientRequest;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.core.json.JsonObject;
import org.vertx.testtools.TestVerticle;

public class SingletonOfSuperClient extends TestVerticle {
	/* Setup for Factory pattern */
	protected final FactoryOfAssertionChecker mAssertionCheckerFactory = new FactoryOfAssertionChecker();
	protected final FactoryOfCurlCommandsSetter mCurlCommandsSetterFactory = new FactoryOfCurlCommandsSetter();
	/* Setup for Strategy pattern */
	protected static BehaviorOfCurlCommandDataGenerater mCurlCommandDataGenerater;
	protected static BehaviorOfReponseDataGenerater mReponseDataGenerater;
	protected static BehaviorOfAssertionChecker mAssertionChecker;
	protected BehaviorOfCurlCommandsSetter mCurlCommandsSetter;
	/* For State Machine */
	protected static StatesOfClient curState = StatesOfClient.STATE_RESET;
	/* Setup for Singleton Pattern */
	protected final SingletonOfConstantsT ct = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfConstantsT cts = SingletonOfConstantsT.getInstance();
	protected final static SingletonOfPrintingMethodsOfClient pmfc = SingletonOfPrintingMethodsOfClient.getInstance();

	/* Variables for HTTP Client */
	protected HttpClient client = null;
	protected static HttpClientRequest requestSendFromClienttoServer = null;
	/* For ClientConfig.json file */
	protected int CURL_HTTP_PORT;
	protected String CURL_HTTP_HOST;
	/* Variables for current data */
	protected static JsonObject currentServerResponseInJsonFormat;
	/**/
	protected static int statusCode = 0;

	protected void sendRequest(StatesOfClient state) {
		resetState();
		setState(state);
		pmfc.printWhichStateIsTesting();

		try {
			if (CURL_HTTP_PORT != 443) {
				client = vertx.createHttpClient().setPort(CURL_HTTP_PORT).setHost(CURL_HTTP_HOST);
			} else {
				client = vertx.createHttpClient().setSSL(true).setTrustAll(true).setPort(CURL_HTTP_PORT).setHost(CURL_HTTP_HOST);
			}

			mCurlCommandsSetter = mCurlCommandsSetterFactory.createSetter(getState());
			mCurlCommandsSetter.execute(state);

			pmfc.printCurrentRequestAndPathInCurlCommand();
			requestSendFromClienttoServer = client.request(BehaviorOfCurlCommandsSetter.currentRequest, BehaviorOfCurlCommandsSetter.currentPath, new Handler<HttpClientResponse>() {
				/*
				 * This handler recieves SerΩ©ver response
				 */
				@Override
				public void handle(HttpClientResponse responseRecievedAtClientFromServer) {
					statusCode = responseRecievedAtClientFromServer.statusCode();
					responseRecievedAtClientFromServer.bodyHandler(new Handler<Buffer>() {
						/*
						 * This handler recieves Server response
						 * body buffer from previous handler
						 */
						@Override
						public void handle(Buffer body) {
							try {
								try {
									pmfc.printMessageFromServer(body);
									currentServerResponseInJsonFormat = new JsonObject(body.toString());
									getDataFromDB();
								} catch (Exception e) {

								}
								mAssertionChecker = mAssertionCheckerFactory.createChecker(getState());
								mAssertionChecker.execute(getState());

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								pmfc.printEnd();
								testComplete();
							}
						}
					});
				}
			}).exceptionHandler(new Handler<Throwable>() {
				@Override
				public void handle(Throwable t) {
					t.printStackTrace();
				}
			});

			try {
				headersSetUp();
				jsonBodySetUp();
			} catch (Exception e) {

			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

	private void getDataFromDB() {
		switch (getState()) {
		case STATE_POST:
		case STATE_GET:
			break;
		default:
			break;
		}
	}

	private void headersSetUp() {

	}

	private void jsonBodySetUp() {
		requestSendFromClienttoServer.end();
	}

	protected static void resetState() {
		curState = StatesOfClient.STATE_RESET;
	}

	protected static void setState(StatesOfClient state) {
		curState = state;
	}

	public static StatesOfClient getState() {
		return curState;
	}

	@Override
	public void start() {
		initialize();
		try {
			serverConfigSetup();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Deploy the module - the System property `vertx.modulename` will
		// contain the name of the module so you don't have to hardcode it in your tests
		container.deployModule(System.getProperty("vertx.modulename"), new AsyncResultHandler<String>() {
			@Override
			public void handle(AsyncResult<String> asyncResult) {
				// Deployment is asynchronous and this this handler will
				// be called when it's complete (or failed)
				assertTrue(asyncResult.succeeded());
				assertNotNull("deploymentID should not be null", asyncResult.result());
				startTests();
			}
		});
	}

	private void serverConfigSetup() throws Exception {
		String sConfig = IOUtils.toString(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/ClientConfig.json"))));
		JsonObject config = new JsonObject(sConfig);
		CURL_HTTP_HOST = config.getString("server_host");
		CURL_HTTP_PORT = config.getInteger("server_port");
	}
}
