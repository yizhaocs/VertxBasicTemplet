package com.yizhao;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfWithCurlJsonBodyApi extends SuperClassOfApis {
	public ApiOfWithCurlJsonBodyApi() {

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
