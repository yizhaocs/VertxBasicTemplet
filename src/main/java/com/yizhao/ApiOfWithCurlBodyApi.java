package com.yizhao;

import java.util.Arrays;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerFileUpload;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ApiOfWithCurlBodyApi extends SuperClassOfApis {
	public ApiOfWithCurlBodyApi() {

	}
	
	public void execute(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client){
		try {
			bridge_between_server_and_client.expectMultiPart(true);
			bridge_between_server_and_client.uploadHandler(new Handler<HttpServerFileUpload>() {
				public void handle(final HttpServerFileUpload upload) {
					final Buffer mainBuffer = new Buffer();
					upload.dataHandler(new Handler<Buffer>() {
						@Override
						public void handle(Buffer buffer) {
							mainBuffer.appendBuffer(buffer);
						}
					}).endHandler(new Handler<Void>() {
						@Override
						public void handle(Void arg0) {
							String query = "INSERT INTO " + "test" + "(" + "a, b" + ")" + " VALUES (" + "1" + "," + "\"" + Arrays.toString(mainBuffer.getBytes()) + "\"" + ")";
							System.out.println("query:" + query);
							JsonObject rawCommandJson = new JsonObject();
							rawCommandJson.putString("action", "raw");
							rawCommandJson.putString("command", query);
							System.out.println("rawCommandJson:" + rawCommandJson.encodePrettily());
							vertx.eventBus().send("backend", rawCommandJson, new Handler<Message<JsonObject>>() {
								/*
								 * This handler recieves response from MySql DBMS
								 */
								@Override
								public void handle(Message<JsonObject> databaseMessage) {
									JsonObject databaseMessageBody = databaseMessage.body();
									JsonObject response = new JsonObject();
									response.putString("status", "0");
									response.putObject("result", databaseMessageBody);
									bridge_between_server_and_client.response().end(response.encodePrettily());
								}
							});
						}
					});

				}
			});
		} catch (Exception e) {
			container.logger().error(e.getStackTrace());
			JsonObject response = new JsonObject();
			response.putString("status", "1");
			response.putString("statusDescription", "Unknown Error");
			bridge_between_server_and_client.response().end(response.encodePrettily());
		} finally {
			
		}
	}

}
