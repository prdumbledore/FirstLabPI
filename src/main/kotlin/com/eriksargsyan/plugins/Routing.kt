package com.eriksargsyan.plugins

import PackRLE
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val OPERATION = "operation"
const val ENCRYPT = "text"
val packRLE = PackRLE()

fun Application.configureRouting() {

    routing {
        get("/encrypt") {
            val operationType = call.parameters[OPERATION]
            val encryptText = call.parameters[ENCRYPT]
            when (operationType) {
                "p", "u" -> {
                    call.respondText(packRLE.newEncrypt(true, encryptText))
                }
            }
        }
    }
}

