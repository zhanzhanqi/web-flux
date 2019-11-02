package com.example.webflux.controller

import com.example.webflux.domain.ReactiveUserRepository
import com.example.webflux.domain.User
import com.example.webflux.domain.UserRepository
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("webflux")
class WebFluxAnnotationController {

    @Autowired
    private lateinit var userRepository: ReactiveUserRepository

    @PostMapping("users")
    fun create(@RequestBody userStream: Publisher<User>): Mono<Void> {
        return this.userRepository.saveAll(userStream).then()
    }

    @GetMapping("users")
    fun list(): Flux<User> {
        return userRepository.findAll()
    }

    @GetMapping("users/{id}")
    fun findById(@PathVariable id: Long): Mono<User> {
        return userRepository.findById(id)
    }

}