package com.oneeyedmen.presstotest

import com.azimolabs.conditionwatcher.ConditionWatcher
import com.azimolabs.conditionwatcher.Instruction


class InstrumentedAcceptanceTests : AcceptanceTests() {
    override fun waitForCondition(description: String, condition: () -> Boolean) = ConditionWatcher.waitForCondition(
        object : Instruction() {
            override fun getDescription() = description
            override fun checkCondition() = condition()
        })
}

