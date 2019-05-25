package com.oneeyedmen.presstotest

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.util.concurrent.TimeoutException

class WaiterTests {

    val waiter = Waiter()

    @Test
    fun returns_immediately_if_condition_is_met() {
        var conditionCount = 0
        waiter.waitFor {
            conditionCount++
            true
        }
        assertEquals(1, conditionCount)
    }

    @Test
    fun polls_until_condition_is_met() {
        var conditionCount = 0
        waiter.waitFor {
            conditionCount++ == 2
        }
        assertEquals(3, conditionCount)
    }

    @Test(timeout = 1000)
    fun throws_if_timeout_before_condition_is_met() {
        var conditionCount = 0
        try {
            waiter.waitFor("a thing", timeoutMillis = 100, pollMillis = 100) {
                conditionCount++
                false
            }
            fail("Should have timed out")
        } catch (x: TimeoutException) {
            assertEquals(2, conditionCount)
            assertTrue(x.message!!.contains("Timeout waiting for a thing"))
        }
    }

    @Test(timeout = 1000)
    fun takes_defaults_from_object() {
        val configured = Waiter("a thing", 100, 50)

        var conditionCount = 0
        try {
            configured.waitFor {
                conditionCount++
                false
            }
            fail("Should have timed out")
        } catch (x: TimeoutException) {
            assertEquals(3, conditionCount)
            assertTrue(x.message!!.contains("Timeout waiting for a thing"))
        }
    }

    @Test
    fun invokes_twiddler() {
        var twiddleCount = 0
        var conditionCount = 0

        val configured = Waiter(twiddler = { twiddleCount++ } )

        configured.waitFor {
            conditionCount++ == 2
        }
        assertEquals(3, conditionCount)
        assertEquals(2, twiddleCount)
    }

    @Test
    fun waitForNo_polls_until_exception_not_thrown() {
        var conditionCount = 0

        waiter.waitForNo<IOException> {
            if (conditionCount++ != 2) throw IOException()
        }
        assertEquals(3, conditionCount)
    }

    @Test(expected = IOException::class)
    fun waitForNo_passes_on_other_exception() {
        waiter.waitForNo<RuntimeException> {
            throw IOException()
        }
    }
}

