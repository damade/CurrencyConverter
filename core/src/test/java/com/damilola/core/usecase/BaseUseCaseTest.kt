package com.damilola.core.usecase

import app.cash.turbine.test
import com.damilola.core.exception.NoParamsException
import com.damilola.testutils.ERROR_MSG
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class BaseUseCaseTest {

    @Test
    fun `check that ExceptionUseCase throws exception`() = runTest {

        ExceptionUseCase(ioDispatcher = UnconfinedTestDispatcher()).execute()
            .test {
                assertThat(awaitError())
                    .hasMessageThat()
                    .isEqualTo(ERROR_MSG)
            }

    }

    @Test(expected = NoParamsException::class)
    fun `check that calling ParamUseCase without params throws exception`() = runTest {
        ParamUseCase(UnconfinedTestDispatcher())()
    }

    @Test
    fun `check that ParamsUseCase returns correct data`() = runTest {
        val param = "Correct data"
        val useCase = ParamUseCase(UnconfinedTestDispatcher())
        val result: String = useCase(param).first()
        assertThat(result).isEqualTo(param)
    }
}