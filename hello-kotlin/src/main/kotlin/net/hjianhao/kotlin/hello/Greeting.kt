package net.hjianhao.kotlin.hello

import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch
import io.vertx.kotlin.coroutines.awaitEvent

/**
 * A function to say hello to given user.
 *
 * @param name user name, optional
 * @return greeting string to given user
 *
 */
fun sayHi(name: String = "World"): String = "Hello $name!"

fun main (args: Array<String>) {
	var vertx = Vertx.vertx();
	
	vertx.deployVerticle (MyVerticle());
	
	launch (vertx.dispatcher ()) {
		val timerId = awaitEvent<Long> {handler ->
			vertx.setTimer (1000, handler);
		}
		
		println ("Event fired from timer with id ${timerId}");
	}
	
}