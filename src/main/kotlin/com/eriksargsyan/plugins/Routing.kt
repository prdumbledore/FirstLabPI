package com.eriksargsyan.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

const val ENCRYPT = "PackRLE"

fun Application.configureRouting() {

    routing {
        get("/encrypt") {
            val encrypt = call.parameters[ENCRYPT]
            call.respondText("Hello World!")
        }
    }
}
