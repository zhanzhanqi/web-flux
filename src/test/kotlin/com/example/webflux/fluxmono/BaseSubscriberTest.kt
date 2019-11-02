package com.example.webflux.fluxmono

import org.junit.jupiter.api.Test
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

class BaseSubscriberTest {

    @Test
    fun testSampleSubscriber() {
        val ss = SampleSubscriber<Int>()
        val ints = Flux.range(1,20)
        ints.subscribe ({ println(it)}, null, { println("Done")}, { it.request(10)})
        ints.subscribe(ss)
    }

}

class SampleSubscriber<T> : BaseSubscriber<T>() {

    override fun hookOnSubscribe(subscription: Subscription) {
        println("Subscribed")
        request(1)
    }

    override fun hookOnNext(value: T) {
        println(value)
        request(1)
    }

}