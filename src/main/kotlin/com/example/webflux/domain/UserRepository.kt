package com.example.webflux.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.QueryByExampleExecutor
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : CrudRepository<User, Long>, QueryByExampleExecutor<User>