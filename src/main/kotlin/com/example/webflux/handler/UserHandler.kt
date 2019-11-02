package com.example.webflux.handler

import com.example.webflux.domain.ReactiveUserRepository
import com.example.webflux.domain.User
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler(private val userRepository: ReactiveUserRepository) {

    fun listUser(request: ServerRequest): Mono<ServerResponse> {
        val users = userRepository.findAll()
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users, User::class.java)
    }

    fun createUser(request: ServerRequest): Mono<ServerResponse> {
        val user = request.body(BodyExtractors.toMono(User::class.java))
                .flatMap { userRepository.save(it) }
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user, User::class.java)
    }

    fun getUser(request: ServerRequest): Mono<ServerResponse> {
        val userId = request.pathVariable("id").toLong()
        val notFound = ServerResponse.notFound().build()
        val userMono = userRepository.findById(userId)
        return userMono
                .flatMap {
                    user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(user))
                }.switchIfEmpty(notFound)
    }

}