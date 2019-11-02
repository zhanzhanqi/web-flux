package com.example.webflux.domain

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ReactiveUserRepository : ReactiveMongoRepository<User, Long>