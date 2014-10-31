package com.yizhao;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfDelete extends SuperClassOfApis {

	public ApiOfDelete() {

	}
	
	public void delete(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		try {
			// Connecting to Redis on localhost
			
			JsonObject response = new JsonObject();
			response.putString("status", "0");
			response.putString("statusDescription", "Success");
			//response.putString("result", String.valueOf(jedis.del(bridge_between_server_and_client.params().get("key").getBytes())));
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
