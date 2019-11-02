package com.example.webflux.fluxmono

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import java.lang.RuntimeException

class Test {

    @Test
    fun testFluxRange() {
        Flux.range(5,3)
                .subscribe { println(it)}
    }

    @Test
    fun testSubscribeWithError() {
        Flux.range(1,4)
                .map { if (it <= 3) it else throw RuntimeException("Got to 4") }
                .subscribe({ println(it)}, { println("Error: $it")})
    }

    @Test
    fun testSubscribeWithCompletion() {
        Flux.range(1,4)
                .subscribe({ println(it)}, { println("Error: $it")}, { println("Done")})
    }

    @Test
    fun testSubscribeWithSubscription() {
        Flux.range(1, 20)
                .subscribe({ println(it)}, null, null, {it.request(10)})

    }
}