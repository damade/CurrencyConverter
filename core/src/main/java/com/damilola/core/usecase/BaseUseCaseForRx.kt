package com.damilola.core.usecase


import com.damilola.core.ext.errorMessage
import com.damilola.core.model.Either
import com.damilola.core.model.Failure

import com.jakewharton.rxrelay3.PublishRelay
import com.jakewharton.rxrelay3.Relay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseUseCaseForRx<TParameter, TResultModel> {

    // Members

    private val compositeDisposable = CompositeDisposable()
    private val resultRelay: Relay<Either<Failure, TResultModel>> = PublishRelay.create()

    // Public Api

    fun execute(parameter: TParameter? = null): Observable<Either<Failure, TResultModel>> {
        compositeDisposable.add(
            onExecute(parameter).subscribe(
                {
                    emitResult(result = it)
                },
                {
                    emitResult(Either.Error(Failure.ThrowableFailure(it)))
                }
            )
        )

        return resultRelay
    }

    fun clear() {
        compositeDisposable.clear()
    }

    // Private Api

    private fun emitResult(result: Either<Failure, TResultModel>) {
        try {
            // ANR-2296 Handle edge case when Internet connection is slower than ~35kbps. In this case,
            // Retrofit throws an error with a null object, so it is safer to handle here instead
            // of in each network source. #mindBlowing
            resultRelay.accept(
                when {
                    result is Either.Error && result.getFailureOrNull() == null ->
                        Either.Error(Failure.ThrowableFailure(Throwable("Handle NPE error in a result.")))
                    result is Either.Success && result.success == null ->
                        Either.Error(Failure.ThrowableFailure(Throwable("Handle NPE model in a result.")))
                    else -> result
                }
            )
        } catch (e: Exception) {
           resultRelay.accept(
               Either.Error(Failure.UnexpectedFailure(e.errorMessage))
           )
        }
    }

    fun result(): Observable<Either<Failure, TResultModel>> = resultRelay

    // Abstract

    /**
     * Implement the main work of your use class here.
     */
    protected abstract fun onExecute(parameter: TParameter?): Observable<Either<Failure, TResultModel>>
}
