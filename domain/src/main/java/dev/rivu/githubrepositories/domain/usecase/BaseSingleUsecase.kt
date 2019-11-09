package dev.rivu.githubrepositories.domain.usecase

import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single

abstract class BaseSingleUsecase<Data, in Param> constructor(private val schedulerProvider: SchedulerProvider) {

    protected abstract fun buildUseCase(param: Param): Single<Data>

    open fun execute(
        param: Param,
        executionType: UsecaseExecutionType = UsecaseExecutionType.IO
    ): Single<Data> {
        return buildUseCase(param)
            .subscribeOn(
                if (executionType is UsecaseExecutionType.IO) {
                    schedulerProvider.io()
                } else {
                    schedulerProvider.computation()
                }
            )
            .observeOn(schedulerProvider.ui())
    }
}