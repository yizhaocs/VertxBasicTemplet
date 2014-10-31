package com.yizhao;

import java.util.Arrays;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfGet extends SuperClassOfApis {

	public ApiOfGet() {

	}

	public void get(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		try {
			// Connecting to Redis on localhost
			
			JsonObject response = new JsonObject();
			response.putString("status", "0");
			response.putString("statusDescription", "Success");
			//response.putString("result", Arrays.toString(jedis.get(bridge_between_server_and_client.params().get("key").getBytes())));
			bridge_between_server_and_client.response().end(response.encodePrettily());
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
