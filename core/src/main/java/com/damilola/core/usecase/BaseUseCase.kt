package com.damilola.core.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCase <in Params, out T> {

    /**
     * The coroutine context this use case should execute on.
     */
    abstract val dispatcher: CoroutineDispatcher

    /**
     * Function which builds Flow instance based on given arguments
     * @param params use case arguments
     */
    abstract fun execute(params: Params? = null): Flow<T>

    operator fun invoke(params: Params? = null): Flow<T> = execute(params).flowOn(dispatcher)
}