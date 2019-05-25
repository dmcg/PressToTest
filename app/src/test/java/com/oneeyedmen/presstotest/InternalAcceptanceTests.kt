package com.oneeyedmen.presstotest

import org.robolectric.shadows.ShadowLooper

class InternalAcceptanceTests : AcceptanceTests(robolectricWaiter)

private val robolectricWaiter = Waiter(twiddler = { ShadowLooper.runMainLooperToNextTask() })
