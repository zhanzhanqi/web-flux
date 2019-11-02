package com.example.webflux

import com.alibaba.fastjson.JSON
import com.example.webflux.domain.User
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserWebClientTest {

    @Test
    fun testUsers() {
        val webTestClient = WebClient.create("http://localhost:8080/router")

        val users = webTestClient
                .get()
                .uri("/user/1001")
                .exchange()
                .flatMap { it.bodyToMono(User::class.java) }
                .block()

        println(JSON.toJSON(users))
    }



}