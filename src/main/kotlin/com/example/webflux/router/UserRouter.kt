package com.example.webflux.router

import com.example.webflux.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class UserRouter(private val handler: UserHandler) {

    @Bean
    fun myRouter() = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("router/user/{id}", handler::getUser)
            POST("router/user", handler::createUser)
            GET("router/user",handler::listUser)
        }
    }

}