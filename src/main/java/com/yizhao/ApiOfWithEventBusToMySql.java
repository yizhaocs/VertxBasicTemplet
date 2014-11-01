package com.yizhao;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

public class ApiOfWithEventBusToMySql extends SuperClassOfApis{
	public ApiOfWithEventBusToMySql(){
		
	}
	
	public void execute(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {
		JsonObject insert = new JsonObject();
		insert.putString("action", "insert");
		insert.putString("table", "test");
		JsonArray fields = new JsonArray();
		fields.add("a");
		fields.add("b");
		insert.putArray("fields", fields);
		JsonArray values = new JsonArray();
		values.addNumber(1);
		values.addNumber(2);
		insert.putArray("values", values);
		vertx.eventBus().send("backend", insert, new Handler<Message<JsonObject>>() {
			/*
			 * This handler recieves response from MySql DBMS
			 */
			@Override
			public void handle(Message<JsonObject> databaseMessage) {
				JsonObject databaseMessageBody = databaseMessage.body();
				JsonObject response = new JsonObject();
				response.putObject("Database Message", databaseMessageBody);
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}
}
