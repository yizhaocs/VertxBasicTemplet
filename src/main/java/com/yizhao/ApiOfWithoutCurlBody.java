package com.yizhao;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfWithoutCurlBody extends SuperClassOfApis {
	public ApiOfWithoutCurlBody() {

	}

	public void execute(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client){
		JsonObject response = new JsonObject();
		response.putString("status", "0");
		response.putString("result", bridge_between_server_and_client.params().get("key"));
		bridge_between_server_and_client.response().end(response.encodePrettily());
	}
}
