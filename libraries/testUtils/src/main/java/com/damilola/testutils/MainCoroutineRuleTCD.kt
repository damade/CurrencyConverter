package com.damilola.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRuleTCD(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) :
    TestWatcher(),
    TestCoroutineScope by createTestCoroutineScope(TestCoroutineDispatcher() + TestCoroutineExceptionHandler() + dispatcher) {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
        cleanupTestCoroutines()
    }
}