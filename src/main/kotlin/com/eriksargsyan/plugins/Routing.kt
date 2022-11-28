package com.eriksargsyan.plugins

import PackCaesar
import PackRLE
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val OPERATION = "operation"
const val STEP = "step"
const val TEXT = "text"
val packRLE = PackRLE()
val packCaesar = PackCaesar()

fun Application.configureRouting() {

    routing {
        get("/rle") {
            val operationType = call.parameters[OPERATION]
            val encryptText = call.parameters[TEXT]
            when (operationType) {
                "p", "u" -> {
                    call.respondText(packRLE.newEncrypt(operationType == "p", encryptText))
                }
            }
        }
        get("/caesar") {
            val operationType = call.parameters[OPERATION]
            val shiftKey = call.parameters[STEP]
            val encryptText = call.parameters[TEXT]
            when (operationType) {
                "p", "u" -> {
                    call.respondText(packCaesar.newEncrypt(operationType == "p", encryptText, shiftKey!!.toInt()))
                }
            }
        }
    }
}

