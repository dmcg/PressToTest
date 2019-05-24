package com.oneeyedmen.presstotest

import org.robolectric.shadows.ShadowLooper
import java.util.concurrent.TimeUnit


class InternalAcceptanceTests : AcceptanceTests() {
    override fun sleep(millis: Long) = ShadowLooper.idleMainLooper(millis, TimeUnit.MILLISECONDS)
}

