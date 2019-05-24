package com.oneeyedmen.presstotest


class InstrumentedAcceptanceTests : AcceptanceTests() {
    override fun sleep(millis: Long) = Thread.sleep(millis)
}

