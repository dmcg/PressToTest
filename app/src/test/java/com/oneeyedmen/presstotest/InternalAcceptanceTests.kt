package com.oneeyedmen.presstotest

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import org.junit.Before
import org.robolectric.shadows.ShadowLooper

class InternalAcceptanceTests : AcceptanceTests(robolectricWaiter) {
    @Before fun hack() {
        // see https://stackoverflow.com/a/45448554/97777
        (activityRule.activity.lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_START)
    }
}

private val robolectricWaiter = Waiter(twiddler = { ShadowLooper.runMainLooperToNextTask() })
