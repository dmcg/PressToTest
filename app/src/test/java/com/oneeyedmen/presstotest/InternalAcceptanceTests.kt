package com.oneeyedmen.presstotest

import com.azimolabs.conditionwatcher.ConditionWatcher
import com.azimolabs.conditionwatcher.Instruction
import org.robolectric.shadows.ShadowLooper


class InternalAcceptanceTests : AcceptanceTests() {
    override fun waitForCondition(description: String, condition: () -> Boolean) = ConditionWatcher.waitForCondition(
        object : Instruction() {
            override fun getDescription() = description
            override fun checkCondition(): Boolean {
                ShadowLooper.runMainLooperToNextTask()
                return condition()
            }
        })
}

