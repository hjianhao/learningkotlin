package net.hjianhao.kotlin.hello

import io.vertx.core.http.HttpServer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult

class MyVerticle : CoroutineVerticle() {

	// Called when verticle is deployed
	suspend override fun start() {
		var server = vertx.createHttpServer()

		server.requestHandler({ request ->
			request.response ().end ("Hello World")
		})
		
		try {
			val result = awaitResult<HttpServer> { handler ->
				server.listen(8080, handler)
			}
			println("Server is now listening!")
		} catch (e: Exception) {
			println("Failed to bind! ${e.message}")
		}
	}

	// Optional - called when verticle is undeployed
	suspend override fun stop() {
	}
}