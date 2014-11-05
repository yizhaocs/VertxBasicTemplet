package com.yizhao;

/*
 * Copyright 2013 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

/*
 This is a simple Java verticle which receives `ping` messages on the event bus and sends back `pong` replies
 */
public class MainServerVerticle extends Verticle {
	SingletonOfConstantsS cs = SingletonOfConstantsS.getInstance();
	SingletonOfServerConfigSetup mSingletonOfServerConfigSetup = SingletonOfServerConfigSetup.getInstance();
	ApiOfWithoutCurlBody mApiOfWithoutCurlBody;
	ApiOfWithCurlJsonBodyApi mApiOfWithCurlBodyApi;
	ApiOfWithCurlBinaryDataFileApi mApiOfWithCurlBinaryDataFileApi;
	ApiOfWithMultiPart mApiOfWithMultiPart;
	ApiOfWithEventBusToMySql mApiOfWithEventBusToMySql;

	private void deployMySqlModule() {
		container.deployModule("io.vertx~mod-mysql-postgresql_2.10~0.3.1", mSingletonOfServerConfigSetup.getDBconfig(), new AsyncResultHandler<String>() {
			public void handle(AsyncResult<String> asyncResult) {
				System.out.println("MySQL/Postgres module deployment ID: " + asyncResult.result());
				System.out.println("MySQL/Postgres module deployment failed: " + asyncResult.failed());
				if (asyncResult.failed()) {
					System.out.println("MySQL/Postgres module deployment asyncResult.cause printStackTrace: ");
					asyncResult.cause().printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		deployMySqlModule();
		RouteMatcher httpRouteMatcher = new RouteMatcher();
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.requestHandler(httpRouteMatcher);
		httpServer.listen(8080, "0.0.0.0");

		// curl -v -X POST http://localhost:8080/withoutcurlbody/apple
		httpRouteMatcher.post("/withoutcurlbody/:key", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at withoutcurlbody API");
				mApiOfWithoutCurlBody = new ApiOfWithoutCurlBody();
				mApiOfWithoutCurlBody.execute(vertx, bridge_between_server_and_client);
			}
		});

		// curl -v -X POST http://localhost:8080/withcurlbody -d '{"body":123}'
		httpRouteMatcher.post("/withcurlbody", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at withcurlbody API");
				mApiOfWithCurlBodyApi = new ApiOfWithCurlJsonBodyApi();
				mApiOfWithCurlBodyApi.execute(vertx, bridge_between_server_and_client);
			}
		});

		// curl -v --request POST --data-binary "@3.png" http://localhost:8080/withbinarydatafile  --trace-ascii /dev/stdout 
		httpRouteMatcher.post("/withbinarydatafile", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at withbinarydatafile API");
				mApiOfWithCurlBinaryDataFileApi = new ApiOfWithCurlBinaryDataFileApi();
				mApiOfWithCurlBinaryDataFileApi.execute(vertx, bridge_between_server_and_client);
			}
		});
		
		// curl -v -X POST http://localhost:8080/withmultipart -F "file=@3.png" --trace-ascii /dev/stdout
		httpRouteMatcher.post("/withmultipart", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at mApiOfWithMultiPart API");
				mApiOfWithMultiPart = new ApiOfWithMultiPart();
				mApiOfWithMultiPart.execute(vertx, bridge_between_server_and_client);
			}
		});
		
		// curl -v -X POST http://localhost:8080/apiofwitheventbustomysql
		httpRouteMatcher.post("/apiofwitheventbustomysql", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest bridge_between_server_and_client) {
				container.logger().info("Invoked at mApiOfWithEventBusToMySql API");
				mApiOfWithEventBusToMySql = new ApiOfWithEventBusToMySql();
				mApiOfWithEventBusToMySql.execute(vertx, bridge_between_server_and_client);
			}
		});
		
		

		httpRouteMatcher.noMatch(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().end("{ \"status\": \"1\", \"api\": \"no Api match\" }");
			}
		});
	}
}