package com.example.webflux.controller

import com.example.webflux.domain.User
import com.example.webflux.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class Controller {

    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("hello")
    fun helloWorld(): String {
        return "Hello World"
    }

    @GetMapping("users/{id}")
    fun findUser(@PathVariable id: Long): User {
        return userRepository.findById(id).orElse(User())
    }

    @PostMapping("users")
    fun addUser(@RequestBody user: User): Long? {
        return userRepository.save(user).id
    }

}