package dev.rivu.githubrepositories.domain.usecase

import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import io.reactivex.Flowable

abstract class BaseFlowableUsecase<Data, in Param> constructor(private val schedulerProvider: SchedulerProvider) {

    protected abstract fun buildUseCase(param: Param): Flowable<Data>

    open fun execute(
        param: Param,
        executionType: UsecaseExecutionType = UsecaseExecutionType.IO
    ): Flowable<Data> {
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