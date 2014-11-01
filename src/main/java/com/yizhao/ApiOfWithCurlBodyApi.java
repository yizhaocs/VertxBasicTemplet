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

	public void execute(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		bridge_between_server_and_client.bodyHandler(new Handler<Buffer>() {
			/*
			 * This handler recieves curl body buffer from Client
			 */
			@Override
			public void handle(Buffer curlBody) {
				JsonObject curlBodyJs = new JsonObject(curlBody.toString());
				JsonObject response = new JsonObject();
				response.putString("status", "0");
				response.putString("statusDescription", "OK");
				response.putString("curlBody", curlBodyJs.encodePrettily());
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}

}
