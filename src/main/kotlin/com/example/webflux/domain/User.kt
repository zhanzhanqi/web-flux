package com.example.webflux.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
class User {

    @Id
    var id: Long? = null
    var firstName: String = ""
    var lastName: String = ""
    var age: Int = -1

}