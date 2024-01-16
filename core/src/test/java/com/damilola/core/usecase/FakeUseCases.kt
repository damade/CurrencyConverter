package com.damilola.core.usecase

import com.damilola.testutils.ERROR_MSG
import com.damilola.core.exception.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class ExceptionUseCase(private val ioDispatcher: CoroutineDispatcher) :
    BaseUseCase<Unit, Unit>() {

    override fun execute(params: Unit?): Flow<Unit> {
        return flow {
            throw SocketTimeoutException(ERROR_MSG)
        }
    }

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher
}

internal class ParamUseCase(private val ioDispatcher: CoroutineDispatcher) :
    BaseUseCase<String, String>() {

    override fun execute(params: String?): Flow<String> {
        requireParams(params)
        return flowOf(params)
    }

    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher
}
