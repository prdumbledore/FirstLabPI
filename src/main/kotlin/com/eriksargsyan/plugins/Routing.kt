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

val RLE_OPERATION_TYPE_ERROR = """
                        OPERATION TYPE ERROR
                        Please, use  parameter "operation=p" for packing or "operation=u" for unpacking
                        
                        Example: http://127.0.0.1:8080/rle?operation=p&text=pppppp
                    """.trimIndent()
val RLE_INPUT_TEXT_ERROR = """
                        INPUT TEXT ERROR
                        Please, use  parameter "text={YOUR TEXT}"
                        
                        Example: http://127.0.0.1:8080/rle?operation=p&text=pppppp
                    """.trimIndent()
val CAESAR_OPERATION_TYPE_ERROR = """
                        OPERATION TYPE ERROR
                        Please, use  parameter "operation=p" for packing or "operation=u" for unpacking
                        
                        Example: http://127.0.0.1:8080/caesar?operation=p&step=5&text=pppppp
                    """.trimIndent()
val CAESAR_STEP_ERROR = """
                        STEP ERROR
                        Please, use  parameter "step={YOUR STEP}"
                        
                        Example: http://127.0.0.1:8080/caesar?operation=p&step=5&text=pppppp
                    """.trimIndent()
val CAESAR_INPUT_TEXT_ERROR = """
                        INPUT TEXT ERROR
                        Please, use  parameter "text={YOUR TEXT}"
                        
                        Example: http://127.0.0.1:8080/caesar?operation=p&step=5&text=pppppp
                    """.trimIndent()


fun Application.configureRouting() {

    routing {
        get("/rle") {
            val operationType = call.parameters[OPERATION]
            val encryptText = call.parameters[TEXT]
            when (operationType) {
                "p", "u" -> {
                    encryptText?.let {
                        call.respondText(packRLE.newEncrypt(operationType == "p", encryptText))
                    } ?: call.respondText(RLE_INPUT_TEXT_ERROR)
                }
                else -> {
                    call.respondText(RLE_OPERATION_TYPE_ERROR)
                }
            }
        }
        get("/caesar") {
            val operationType = call.parameters[OPERATION]
            val shiftKey = call.parameters[STEP]
            val encryptText = call.parameters[TEXT]
            when (operationType) {
                "p", "u" -> {
                    encryptText?.let {
                        shiftKey?.let {
                            call.respondText(packCaesar.newEncrypt(operationType == "p", encryptText, shiftKey.toInt()))
                        } ?: call.respondText(CAESAR_STEP_ERROR)
                    } ?: call.respondText(CAESAR_INPUT_TEXT_ERROR)
                }
                else -> {
                    call.respondText(CAESAR_OPERATION_TYPE_ERROR)
                }
            }
        }
    }
}
