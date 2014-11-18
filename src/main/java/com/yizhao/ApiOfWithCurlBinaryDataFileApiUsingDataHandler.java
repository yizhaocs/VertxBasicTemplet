package com.yizhao;

import java.util.Arrays;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonObject;

public class ApiOfWithCurlBinaryDataFileApiUsingDataHandler  extends SuperClassOfApis {
	public ApiOfWithCurlBinaryDataFileApiUsingDataHandler() {

	}

	public void execute(final Vertx vertx, final HttpServerRequest bridge_between_server_and_client) {		
		final Buffer mainBuffer = new Buffer();
		bridge_between_server_and_client.dataHandler(new Handler<Buffer>() {
			@Override
			public void handle(Buffer buffer) {
				mainBuffer.appendBuffer(buffer);
				System.out.println("Buffer:" + Arrays.toString(mainBuffer.getBytes()));
			}
		});

		bridge_between_server_and_client.endHandler(new VoidHandler() {
			@Override
			public void handle() {
				JsonObject response = new JsonObject();
				response.putString("status", "0");
				response.putString("statusDescription", "OK");
				response.putString("Binary Data", Arrays.toString(mainBuffer.getBytes()));
				bridge_between_server_and_client.response().end(response.encodePrettily());
			}
		});
	}
}
