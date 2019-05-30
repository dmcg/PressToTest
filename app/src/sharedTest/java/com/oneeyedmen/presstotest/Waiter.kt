package com.oneeyedmen.presstotest

import java.util.concurrent.TimeoutException


data class Waiter(
    val defaultDescription: String = "something",
    val defaultTimeoutMillis: Long = 60000,
    val defaultPollMillis: Long = 250,
    val twiddler: () -> Unit = {}
) {
    fun waitFor(
        description: String? = null,
        timeoutMillis: Long = -1,
        pollMillis: Long = -1,
        condition: () -> Boolean
    ) {
        val resolvedDescription = description ?: defaultDescription
        val resolvedTimeoutMillis = if (timeoutMillis >= 0 ) timeoutMillis else defaultTimeoutMillis
        val resolvedPollMillis = if (pollMillis >= 0 ) pollMillis else defaultPollMillis

        val endT = System.currentTimeMillis() + resolvedTimeoutMillis
        while (!condition()) {
            if (System.currentTimeMillis() > endT)
                throw TimeoutException("Timeout waiting for $resolvedDescription after more than $resolvedTimeoutMillis ms")
            Thread.sleep(resolvedPollMillis)
            twiddler()
        }
    }

    inline fun <reified T: Throwable> waitForNo(
        description: String? = null,
        timeoutMillis: Long = -1,
        pollMillis: Long = -1,
        crossinline block: () -> Unit
    ) {
        waitFor(description, timeoutMillis, pollMillis) {
            try {
                block()
                true
            } catch (t: Throwable) {
                if (t is T) false else throw t
            }
        }
    }

}