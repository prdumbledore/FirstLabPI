package com.eriksargsyan

import com.eriksargsyan.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ApplicationTest {

    @Test
    fun testRlePacking() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("rle?operation=p&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("5p", response.bodyAsText())

        response = client.get("rle?operation=z&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(RLE_OPERATION_TYPE_ERROR, response.bodyAsText())

        response = client.get("rle?operation=p&text")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(RLE_INPUT_TEXT_ERROR, response.bodyAsText())
    }

    @Test
    fun testRleUnpacking() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("rle?operation=u&text=5p")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("pppppp", response.bodyAsText())

        response = client.get("rle?operation=d&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(RLE_OPERATION_TYPE_ERROR, response.bodyAsText())

        response = client.get("rle?operation=u&text")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(RLE_INPUT_TEXT_ERROR, response.bodyAsText())
    }

    @Test
    fun testCaesarPacking() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("/caesar?operation=p&step=5&text=kkkkkk")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("pppppp", response.bodyAsText())

        response = client.get("/caesar?operation=k&step=5&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_OPERATION_TYPE_ERROR, response.bodyAsText())

        response = client.get("/caesar?operation=p&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_STEP_ERROR, response.bodyAsText())

        response = client.get("/caesar?operation=p&step=5")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_INPUT_TEXT_ERROR, response.bodyAsText())
    }

    @Test
    fun testCaesarUnpacking() = testApplication {
        application {
            configureRouting()
        }
        var response = client.get("/caesar?operation=u&step=5&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("kkkkkk", response.bodyAsText())

        response = client.get("/caesar?operation=z&step=5&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_OPERATION_TYPE_ERROR, response.bodyAsText())

        response = client.get("/caesar?operation=u&text=pppppp")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_STEP_ERROR, response.bodyAsText())

        response = client.get("/caesar?operation=u&step=5")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(CAESAR_INPUT_TEXT_ERROR, response.bodyAsText())
    }
}